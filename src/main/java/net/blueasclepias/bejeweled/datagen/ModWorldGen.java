package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.feature.configuredfeature.ModConfiguredFeatures;
import net.blueasclepias.bejeweled.feature.placedfeature.ModPlacedFeature;
import net.blueasclepias.bejeweled.oredata.OreTypes;
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
public class ModWorldGen {

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        context.register(
                ModConfiguredFeatures.CORAL_POLYPS_CONFIGURED,
                new ConfiguredFeature<>(
                        ModFeatures.CORAL_POLYP.get(),
                        NoneFeatureConfiguration.INSTANCE
                )
        );
        context.register(
                ModConfiguredFeatures.GEM_ORES_CONFIGURED,
                new ConfiguredFeature<>(
                        ModFeatures.BASIC_GEM_ORE.get(),
                        NoneFeatureConfiguration.INSTANCE
                )
        );
    }

    public static void bootstrapPlaced(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configured =
                context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(
                ModPlacedFeature.ALL.get("coral_polyp"),
                new PlacedFeature(
                        configured.getOrThrow(ModConfiguredFeatures.CORAL_POLYPS_CONFIGURED),
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
                        feature.biomeFeature().feature(),
                        new PlacedFeature(
                                configured.getOrThrow(ModConfiguredFeatures.GEM_ORES_CONFIGURED),
                                feature.placementModifiers()
                        )
                );
            })
        );
    }

}

