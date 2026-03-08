package net.blueasclepias.bejeweled.common.api;

import net.blueasclepias.bejeweled.common.data.ore.definition.OreBlockVariant;
import net.blueasclepias.bejeweled.common.data.ore.definition.OreDefinition;
import net.blueasclepias.bejeweled.server.worldgen.placement.BiomeFeaturePlacement;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public interface IOreFeature {
    String id();
    OreDefinition definition();
    OreBlockVariant variant();
    ResourceKey<ConfiguredFeature<?, ?>> configuredFeature();
    boolean isGeneric();
    ResourceKey<PlacedFeature> placedFeature();
    List<PlacementModifier> placementModifiers();
    BiomeFeaturePlacement biomeFeature();
    int size();
}
