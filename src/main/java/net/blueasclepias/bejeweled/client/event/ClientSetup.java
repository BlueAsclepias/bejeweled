package net.blueasclepias.bejeweled.client.event;

import net.blueasclepias.bejeweled.common.item.model.DynamicItemModelHandler;
import net.blueasclepias.bejeweled.common.registry.ModItems;
import net.blueasclepias.bejeweled.common.registry.ModMenus;
import net.blueasclepias.bejeweled.common.screen.GemCuttingTableScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

@Mod.EventBusSubscriber(
        modid = MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(
                    ModMenus.GEM_CUTTING_TABLE.get(),
                    GemCuttingTableScreen::new
            );
        });

        event.enqueueWork(() -> {
            Minecraft.getInstance().getItemColors().register(
                    DynamicItemModelHandler::tintGemLayer,
                    ModItems.GEM_ITEM.get()
            );
        });

        event.enqueueWork(() -> {
            DynamicItemModelHandler.registerGemPredicate(ModItems.GEM_ITEM.get());
        });

        event.enqueueWork(() -> {
            DynamicItemModelHandler.registerMaterialPredicate(ModItems.SOCKETED_RING.get());
            DynamicItemModelHandler.registerMaterialPredicate(ModItems.SOCKETED_AMULET.get());
            DynamicItemModelHandler.registerMaterialPredicate(ModItems.SOCKETED_BRACELET.get());
            DynamicItemModelHandler.registerMaterialPredicate(ModItems.SOCKETED_CIRCLET.get());
        });

        event.enqueueWork(() -> {
            Minecraft.getInstance().getItemColors().register(
                    DynamicItemModelHandler::tintSocketLayer,
                    ModItems.SOCKETED_RING.get(),
                    ModItems.SOCKETED_AMULET.get(),
                    ModItems.SOCKETED_BRACELET.get(),
                    ModItems.SOCKETED_CIRCLET.get()
            );
        });
    }


}
