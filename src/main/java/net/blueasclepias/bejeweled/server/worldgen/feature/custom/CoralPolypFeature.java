package net.blueasclepias.bejeweled.server.worldgen.feature.custom;

import net.blueasclepias.bejeweled.common.block.CoralPolypBlock;
import net.blueasclepias.bejeweled.common.data.coral.registry.CoralPolypRegistry;
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
import java.util.Objects;

/**
 * Searches a small randomized neighborhood for an existing coral block, resolves the matching
 * {@link CoralPolypBlock} variant, and then tries to attach it to a horizontal water space beside that anchor.
 * Successful placements preserve facing and waterlogging so generated polyps appear as submerged growths attached to
 * the source coral.
 */
public class CoralPolypFeature extends Feature<NoneFeatureConfiguration> {
    public CoralPolypFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    /**
     * Finds a nearby coral block, looks up its matching polyp variant, and tries to attach that polyp to adjacent
     * water.
     */
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        RandomSource random = ctx.random();
        BlockPos origin = ctx.origin();

        BlockPos anchorPos = findCoralAnchor(level, random, origin);
        if (anchorPos == null) return false;

        Block anchorBlock = level.getBlockState(anchorPos).getBlock();
        CoralPolypBlock polypVariant = Objects.requireNonNull(CoralPolypRegistry.findCoralPolyp(anchorBlock));

        return placeFeature(level, random, anchorPos, polypVariant);
    }

    /**
     * Performs up to eight random samples in a 7x5x7 box centered on the origin and returns the first coral block
     * found.
     */
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

    /**
     * Checks horizontal faces around the anchor for water and, on a 70% roll, places a facing and waterlogged polyp
     * in the first eligible spot.
     */
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
