package net.blueasclepias.bejeweled.data.definition.ore;

import net.blueasclepias.bejeweled.data.accessor.OreAccessor;
import net.blueasclepias.bejeweled.feature.biomeplacement.BiomeFeaturePlacement;
import net.blueasclepias.bejeweled.interfaces.IOreFeature;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public record OreFeature(
        @NotBlank String id,
        @NotNull OreDefinition definition, // identity
        @NotNull OreVariant variant, // block model metadata
        @NotNull ResourceKey<ConfiguredFeature<?, ?>> configuredFeature,
        boolean isGeneric,
        @NotNull ResourceKey<PlacedFeature> placedFeature,
        @NotNull List<PlacementModifier> placementModifiers,
        @NotNull BiomeFeaturePlacement biomeFeature,
        int size
) implements IOreFeature {
    public OreFeature (
            @NotBlank String id,
            @NotNull OreDefinition definition,
            @NotNull OreVariant variant,
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
        OreAccessor.registerFeature(fromNamespaceAndPath(MOD_ID, id), this);
    }
}