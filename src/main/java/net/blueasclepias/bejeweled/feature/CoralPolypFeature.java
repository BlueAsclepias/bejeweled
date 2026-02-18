package net.blueasclepias.bejeweled.feature;

import net.blueasclepias.bejeweled.block.CoralPolypBlock;
import net.blueasclepias.bejeweled.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import javax.annotation.Nullable;

/**
 * Feature that places coral polyp blocks adjacent to existing coral blocks underwater.
 */
public class CoralPolypFeature extends Feature<NoneFeatureConfiguration> {
    public CoralPolypFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        RandomSource random = ctx.random();
        BlockPos origin = ctx.origin();

        BlockPos anchorPos = findCoralAnchor(level, random, origin);
        if (anchorPos == null) return false;

        Block anchorBlock = level.getBlockState(anchorPos).getBlock();
        CoralPolypBlock polypVariant = ModBlocks.getPolypVariantFor(anchorBlock);

        return placeFeature(level, random, anchorPos, polypVariant);
    }

    @Nullable
    private BlockPos findCoralAnchor(LevelAccessor level, RandomSource random, BlockPos origin) {
        for (int i = 0; i < 8; i++) {
            BlockPos candidate = origin.offset(
                    random.nextInt(7) - 3,
                    random.nextInt(5) - 2,
                    random.nextInt(7) - 3
            );

            BlockState state = level.getBlockState(candidate);

            if (state.is(BlockTags.CORAL_BLOCKS)) return candidate;
        }
        return null;
    }

    private boolean placeFeature(LevelAccessor level, RandomSource random, BlockPos pos, CoralPolypBlock block) {
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos supportPos = pos.relative(direction);
            BlockState supportState = level.getBlockState(supportPos);
            if (!supportState.is(Blocks.WATER)) {
                continue;
            }
            if (random.nextFloat() < 0.7F && level.getBlockState(supportPos).is(Blocks.WATER)) {
                BlockState defaultBlockState = block.defaultBlockState();
                if (defaultBlockState.hasProperty(CoralPolypBlock.FACING)) {
                    defaultBlockState = defaultBlockState.setValue(CoralPolypBlock.FACING, direction);
                }
                if (defaultBlockState.hasProperty(CoralPolypBlock.WATERLOGGED)) {
                    defaultBlockState = defaultBlockState.setValue(CoralPolypBlock.WATERLOGGED, true);
                }
                level.setBlock(supportPos, defaultBlockState, 2);
                return true;
            }
        }
        return false;
    }
}
