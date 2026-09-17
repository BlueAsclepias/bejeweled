package net.blueasclepias.bejeweled.common.data.ore.definition;

import net.blueasclepias.bejeweled.server.worldgen.feature.ConfiguredFeatures;
import net.blueasclepias.bejeweled.server.worldgen.feature.PlacedFeatures;
import net.blueasclepias.bejeweled.server.worldgen.placement.BiomeFeaturePlacement;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A single generated ore block: the gem it identifies with ({@link OreDefinition}), the host-block variant it
 * replaces ({@link OreBlockVariant}), how it is placed in the world ({@link OrePlacement}), and the biomes it can
 * appear in.
 * Instances are pure data. Registering an entry into
 * {@link net.blueasclepias.bejeweled.common.data.ore.registry.OreRegistry} is an explicit step performed by whoever
 * assembles the mod's ore list, not a constructor side effect. The configured/placed feature ids are derived from
 * {@link #id()} rather than stored separately, so there is exactly one place that owns the entry's identity.
 */
public record OreEntry(
        @NotNull String id,
        @NotNull OreDefinition definition,
        @NotNull OreBlockVariant variant,
        int size,
        @NotNull List<PlacementModifier> placementModifiers,
        @NotNull BiomeFeaturePlacement biomeFeature,
        @NotNull OrePlacement placement
) {
    public ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey() {
        return ConfiguredFeatures.create(id);
    }

    public ResourceKey<PlacedFeature> placedFeatureKey() {
        return PlacedFeatures.create(id);
    }
}
