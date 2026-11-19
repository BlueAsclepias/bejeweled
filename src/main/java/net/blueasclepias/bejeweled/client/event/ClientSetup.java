package net.blueasclepias.bejeweled.client.event;

import net.blueasclepias.bejeweled.common.item.model.DynamicItemModelHandler;
import net.blueasclepias.bejeweled.common.registry.ModItems;
import net.blueasclepias.bejeweled.common.registry.ModMenus;
import net.blueasclepias.bejeweled.common.screen.GemCuttingTableScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Registers client screens, colors, and item model predicates.
 */
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
            ModItems.SOCKETED_JEWELRY_ITEMS.values().forEach(item ->
                    DynamicItemModelHandler.registerMaterialPredicate(item.get())
            );
        });

        event.enqueueWork(() -> {
            Minecraft.getInstance().getItemColors().register(
                    DynamicItemModelHandler::tintSocketLayer,
                    ModItems.SOCKETED_JEWELRY_ITEMS.values().stream()
                            .map(item -> (Item) item.get())
                            .toArray(Item[]::new)
            );
        });
    }


}
