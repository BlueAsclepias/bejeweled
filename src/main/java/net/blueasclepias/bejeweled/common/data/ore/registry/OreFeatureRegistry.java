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

/**
 * Static registry that connects {@link OreGenerationFeature} metadata to the actual ore blocks created for those
 * features.
 * {@link OreGenerationFeature} instances register themselves here when constructed, and block registration later
 * binds each created ore block back to the corresponding feature id. Worldgen and datagen reuse this registry to
 * iterate every feature, to access only generic feature variants, and to resolve the canonical block for a specific
 * ore definition and block variant pair. The reverse lookup helpers compare definitions and variants by instance
 * identity, so callers are expected to use the shared objects already stored in the registry.
 */
public final class OreFeatureRegistry {

    private static final Map<ResourceLocation, OreGenerationFeature> FEATURES = new HashMap<>();
    private static final Multimap<OreGenerationFeature, Block> BLOCKS_BY_FEATURE = HashMultimap.create();

    public static void registerFeature(ResourceLocation id, OreGenerationFeature feat) {
        FEATURES.put(id, feat);
    }

    /**
     * Associates a newly registered block with a previously registered feature id. Missing ids fail fast instead of
     * silently creating an orphaned binding.
     */
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

    /**
     * Returns a filtered multimap view containing only features flagged as generic.
     */
    public static Multimap<OreGenerationFeature, Block> allBlocksByGenericFeatures() {
        return Multimaps.filterKeys(BLOCKS_BY_FEATURE, OreGenerationFeature::isGeneric);
    }

    public static Multimap<OreGenerationFeature, Block> allBlocksByFeature() {
        return BLOCKS_BY_FEATURE;
    }

    /**
     * Finds the registered feature whose ore definition and block variant are the exact instances supplied here.
     */
    public static Optional<OreGenerationFeature> find(OreDefinition def, OreBlockVariant variant) {
        return allFeatures().stream()
                .filter(f -> f.definition() == def && f.variant() == variant)
                .findFirst();
    }

    /**
     * Resolves the first block bound to the matching feature pair, if that feature has already been associated with a
     * block.
     */
    public static Optional<Block> getBlock(OreDefinition def, OreBlockVariant variant) {
        return find(def, variant)
                .map(BLOCKS_BY_FEATURE::get)
                .filter(c -> !c.isEmpty())
                .map(c -> c.iterator().next());
    }
}
