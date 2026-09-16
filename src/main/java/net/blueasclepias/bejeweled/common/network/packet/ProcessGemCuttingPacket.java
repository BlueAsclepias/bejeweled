package net.blueasclepias.bejeweled.common.network.packet;

import net.blueasclepias.bejeweled.common.block.entity.GemCuttingTableBlockEntity;
import net.blueasclepias.bejeweled.common.container.GemCuttingTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Client-to-server request sent when the gem cutting screen's process button is pressed.
 * The packet only identifies the target table position; all actual processing remains server-authoritative.
 * Handling includes menu, block-entity, and distance checks so stale or spoofed requests cannot trigger arbitrary
 * workstation actions.
 */
public record ProcessGemCuttingPacket(BlockPos pos) {

    public static void encode(ProcessGemCuttingPacket msg, FriendlyByteBuf buf) {
        buf.writeBlockPos(msg.pos);
    }

    public static ProcessGemCuttingPacket decode(FriendlyByteBuf buf) {
        return new ProcessGemCuttingPacket(buf.readBlockPos());
    }

    /**
     * Processes the request on the server thread after re-checking that the sender is still interacting with the
     * same table.
     * The packet is ignored unless the open menu matches the position, a real gem cutting table block entity exists
     * there, and the player is within the normal 8-block interaction distance.
     */
    public static void handle(ProcessGemCuttingPacket msg, Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {

            ServerPlayer player = ctx.get().getSender();
            if(player == null) return;

            if(!(player.containerMenu instanceof GemCuttingTableMenu menu))
                return;

            if(!menu.getBlockPos().equals(msg.pos()))
                return;

            BlockEntity be = player.level().getBlockEntity(msg.pos());
            if(!(be instanceof GemCuttingTableBlockEntity))
                return;

            if(player.distanceToSqr(
                    msg.pos().getX() + 0.5,
                    msg.pos().getY() + 0.5,
                    msg.pos().getZ() + 0.5
            ) > 64)
                return;

            menu.tryProcess();

        });

        ctx.get().setPacketHandled(true);
    }
}
