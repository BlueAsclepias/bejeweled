package net.blueasclepias.bejeweled.server.worldgen.bootstrap;

import net.blueasclepias.bejeweled.common.data.ore.registry.OreFeatureRegistry;
import net.blueasclepias.bejeweled.server.worldgen.feature.ConfiguredFeatures;
import net.blueasclepias.bejeweled.server.worldgen.feature.PlacedFeatures;
import net.blueasclepias.bejeweled.server.worldgen.registry.CustomFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;

import java.util.List;

/**
 * Data generation for world generation features.
 */
public class WorldGenBootstrap {

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        // generics
        OreFeatureRegistry.allBlocksByGenericFeatures()
                        .forEach((feat, block) -> {
                            context.register(
                                    feat.configuredFeature(),
                                    ConfiguredFeatures.createGenericOreConfigFeat(feat, block)
                            );
                        });

        // specifics
        context.register(
                ConfiguredFeatures.ALL_CORAL_POLYP_CONFIGURED,
                new ConfiguredFeature<>(
                        CustomFeatures.CORAL_POLYP.get(),
                        NoneFeatureConfiguration.INSTANCE
                )
        );

        context.register(
                ConfiguredFeatures.STONE_BERYL_CONFIGURED,
                new ConfiguredFeature<>(
                        CustomFeatures.STONE_BERYL.get(),
                        NoneFeatureConfiguration.INSTANCE
                )
        );

        context.register(
                ConfiguredFeatures.GRANITE_BERYL_CONFIGURED,
                new ConfiguredFeature<>(
                        CustomFeatures.GRANITE_BERYL.get(),
                        NoneFeatureConfiguration.INSTANCE
                )
        );

        context.register(
                ConfiguredFeatures.ALL_TURQUOISE_CONFIGURED,
                new ConfiguredFeature<>(
                        CustomFeatures.TURQUOISE.get(),
                        NoneFeatureConfiguration.INSTANCE
                )
        );

        context.register(
                ConfiguredFeatures.ALL_OLIVINE_CONFIGURED,
                new ConfiguredFeature<>(
                        CustomFeatures.OLIVINE_CONFIGURED.get(),
                        NoneFeatureConfiguration.INSTANCE
                )
        );

    }

    public static void bootstrapPlaced(BootstapContext<net.minecraft.world.level.levelgen.placement.PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configured =
                context.lookup(Registries.CONFIGURED_FEATURE);

        // all polyps use the same feature
        context.register(
                PlacedFeatures.CORAL_POLYP,
                new net.minecraft.world.level.levelgen.placement.PlacedFeature(
                        configured.getOrThrow(ConfiguredFeatures.ALL_CORAL_POLYP_CONFIGURED),
                        List.of(
                                CountPlacement.of(8),
                                InSquarePlacement.spread(),
                                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR)
                        )
                )
        );

        // ores
        OreFeatureRegistry.allFeatures().forEach(feat ->
            context.register(
                    feat.placedFeature(),
                    new net.minecraft.world.level.levelgen.placement.PlacedFeature(
                            configured.getOrThrow(feat.configuredFeature()),
                            feat.placementModifiers()
                    )
            )
        );
    }

}

