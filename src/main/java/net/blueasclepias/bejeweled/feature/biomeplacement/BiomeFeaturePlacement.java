package net.blueasclepias.bejeweled.feature.biomeplacement;

import net.blueasclepias.bejeweled.interfaces.BiomeFilter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jetbrains.annotations.NotNull;

/**
 * Defines the placement of a feature in specific biomes during a certain generation step.
 * @param feature
 * @param biomeFilter
 * @param step
 */
public record BiomeFeaturePlacement(
        @NotNull ResourceKey<PlacedFeature> feature,
        @NotNull BiomeFilter biomeFilter,
        @NotNull GenerationStep.Decoration step
) {}