package net.blueasclepias.bejeweled.common.data.ore.definition;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.function.Supplier;

/**
 * How an {@link OreEntry} is placed in the world.
 * {@link Generic} entries are placed with vanilla's {@code Feature.ORE} + {@code OreConfiguration}, built
 * automatically for the block bound to the entry. {@link Custom} entries are placed by a bespoke {@link Feature}
 * (e.g. one that only places near lava or water) supplied by the mod's feature registry.
 */
public sealed interface OrePlacement permits OrePlacement.Generic, OrePlacement.Custom {

    record Generic() implements OrePlacement {}

    record Custom(Supplier<Feature<NoneFeatureConfiguration>> feature) implements OrePlacement {}

    OrePlacement GENERIC = new Generic();

    static OrePlacement generic() {
        return GENERIC;
    }

    static OrePlacement custom(Supplier<Feature<NoneFeatureConfiguration>> feature) {
        return new Custom(feature);
    }
}
