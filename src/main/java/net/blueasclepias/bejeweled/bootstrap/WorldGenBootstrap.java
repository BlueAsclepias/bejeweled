package net.blueasclepias.bejeweled.bootstrap;

import net.blueasclepias.bejeweled.feature.configuredfeature.ModConfiguredFeatures;
import net.blueasclepias.bejeweled.feature.placedfeature.ModPlacedFeature;
import net.blueasclepias.bejeweled.material.registry.ModOreRegistry;
import net.blueasclepias.bejeweled.registry.ModFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

/**
 * Data generation for world generation features.
 */
public class WorldGenBootstrap {

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        // generics
        ModOreRegistry.allBlocksByGenericFeatures()
                        .forEach((feat, block) -> {
                            context.register(
                                    feat.configuredFeature(),
                                    ModConfiguredFeatures.createGenericOreConfigFeat(feat, block)
                            );
                        });

        // specifics
        context.register(
                ModConfiguredFeatures.ALL_CORAL_POLYP_CONFIGURED,
                new ConfiguredFeature<>(
                        ModFeatures.CORAL_POLYP.get(),
                        NoneFeatureConfiguration.INSTANCE
                )
        );

        context.register(
                ModConfiguredFeatures.STONE_BERYL_CONFIGURED,
                new ConfiguredFeature<>(
                        ModFeatures.STONE_BERYL.get(),
                        NoneFeatureConfiguration.INSTANCE
                )
        );

        context.register(
                ModConfiguredFeatures.GRANITE_BERYL_CONFIGURED,
                new ConfiguredFeature<>(
                        ModFeatures.GRANITE_BERYL.get(),
                        NoneFeatureConfiguration.INSTANCE
                )
        );

        context.register(
                ModConfiguredFeatures.ALL_TURQUOISE_CONFIGURED,
                new ConfiguredFeature<>(
                        ModFeatures.TURQUOISE.get(),
                        NoneFeatureConfiguration.INSTANCE
                )
        );

        context.register(
                ModConfiguredFeatures.ALL_OLIVINE_CONFIGURED,
                new ConfiguredFeature<>(
                        ModFeatures.OLIVINE_CONFIGURED.get(),
                        NoneFeatureConfiguration.INSTANCE
                )
        );

    }

    public static void bootstrapPlaced(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configured =
                context.lookup(Registries.CONFIGURED_FEATURE);

        // all polyps use the same feature
        context.register(
                ModPlacedFeature.CORAL_POLYP,
                new PlacedFeature(
                        configured.getOrThrow(ModConfiguredFeatures.ALL_CORAL_POLYP_CONFIGURED),
                        List.of(
                                CountPlacement.of(8),
                                InSquarePlacement.spread(),
                                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR)
                        )
                )
        );

        // ores
        ModOreRegistry.allFeatures().forEach(feat ->
            context.register(
                    feat.placedFeature(),
                    new PlacedFeature(
                            configured.getOrThrow(feat.configuredFeature()),
                            feat.placementModifiers()
                    )
            )
        );
    }

}

