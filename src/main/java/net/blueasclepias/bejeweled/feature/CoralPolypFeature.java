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
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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

        BlockPos anchor = findCoralAnchor(level, random, origin);
        if (anchor == null) {
            return false;
        }

        Block coralType = level.getBlockState(anchor).getBlock();
        Block polypVariant = getPolypVariantFor(coralType);

        return placeFeature(level, random, anchor, polypVariant);
    }

    private Block getPolypVariantFor(Block coralBlock) {
        String name = ForgeRegistries.BLOCKS.getKey(coralBlock).getPath() + "_polyp";

        return ModBlocks.CORAL_POLYP_BLOCKS.stream()
                .filter(cpb -> cpb.getId().getPath().equals(name))
                .findFirst()
                .map(RegistryObject::get)
                .orElse(null);
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

            if (state.is(BlockTags.CORAL_BLOCKS)) {
                return candidate;
            }
        }
        return null;
    }

    protected boolean placeFeature(LevelAccessor level, RandomSource random, BlockPos pos, Block block) {
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos supportPos = pos.relative(direction);
            BlockState supportState = level.getBlockState(supportPos);
            if (!supportState.is(Blocks.WATER)) {
                continue;
            }
            if (random.nextFloat() < 0.7F) {
                BlockPos relativeBlockPos = pos.relative(direction);
                if (level.getBlockState(relativeBlockPos).is(Blocks.WATER)) {
                    String name = ForgeRegistries.BLOCKS.getKey(block).getPath();
                    ModBlocks.CORAL_POLYP_BLOCKS.stream()
                            .filter(cpb -> cpb.getId().getPath().equals(name))
                            .findFirst().ifPresent(b -> {
                                BlockState defaultBlockState = b.get().defaultBlockState();
                                if (defaultBlockState.hasProperty(CoralPolypBlock.FACING)) {
                                    defaultBlockState = defaultBlockState.setValue(CoralPolypBlock.FACING, direction);
                                }
                                if (defaultBlockState.hasProperty(CoralPolypBlock.WATERLOGGED)) {
                                    defaultBlockState = defaultBlockState.setValue(CoralPolypBlock.WATERLOGGED, true);
                                }
                                level.setBlock(relativeBlockPos, defaultBlockState, 2);
                            });
                    return true;
                }
            }
        }
        return false;
    }
}
