package net.blueasclepias.bejeweled.server.event;

import net.blueasclepias.bejeweled.common.data.gem.loader.GemDefinitionLoader;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

@Mod.EventBusSubscriber(
        modid = MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AddReloadListenerEventHandler {
    @SubscribeEvent
    public static void onReload(AddReloadListenerEvent event) {
        event.addListener(new GemDefinitionLoader());
    }
}
