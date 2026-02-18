package net.blueasclepias.bejeweled.event;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

@Mod.EventBusSubscriber(
        modid = MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModLifecycle {
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
       // TODO
    }
}
