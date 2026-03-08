package net.blueasclepias.bejeweled.common.network;

import net.blueasclepias.bejeweled.common.network.packet.ProcessGemCuttingPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class ModNetwork {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL =
            NetworkRegistry.newSimpleChannel(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "main"),
                    () -> PROTOCOL_VERSION,
                    PROTOCOL_VERSION::equals,
                    PROTOCOL_VERSION::equals
            );

    private static int packetId = 0;

    public static void register() {
        CHANNEL.registerMessage(
                packetId++,
                ProcessGemCuttingPacket.class,
                ProcessGemCuttingPacket::encode,
                ProcessGemCuttingPacket::decode,
                ProcessGemCuttingPacket::handle
        );
    }
}
