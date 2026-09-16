package net.blueasclepias.bejeweled.common.block;

import net.blueasclepias.bejeweled.common.block.entity.GemCuttingTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

/**
 * Placed workstation block for the gem-cutting gameplay loop.
 * It creates the matching block entity and, when used on the server, opens the gem cutting menu by passing that
 * block entity to {@code NetworkHooks.openScreen} as the {@link net.minecraft.world.MenuProvider}.
 * This keeps the menu tied to the exact table position and shared inventory instance.
 */
public class GemCuttingTableBlock extends BaseEntityBlock {

    public GemCuttingTableBlock(Properties properties) {
        super(properties);
    }

    // Forge, please serialize and send the blockpos to the client so it can open the menu
    /**
     * Opens the gem cutting table menu from the server side using the block entity at this position.
     * Returning sided success lets the interaction feel immediate on the client while leaving menu creation
     * authoritative on the server.
     */
    @Override
    public @NotNull InteractionResult use(
            @NotNull BlockState state,
            Level level,
            @NotNull BlockPos pos,
            @NotNull Player player,
            @NotNull InteractionHand hand,
            @NotNull BlockHitResult hit
    ) {
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof GemCuttingTableBlockEntity workstation) {
                NetworkHooks.openScreen(
                        (ServerPlayer) player,
                        workstation,
                        pos
                );
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new GemCuttingTableBlockEntity(pos, state);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

}
