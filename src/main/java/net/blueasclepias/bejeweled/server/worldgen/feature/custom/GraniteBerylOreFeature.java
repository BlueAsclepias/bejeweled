package net.blueasclepias.bejeweled.server.worldgen.feature.custom;

import net.blueasclepias.bejeweled.common.data.ore.defaults.OreDefinitions;
import net.blueasclepias.bejeweled.common.data.ore.defaults.OreVariants;
import net.blueasclepias.bejeweled.common.data.ore.registry.OreFeatureRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

/**
 * Places the granite aquamarine ore variant only inside thicker granite masses.
 * The origin must already be granite, and cave-exposed candidates with fewer than three adjacent granite blocks are
 * rejected so vein edges along open caves stay clear.
 */
public class GraniteBerylOreFeature extends Feature<NoneFeatureConfiguration> {
    public GraniteBerylOreFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    /**
     * Attempts to replace a granite block with the granite aquamarine variant when it is not part of a thin
     * cave-exposed granite edge.
     */
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        return placeFeature(ctx.level(), ctx.origin());
    }

    /**
     * Applies the full granite-beryl placement rule to the origin block.
     */
    private static boolean placeFeature(WorldGenLevel level, BlockPos origin) {
        BlockState current = level.getBlockState(origin);
        if (!current.is(Blocks.GRANITE)) return false;

        if (isInCaveGranitePatch(level, origin)) return false;

        Block block = OreFeatureRegistry
                .getBlock(OreDefinitions.AQUAMARINE, OreVariants.GRANITE)
                .orElseThrow();

        level.setBlock(
                origin,
                block.defaultBlockState(),
                2
        );

        return true;
    }

    /**
     * Treats a candidate as a cave-edge granite patch when it borders underground air and has fewer than three
     * granite neighbors.
     */
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
