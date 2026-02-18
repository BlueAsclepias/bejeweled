package net.blueasclepias.bejeweled.feature.configuredfeature;

import net.blueasclepias.bejeweled.material.definition.ore.OreFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.List;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

/**
 * Registers Configured Features for the mod.
 */
public class ModConfiguredFeatures {

    // ===== Specific Configured Features =====
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALL_CORAL_POLYP_CONFIGURED = create("coral_polyp");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_BERYL_CONFIGURED = create("stone_beryl");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRANITE_BERYL_CONFIGURED = create("granite_beryl");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALL_TURQUOISE_CONFIGURED = create("turquoise");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALL_OLIVINE_CONFIGURED = create("olivine");

    public static ResourceKey<ConfiguredFeature<?,?>> create(String id) {
        return ResourceKey.create(
                Registries.CONFIGURED_FEATURE,
                fromNamespaceAndPath(MOD_ID, id)
        );
    }

    public static ConfiguredFeature<OreConfiguration, Feature<OreConfiguration>> createGenericOreConfigFeat(OreFeature feat, Block block) {
        return new ConfiguredFeature<>(
                Feature.ORE,
                new OreConfiguration(
                        List.of(
                                OreConfiguration.target(
                                        feat.variant().ruleTest(),
                                        block.defaultBlockState()
                                )
                        ),
                        feat.size()
                )
        );
    }
}
