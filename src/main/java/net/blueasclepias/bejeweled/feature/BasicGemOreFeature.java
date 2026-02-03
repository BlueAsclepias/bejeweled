package net.blueasclepias.bejeweled.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

// TODO: THIS IS A PROOF OF CONCEPT. MAKE AN ALGORITHM FOR EACH IF NECESSARY
public class BasicOreFeature extends Feature<NoneFeatureConfiguration> {
    public BasicOreFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        RandomSource random = ctx.random();
        BlockPos origin = ctx.origin();

        // TODO: every ore uses the same feature, but different biome modifiers (is it possible?)

        return false;
    }
}
