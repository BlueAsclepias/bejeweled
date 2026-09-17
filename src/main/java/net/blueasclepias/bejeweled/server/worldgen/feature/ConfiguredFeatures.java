package net.blueasclepias.bejeweled.server.worldgen.feature;

import net.blueasclepias.bejeweled.common.data.ore.definition.OreEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Registers Configured Features for the mod.
 */
public class ConfiguredFeatures {

    // ===== Specific Configured Features =====
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALL_CORAL_POLYP_CONFIGURED = create("all_coral_polyp");

    public static @NotNull ResourceKey<ConfiguredFeature<?,?>> create(String id) {
        return ResourceKey.create(
                Registries.CONFIGURED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, id)
        );
    }

    @Contract("_, _ -> new")
    public static @NotNull ConfiguredFeature<OreConfiguration, Feature<OreConfiguration>> createGenericOreConfigFeat(@NotNull OreEntry entry, @NotNull Block block) {
        return new ConfiguredFeature<>(
                Feature.ORE,
                new OreConfiguration(
                        List.of(
                                OreConfiguration.target(
                                        entry.variant().ruleTest(),
                                        block.defaultBlockState()
                                )
                        ),
                        entry.size()
                )
        );
    }
}
