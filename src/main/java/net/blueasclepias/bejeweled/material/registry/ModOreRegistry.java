package net.blueasclepias.bejeweled.material.registry;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import net.blueasclepias.bejeweled.material.definition.ore.OreDefinition;
import net.blueasclepias.bejeweled.material.definition.ore.OreFeature;
import net.blueasclepias.bejeweled.material.definition.ore.OreVariant;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ModOreRegistry {

    private static final Map<ResourceLocation, OreFeature> FEATURES = new HashMap<>();
    private static final Map<ResourceLocation, OreVariant> VARIANTS = new HashMap<>();
    private static final Map<ResourceLocation, OreDefinition> DEFINITIONS = new HashMap<>();
    private static final Map<Block, OreFeature> BLOCK_BINDINGS = new HashMap<>();
    private static final Multimap<OreFeature, Block> BLOCKS_BY_FEATURE = HashMultimap.create();

    public static void registerFeature(ResourceLocation id, OreFeature feat) {
        FEATURES.put(id, feat);
    }

    public static void registerVariant(ResourceLocation id, OreVariant var) {
        VARIANTS.put(id,var);
    }

    public static void registerDefinition(ResourceLocation id, OreDefinition def) {
        DEFINITIONS.put(id, def);
    }

    public static void bind(Block block, @NotNull ResourceLocation id) {
        OreFeature feat = Objects.requireNonNull(FEATURES.get(id));
        BLOCK_BINDINGS.put(block,feat);
        BLOCKS_BY_FEATURE.put(feat,block);
    }

    public static Collection<OreFeature> allFeatures() {
        return FEATURES.values();
    }

    public static Multimap<OreFeature, Block> allBlocksByGenericFeatures() {
        return Multimaps.filterKeys(BLOCKS_BY_FEATURE, OreFeature::isGeneric);
    }

    public static Multimap<OreFeature, Block> allBlocksByFeature() {
        return BLOCKS_BY_FEATURE;
    }

    public static Optional<OreFeature> find(OreDefinition def, OreVariant variant) {
        return allFeatures().stream()
                .filter(f -> f.definition() == def && f.variant() == variant)
                .findFirst();
    }

    public static Optional<Block> getBlock(OreDefinition def, OreVariant variant) {
        return find(def, variant)
                .map(BLOCKS_BY_FEATURE::get)
                .filter(c -> !c.isEmpty())
                .map(c -> c.iterator().next());
    }
}
