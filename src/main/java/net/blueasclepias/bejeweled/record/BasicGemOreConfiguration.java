package net.blueasclepias.bejeweled.record;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration.TargetBlockState;

import java.util.List;
import java.util.function.Predicate;

public record GemOreConfiguration(
        List<TargetBlockState> targets
) implements FeatureConfiguration {
    public static final Codec<GemOreConfiguration> CODEC =
            TargetBlockState.CODEC.listOf()
                    .fieldOf("targets")
                    .xmap(GemOreConfiguration::new, GemOreConfiguration::targets)
                    .codec();



}


