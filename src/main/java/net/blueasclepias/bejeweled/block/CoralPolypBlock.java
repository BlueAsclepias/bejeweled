package net.blueasclepias.bejeweled.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CoralPolypBlock extends DirectionalBlock {

    public CoralPolypBlock(Properties props) {
        super(props);
    }

    // TODO: has to be waterlogged, has growth, restricted to warm sea biomes, only spawns in red and orange coral blocks
    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos attached = pos.relative(state.getValue(FACING).getOpposite());
        return level.getBlockState(attached).isSolid();
    }
}

