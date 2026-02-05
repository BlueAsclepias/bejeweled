package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.feature.configuredfeature.ModConfiguredFeatures;
import net.blueasclepias.bejeweled.feature.placedfeature.ModPlacedFeature;
import net.blueasclepias.bejeweled.oretype.OreTypes;
import net.blueasclepias.bejeweled.record.OreFeature;
import net.blueasclepias.bejeweled.registry.ModBlocks;
import net.blueasclepias.bejeweled.registry.ModFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

/**
 * Data generation for world generation features.
 */
public class ModWorldGen {

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        OreTypes.ALL.forEach(type ->{
            type.features().stream()
                    .filter(OreFeature::isGeneric)
                    .forEach(feature -> {
                        context.register(
                                feature.configuredFeature(),
                                new ConfiguredFeature<>(
                                        Feature.ORE,
                                        new OreConfiguration(
                                                List.of(
                                                        OreConfiguration.target(
                                                                feature.base().ruleTest(),
                                                                ModBlocks.ORE_BLOCKS.get(type)
                                                                        .get(feature.base())
                                                                        .get()
                                                                        .defaultBlockState()
                                                        )
                                                ),
                                                feature.size()
                                        )
                                )
                        );
                    });
        });

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

        OreTypes.ALL.forEach(type ->
            type.features().forEach(feature -> {
                context.register(
                        feature.placedFeature(),
                        new PlacedFeature(
                                configured.getOrThrow(feature.configuredFeature()),
                                feature.placementModifiers()
                        )
                );
            })
        );
    }

}

