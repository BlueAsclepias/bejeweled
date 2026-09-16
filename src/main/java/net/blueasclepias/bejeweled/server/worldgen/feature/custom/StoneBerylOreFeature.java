package net.blueasclepias.bejeweled.server.worldgen.feature.custom;

import net.blueasclepias.bejeweled.common.data.ore.defaults.OreDefinitions;
import net.blueasclepias.bejeweled.common.data.ore.defaults.OreVariants;
import net.blueasclepias.bejeweled.common.data.ore.registry.OreFeatureRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

/**
 * Places the stone aquamarine ore variant in regular stone only when the block is not part of a cave-exposed granite
 * contact.
 * A candidate is rejected if it touches underground air that cannot see the sky and also has at least one granite
 * neighbor, keeping this variant out of exposed granite seams.
 */
public class StoneBerylOreFeature extends Feature<NoneFeatureConfiguration> {
    public StoneBerylOreFeature()  {
        super(NoneFeatureConfiguration.CODEC);
    }

    /**
     * Attempts to replace a stone-replaceable block with the stone aquamarine variant unless it sits on an exposed
     * underground granite edge.
     */
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        return placeFeature(ctx.level(), ctx.origin());
    }

    /**
     * Applies the full stone-beryl placement rule to the origin block.
     */
    private static boolean placeFeature(WorldGenLevel level, BlockPos origin) {
        BlockState current = level.getBlockState(origin);
        if (!current.is(BlockTags.STONE_ORE_REPLACEABLES)) return false;
        if (isInCaveNextToGranite(level, origin)) return false;

        Block block = OreFeatureRegistry
                .getBlock(OreDefinitions.AQUAMARINE, OreVariants.STONE)
                .orElseThrow();

        level.setBlock(
                origin,
                block.defaultBlockState(),
                2
        );

        return true;
    }

    /**
     * Detects the disallowed case where the origin borders both underground cave air and at least one granite block.
     */
    private static boolean isInCaveNextToGranite(WorldGenLevel level, BlockPos origin) {
        boolean isExposedInCave = false;
        boolean hasGraniteNeighbor = false;

        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = origin.relative(dir);
            BlockState neighbor = level.getBlockState(neighborPos);

            if (neighbor.isAir() && !level.canSeeSky(neighborPos)) {
                isExposedInCave = true;
            }

            if(neighbor.is(Blocks.GRANITE)){
                hasGraniteNeighbor = true;
            }
        }

        return isExposedInCave && hasGraniteNeighbor;
    }
}
