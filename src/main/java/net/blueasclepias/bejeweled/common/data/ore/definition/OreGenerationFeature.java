package net.blueasclepias.bejeweled.common.data.ore.definition;

import net.blueasclepias.bejeweled.common.api.IOreFeature;
import net.blueasclepias.bejeweled.common.data.ore.registry.OreFeatureRegistry;
import net.blueasclepias.bejeweled.server.worldgen.placement.BiomeFeaturePlacement;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public record OreGenerationFeature(
        @NotBlank String id,
        @NotNull OreDefinition definition, // identity
        @NotNull OreBlockVariant variant, // block model metadata
        @NotNull ResourceKey<ConfiguredFeature<?, ?>> configuredFeature,
        boolean isGeneric,
        @NotNull ResourceKey<PlacedFeature> placedFeature,
        @NotNull List<PlacementModifier> placementModifiers,
        @NotNull BiomeFeaturePlacement biomeFeature,
        int size
) implements IOreFeature {
    public OreGenerationFeature(
            @NotBlank String id,
            @NotNull OreDefinition definition,
            @NotNull OreBlockVariant variant,
            @NotNull ResourceKey<ConfiguredFeature<?, ?>> configuredFeature,
            boolean isGeneric,
            @NotNull ResourceKey<PlacedFeature> placedFeature,
            @NotNull List<PlacementModifier> placementModifiers,
            @NotNull BiomeFeaturePlacement biomeFeature,
            int size){
        this.id = id;
        this.definition = definition;
        this.variant = variant;
        this.configuredFeature = configuredFeature;
        this.isGeneric = isGeneric;
        this.placedFeature = placedFeature;
        this.placementModifiers = placementModifiers;
        this.biomeFeature = biomeFeature;
        this.size = size;
        OreFeatureRegistry.registerFeature(ResourceLocation.fromNamespaceAndPath(MOD_ID, id), this);
    }
}