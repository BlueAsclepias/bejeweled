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

    // A single configured feature for all Gems
    public static final ResourceKey<ConfiguredFeature<?, ?>> GEM_ORES_CONFIGURED =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    fromNamespaceAndPath(MOD_ID, "gem_ores")
            );

    // A single configured feature for all Coral Polyps
    public static final ResourceKey<ConfiguredFeature<?, ?>> CORAL_POLYPS_CONFIGURED =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    fromNamespaceAndPath(MOD_ID, "coral_polyp")
            );

}
