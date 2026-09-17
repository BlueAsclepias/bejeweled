package net.blueasclepias.bejeweled.server.worldgen.feature.custom;

import net.blueasclepias.bejeweled.common.data.ore.defaults.OreDefinitions;
import net.blueasclepias.bejeweled.common.data.ore.defaults.OreVariants;
import net.blueasclepias.bejeweled.common.data.ore.registry.OreRegistry;
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

/**
 * Places the stone turquoise ore variant only in wet, copper-adjacent stone.
 * The origin must be a stone-ore-replaceable block, touch vanilla copper ore on at least one face, and sit within
 * two blocks of water anywhere in the surrounding 5x5x5 cube before it is replaced.
 */
public class TurquoiseOreFeature extends Feature<NoneFeatureConfiguration> {
    public TurquoiseOreFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    /**
     * Tries to replace the origin with stone turquoise ore when it is both face-adjacent to copper ore and within a
     * small water-filled neighborhood.
     */
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        return placeFeature(ctx.level(), ctx.origin());
    }

    /**
     * Applies the full turquoise placement test to one candidate block and swaps it for the registered stone variant
     * on success.
     */
    private static boolean placeFeature(WorldGenLevel level, BlockPos origin) {
        BlockState current = level.getBlockState(origin);
        if (!current.is(BlockTags.STONE_ORE_REPLACEABLES)) return false;
        if (!isNearStoneCopperOre(level, origin) || !isNearWater(origin, level)) return false;

        Block block = OreRegistry
                .blockFor(OreDefinitions.TURQUOISE, OreVariants.STONE)
                .orElseThrow();

        level.setBlock(
                origin,
                block.defaultBlockState(),
                2
        );
        return true;
    }

    /**
     * Returns whether any of the six orthogonal neighbors is vanilla copper ore.
     */
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

    /**
     * Scans a two-block radius cube around the origin for any water fluid state.
     */
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
