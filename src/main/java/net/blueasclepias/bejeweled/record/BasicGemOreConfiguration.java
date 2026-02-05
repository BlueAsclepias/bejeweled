package net.blueasclepias.bejeweled.record;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration.TargetBlockState;

import java.util.List;

public record BasicGemOreConfiguration(
        List<TargetBlockState> targets
) implements FeatureConfiguration {
    public static final Codec<BasicGemOreConfiguration> CODEC =
            TargetBlockState.CODEC.listOf()
                    .fieldOf("targets")
                    .xmap(BasicGemOreConfiguration::new, BasicGemOreConfiguration::targets)
                    .codec();
}


