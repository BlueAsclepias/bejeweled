package net.blueasclepias.bejeweled.feature.configuredfeature;

import net.blueasclepias.bejeweled.data.definition.ore.OreFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

/**
 * Registers Configured Features for the mod.
 */
public class ModConfiguredFeatures {

    // ===== Specific Configured Features =====
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALL_CORAL_POLYP_CONFIGURED = create("all_coral_polyp");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_BERYL_CONFIGURED = create("stone_beryl_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRANITE_BERYL_CONFIGURED = create("granite_beryl_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALL_TURQUOISE_CONFIGURED = create("all_turquoise_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALL_OLIVINE_CONFIGURED = create("all_olivine_ore");

    public static @NotNull ResourceKey<ConfiguredFeature<?,?>> create(String id) {
        return ResourceKey.create(
                Registries.CONFIGURED_FEATURE,
                fromNamespaceAndPath(MOD_ID, id)
        );
    }

    @Contract("_, _ -> new")
    public static @NotNull ConfiguredFeature<OreConfiguration, Feature<OreConfiguration>> createGenericOreConfigFeat(@NotNull OreFeature feat, @NotNull Block block) {
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
