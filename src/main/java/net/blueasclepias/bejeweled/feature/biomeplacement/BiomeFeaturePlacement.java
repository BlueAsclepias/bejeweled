package net.blueasclepias.bejeweled.feature.biomeplacement;

import net.blueasclepias.bejeweled.interfaces.BiomeFilter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Defines the placement of a feature in specific biomes during a certain generation step.
 * @param features
 * @param biomeFilter
 * @param step
 */
public record BiomeFeaturePlacement(
        @NotBlank String name,
        @NotNull Set<ResourceKey<PlacedFeature>> features,
        @NotNull BiomeFilter biomeFilter,
        @NotNull GenerationStep.Decoration step
) {
    public BiomeFeaturePlacement {
        Objects.requireNonNull(features);
        Objects.requireNonNull(biomeFilter);
        Objects.requireNonNull(step);

        // 🔑 Make it mutable
        features = new HashSet<>(features);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BiomeFeaturePlacement other)) return false;
        return biomeFilter.equals(other.biomeFilter)
                && step == other.step
                && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(biomeFilter, step);
    }
}