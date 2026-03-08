package net.blueasclepias.bejeweled.server.worldgen.feature.custom;

import net.blueasclepias.bejeweled.common.data.ore.defaults.OreDefinitions;
import net.blueasclepias.bejeweled.common.data.ore.defaults.OreVariants;
import net.blueasclepias.bejeweled.common.data.ore.registry.OreFeatureRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class TurquoiseOreFeature extends Feature<NoneFeatureConfiguration> {
    public TurquoiseOreFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        return placeFeature(ctx.level(), ctx.origin());
    }

    private static boolean placeFeature(WorldGenLevel level, BlockPos origin) {
        BlockState current = level.getBlockState(origin);
        if (!current.is(BlockTags.STONE_ORE_REPLACEABLES)) return false;
        if (!isNearStoneCopperOre(level, origin) || !isNearWater(origin, level)) return false;

        Block block = OreFeatureRegistry
                .getBlock(OreDefinitions.TURQUOISE, OreVariants.STONE)
                .orElseThrow();

        level.setBlock(
                origin,
                block.defaultBlockState(),
                2
        );
        return true;
    }

    private static boolean isNearStoneCopperOre(WorldGenLevel level, BlockPos origin) {
        boolean isNearStoneCopperOre = false;

        for (Direction dir : Direction.values()) {
            BlockState neighbor = level.getBlockState(origin.relative(dir));
            if (neighbor.is(Blocks.COPPER_ORE)) {
                isNearStoneCopperOre = true;
                break;
            }
        }

        return isNearStoneCopperOre;
    }

    private static boolean isNearWater(BlockPos origin, WorldGenLevel level) {
        boolean isNearWater = false;
        BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();

        int radius = 2;

        for (int dx = -radius; dx <= radius && !isNearWater; dx++) {
            for (int dy = -radius; dy <= radius && !isNearWater; dy++) {
                for (int dz = -radius; dz <= radius && !isNearWater; dz++) {
                    cursor.set(
                            origin.getX() + dx,
                            origin.getY() + dy,
                            origin.getZ() + dz
                    );

                    BlockState state = level.getBlockState(cursor);
                    if (state.getFluidState().is(FluidTags.WATER)) {
                        isNearWater = true;
                    }
                }
            }
        }

        return isNearWater;
    }
}
