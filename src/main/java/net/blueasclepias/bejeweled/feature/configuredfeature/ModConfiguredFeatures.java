package net.blueasclepias.bejeweled.feature.configuredfeature;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.ArrayList;
import java.util.List;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

/**
 * Registers Configured Features for the mod.
 */
public class ModConfiguredFeatures {

    public static final List<ResourceKey<ConfiguredFeature<?, ?>>> ALL = new ArrayList<>();

    // ===== Specific Configured Features =====
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALL_CORAL_POLYP_CONFIGURED = create("coral_polyp");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_BERYL_CONFIGURED = create("stone_beryl");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRANITE_BERYL_CONFIGURED = create("granite_beryl");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALL_TURQUOISE_CONFIGURED = create("turquoise");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALL_OLIVINE_CONFIGURED = create("olivine");

    public static ResourceKey<ConfiguredFeature<?,?>> create(String s) {
        ResourceKey<ConfiguredFeature<?, ?>> key = ResourceKey.create(
                Registries.CONFIGURED_FEATURE,
                fromNamespaceAndPath(MOD_ID, s)
        );
        ALL.add(key);
        return key;
    }
}
