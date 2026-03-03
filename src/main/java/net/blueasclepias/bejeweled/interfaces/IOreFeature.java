package net.blueasclepias.bejeweled.interfaces;

import net.blueasclepias.bejeweled.data.definition.ore.OreDefinition;
import net.blueasclepias.bejeweled.data.definition.ore.OreVariant;
import net.blueasclepias.bejeweled.feature.biomeplacement.BiomeFeaturePlacement;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public interface IOreFeature {
    String id();
    OreDefinition definition();
    OreVariant variant();
    ResourceKey<ConfiguredFeature<?, ?>> configuredFeature();
    boolean isGeneric();
    ResourceKey<PlacedFeature> placedFeature();
    List<PlacementModifier> placementModifiers();
    BiomeFeaturePlacement biomeFeature();
    int size();
}
