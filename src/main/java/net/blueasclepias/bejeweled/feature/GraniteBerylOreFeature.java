package net.blueasclepias.bejeweled.feature;

import net.blueasclepias.bejeweled.data.accessor.OreAccessor;
import net.blueasclepias.bejeweled.data.instance.ore.OreDefinitions;
import net.blueasclepias.bejeweled.data.instance.ore.OreVariants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GraniteBerylOreFeature extends Feature<NoneFeatureConfiguration> {
    public GraniteBerylOreFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        return placeFeature(ctx.level(), ctx.origin());
    }

    private static boolean placeFeature(WorldGenLevel level, BlockPos origin) {
        BlockState current = level.getBlockState(origin);
        if (!current.is(Blocks.GRANITE)) return false;

        if (isInCaveGranitePatch(level, origin)) return false;

        Block block = OreAccessor
                .getBlock(OreDefinitions.AQUAMARINE, OreVariants.GRANITE)
                .orElseThrow();

        level.setBlock(
                origin,
                block.defaultBlockState(),
                2
        );

        return true;
    }

    private static boolean isInCaveGranitePatch(WorldGenLevel level, BlockPos origin) {
        boolean isExposedInCave = false;
        int graniteNeighbors = 0;

        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = origin.relative(dir);
            BlockState neighbor = level.getBlockState(neighborPos);

            if (neighbor.isAir() && !level.canSeeSky(neighborPos)) {
                isExposedInCave = true;
            }

            if (neighbor.is(Blocks.GRANITE)) {
                graniteNeighbors++;
            }
        }

        return isExposedInCave && graniteNeighbors < 3;
    }
}
