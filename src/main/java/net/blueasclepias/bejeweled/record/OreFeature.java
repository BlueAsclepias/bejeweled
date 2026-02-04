package net.blueasclepias.bejeweled.record;

import net.blueasclepias.bejeweled.enums.OreBase;
import net.blueasclepias.bejeweled.feature.biomeplacement.BiomeFeaturePlacement;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record OreFeature(
        @NotNull OreBase base,
        @NotNull ResourceKey<PlacedFeature> resourceKey,
        @NotNull List<PlacementModifier> placementModifiers,
        @NotNull BiomeFeaturePlacement biomeFeature
) {}