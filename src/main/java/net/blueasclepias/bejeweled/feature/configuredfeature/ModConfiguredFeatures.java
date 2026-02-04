package net.blueasclepias.bejeweled.feature.configuredfeature;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

/**
 * Registers Configured Features for the mod.
 */
public class ModConfiguredFeatures {

    // === generic ===
    public static final ResourceKey<ConfiguredFeature<?, ?>> BASIC_GEM_CONFIGURED =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    fromNamespaceAndPath(MOD_ID, "basic_gem")
            );

    public static final ResourceKey<ConfiguredFeature<?, ?>> CORAL_POLYP_CONFIGURED =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    fromNamespaceAndPath(MOD_ID, "coral_polyp")
            );

    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_BERYL_CONFIGURED =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    fromNamespaceAndPath(MOD_ID, "stone_beryl")
            );

    public static final ResourceKey<ConfiguredFeature<?, ?>> GRANITE_BERYL_CONFIGURED =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    fromNamespaceAndPath(MOD_ID, "granite_beryl")
            );

    public static final ResourceKey<ConfiguredFeature<?, ?>> TURQUOISE_CONFIGURED =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    fromNamespaceAndPath(MOD_ID, "turquoise")
            );

    public static final ResourceKey<ConfiguredFeature<?, ?>> OLIVINE_CONFIGURED =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    fromNamespaceAndPath(MOD_ID, "olivine")
            );
}
