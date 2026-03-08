package net.blueasclepias.bejeweled.common.network.packet;

import net.blueasclepias.bejeweled.common.container.GemCuttingTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record ProcessGemCuttingPacket(BlockPos pos) {

    public static void encode(ProcessGemCuttingPacket msg, FriendlyByteBuf buf) {
        buf.writeBlockPos(msg.pos);
    }

    public static ProcessGemCuttingPacket decode(FriendlyByteBuf buf) {
        return new ProcessGemCuttingPacket(buf.readBlockPos());
    }

    public static void handle(ProcessGemCuttingPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            if (player.containerMenu instanceof GemCuttingTableMenu menu) {
                menu.tryProcess();
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
