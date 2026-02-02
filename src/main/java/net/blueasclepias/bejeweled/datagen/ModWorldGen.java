package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.registry.ModFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

// TODO: Refactor Ore Generation to use this
public class ModWorldGen {

    public static final ResourceKey<ConfiguredFeature<?, ?>> CORAL_POLYP_CONFIGURED =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    fromNamespaceAndPath(MOD_ID, "coral_polyp")
            );

    public static final ResourceKey<PlacedFeature> CORAL_POLYP_PLACED =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    fromNamespaceAndPath(MOD_ID, "coral_polyp")
            );

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        context.register(
                CORAL_POLYP_CONFIGURED,
                new ConfiguredFeature<>(
                        ModFeatures.CORAL_POLYP.get(),
                        NoneFeatureConfiguration.INSTANCE
                )
        );
    }

    public static void bootstrapPlaced(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configured =
                context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(
                CORAL_POLYP_PLACED,
                new PlacedFeature(
                        configured.getOrThrow(CORAL_POLYP_CONFIGURED),
                        List.of(
                                CountPlacement.of(8),
                                InSquarePlacement.spread(),
                                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR)
                        )
                )
        );
    }
}

