package net.blueasclepias.bejeweled.common.data.ore.registry;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import net.blueasclepias.bejeweled.common.data.ore.definition.OreBlockVariant;
import net.blueasclepias.bejeweled.common.data.ore.definition.OreDefinition;
import net.blueasclepias.bejeweled.common.data.ore.definition.OreGenerationFeature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class OreFeatureRegistry {

    private static final Map<ResourceLocation, OreGenerationFeature> FEATURES = new HashMap<>();
    private static final Multimap<OreGenerationFeature, Block> BLOCKS_BY_FEATURE = HashMultimap.create();

    public static void registerFeature(ResourceLocation id, OreGenerationFeature feat) {
        FEATURES.put(id, feat);
    }

    public static void bind(Block block, @NotNull ResourceLocation id) {
        OreGenerationFeature feat = Objects.requireNonNull(FEATURES.get(id));
        BLOCKS_BY_FEATURE.put(feat,block);
    }

    public static Map<ResourceLocation, OreGenerationFeature> all(){
        return FEATURES;
    }

    public static Collection<OreGenerationFeature> allFeatures() {
        return FEATURES.values();
    }

    public static Collection<Block> allBlocks() {
        return BLOCKS_BY_FEATURE.values();
    }

    public static Multimap<OreGenerationFeature, Block> allBlocksByGenericFeatures() {
        return Multimaps.filterKeys(BLOCKS_BY_FEATURE, OreGenerationFeature::isGeneric);
    }

    public static Multimap<OreGenerationFeature, Block> allBlocksByFeature() {
        return BLOCKS_BY_FEATURE;
    }

    public static Optional<OreGenerationFeature> find(OreDefinition def, OreBlockVariant variant) {
        return allFeatures().stream()
                .filter(f -> f.definition() == def && f.variant() == variant)
                .findFirst();
    }

    public static Optional<Block> getBlock(OreDefinition def, OreBlockVariant variant) {
        return find(def, variant)
                .map(BLOCKS_BY_FEATURE::get)
                .filter(c -> !c.isEmpty())
                .map(c -> c.iterator().next());
    }
}
