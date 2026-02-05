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

    public static final List<ResourceKey<ConfiguredFeature<?, ?>>> GENERICS = new ArrayList<>();

    // ===== Specific Configured Features =====
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALL_CORAL_POLYP_CONFIGURED = create("coral_polyp", false);
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_BERYL_CONFIGURED = create("stone_beryl", false);
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRANITE_BERYL_CONFIGURED = create("granite_beryl", false);
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALL_TURQUOISE_CONFIGURED = create("turquoise", false);
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALL_OLIVINE_CONFIGURED = create("olivine", false);

    public static ResourceKey<ConfiguredFeature<?,?>> create(String s, boolean isGeneric) {
        ResourceKey<ConfiguredFeature<?, ?>> key = ResourceKey.create(
                Registries.CONFIGURED_FEATURE,
                fromNamespaceAndPath(MOD_ID, s)
        );
        if(isGeneric) GENERICS.add(key);
        return key;
    }
}
