package net.blueasclepias.bejeweled.server.worldgen.bootstrap;

import net.blueasclepias.bejeweled.common.data.ore.definition.OreEntry;
import net.blueasclepias.bejeweled.common.data.ore.definition.OrePlacement;
import net.blueasclepias.bejeweled.common.data.ore.registry.OreRegistry;
import net.blueasclepias.bejeweled.server.worldgen.feature.ConfiguredFeatures;
import net.blueasclepias.bejeweled.server.worldgen.feature.ModPlacedFeatures;
import net.blueasclepias.bejeweled.server.worldgen.registry.CustomFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Data generation for world generation features.
 */
public class WorldGenBootstrap {

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        Map<OreEntry, Block> blocks = OreRegistry.blocksByEntry();
        for (OreEntry entry : OreRegistry.entries()) {
            if (entry.placement() instanceof OrePlacement.Generic) {
                Block block = Objects.requireNonNull(blocks.get(entry), () -> "No block bound for ore entry " + entry.id());
                context.register(entry.configuredFeatureKey(), ConfiguredFeatures.createGenericOreConfigFeat(entry, block));
            } else if (entry.placement() instanceof OrePlacement.Custom custom) {
                context.register(
                        entry.configuredFeatureKey(),
                        new ConfiguredFeature<>(custom.feature().get(), NoneFeatureConfiguration.INSTANCE)
                );
            }
        }

        context.register(
                ConfiguredFeatures.ALL_CORAL_POLYP_CONFIGURED,
                new ConfiguredFeature<>(
                        CustomFeatures.CORAL_POLYP.get(),
                        NoneFeatureConfiguration.INSTANCE
                )
        );
    }

    public static void bootstrapPlaced(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configured =
                context.lookup(Registries.CONFIGURED_FEATURE);

        // all polyps use the same feature
        context.register(
                ModPlacedFeatures.CORAL_POLYP,
                new PlacedFeature(
                        configured.getOrThrow(ConfiguredFeatures.ALL_CORAL_POLYP_CONFIGURED),
                        List.of(
                                CountPlacement.of(8),
                                InSquarePlacement.spread(),
                                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR)
                        )
                )
        );

        // ores
        OreRegistry.entries().forEach(entry ->
            context.register(
                    entry.placedFeatureKey(),
                    new PlacedFeature(
                            configured.getOrThrow(entry.configuredFeatureKey()),
                            entry.placementModifiers()
                    )
            )
        );
    }

}

