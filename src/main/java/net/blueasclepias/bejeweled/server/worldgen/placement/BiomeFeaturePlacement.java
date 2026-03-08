package net.blueasclepias.bejeweled.server.worldgen.placement;

import net.blueasclepias.bejeweled.server.worldgen.util.IBiomeFilter;
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
 * @param IBiomeFilter
 * @param step
 */
public record BiomeFeaturePlacement(
        @NotBlank String name,
        @NotNull Set<ResourceKey<PlacedFeature>> features,
        @NotNull IBiomeFilter IBiomeFilter,
        @NotNull GenerationStep.Decoration step
) {
    public BiomeFeaturePlacement {
        Objects.requireNonNull(features);
        Objects.requireNonNull(IBiomeFilter);
        Objects.requireNonNull(step);

        // 🔑 Make it mutable
        features = new HashSet<>(features);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BiomeFeaturePlacement other)) return false;
        return IBiomeFilter.equals(other.IBiomeFilter)
                && step == other.step
                && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IBiomeFilter, step);
    }
}