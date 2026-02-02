package net.blueasclepias.bejeweled.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CoralPolypBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final Block supportBlock;

    // Attached to NORTH wall (positive Z)
    private static final VoxelShape NORTH_SHAPE =
            Block.box(5, 5, 10, 11, 11, 16);
    // Attached to SOUTH wall (negative Z)
    private static final VoxelShape SOUTH_SHAPE =
            Block.box(5, 5, 0, 11, 11, 6);
    // Attached to WEST wall (positive X)
    private static final VoxelShape WEST_SHAPE =
            Block.box(10, 5, 5, 16, 11, 11);
    // Attached to EAST wall (negative X)
    private static final VoxelShape EAST_SHAPE =
            Block.box(0, 5, 5, 6, 11, 11);

    public CoralPolypBlock(Block supportBlock, Properties properties) {
        super(properties);
        this.supportBlock = supportBlock;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction face = context.getClickedFace();
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();

        if (!face.getAxis().isHorizontal() || !canSurvive(level, pos, face))
            return null;

        return this.defaultBlockState()
                .setValue(FACING, face)
                .setValue(WATERLOGGED, level.getFluidState(pos).is(FluidTags.WATER));
    }

    @Override
    public BlockState updateShape(
            BlockState state,
            Direction direction,
            BlockState neighborState,
            LevelAccessor level,
            BlockPos pos,
            BlockPos neighborPos
    ) {
        Direction facing = state.getValue(FACING);

        if (direction == facing.getOpposite() && !canSurvive(level, pos, facing)) {
            return Blocks.AIR.defaultBlockState();
        }

        return state;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return switch (state.getValue(FACING).getOpposite()) {
            case NORTH -> SOUTH_SHAPE;
            case SOUTH -> NORTH_SHAPE;
            case WEST  -> EAST_SHAPE;
            case EAST  -> WEST_SHAPE;
            default    -> NORTH_SHAPE;
        };
    }


    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED)
                ? Fluids.WATER.getSource(false)
                : super.getFluidState(state);
    }

    private boolean canSurvive(LevelAccessor level, BlockPos pos, Direction facing) {
        BlockPos supportPos = pos.relative(facing.getOpposite());
        BlockState support = level.getBlockState(supportPos);

        return support.isFaceSturdy(level, supportPos, facing)
                && support.is(supportBlock);
    }

}