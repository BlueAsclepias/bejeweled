package net.blueasclepias.bejeweled.feature;

import net.blueasclepias.bejeweled.oretype.OreBases;
import net.blueasclepias.bejeweled.oretype.OreTypes;
import net.blueasclepias.bejeweled.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class OlivineOreFeature extends Feature<NoneFeatureConfiguration> {
    public OlivineOreFeature()  {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        return placeFeature(ctx.level(), ctx.origin());
    }

    private static boolean placeFeature(WorldGenLevel level, BlockPos origin) {
        BlockState current = level.getBlockState(origin);
        if (!current.is(BlockTags.DEEPSLATE_ORE_REPLACEABLES)) return false;
        if (!isNearLava(level, origin)) return false;

        level.setBlock(
                origin,
                ModBlocks.ORE_BLOCKS
                        .get(OreTypes.OLIVINE)
                        .get(OreBases.DEEPSLATE)
                        .get()
                        .defaultBlockState(),
                2
        );

        return true;
    }

    private static boolean isNearLava(WorldGenLevel level, BlockPos origin) {
        boolean isNearLava = false;
        BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();

        int radius = 2;

        for (int dx = -radius; dx <= radius && !isNearLava; dx++) {
            for (int dy = -radius; dy <= radius && !isNearLava; dy++) {
                for (int dz = -radius; dz <= radius && !isNearLava; dz++) {
                    cursor.set(
                            origin.getX() + dx,
                            origin.getY() + dy,
                            origin.getZ() + dz
                    );

                    BlockState state = level.getBlockState(cursor);
                    if (state.getFluidState().is(FluidTags.LAVA)) {
                        isNearLava = true;
                    }
                }
            }
        }

        return isNearLava;
    }
}
