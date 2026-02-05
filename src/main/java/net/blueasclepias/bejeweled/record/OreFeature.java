package net.blueasclepias.bejeweled.record;

import net.blueasclepias.bejeweled.feature.biomeplacement.BiomeFeaturePlacement;
import net.blueasclepias.bejeweled.feature.configuredfeature.ModConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record OreFeature(
        @NotNull OreBase base,
        @NotNull ResourceKey<ConfiguredFeature<?, ?>> configuredFeature,
        @NotNull ResourceKey<PlacedFeature> placedFeature,
        @NotNull List<PlacementModifier> placementModifiers,
        @NotNull BiomeFeaturePlacement biomeFeature,
        int size
) {
    public boolean isGeneric() {
        return ModConfiguredFeatures.GENERICS.contains(configuredFeature);
    }
}