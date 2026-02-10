package net.blueasclepias.bejeweled;

import com.mojang.logging.LogUtils;
import net.blueasclepias.bejeweled.registry.*;
import net.blueasclepias.bejeweled.screen.GemCuttingTableScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/**
 * Main mod class
 */
@Mod(Bejeweled.MOD_ID)
public class Bejeweled {
    public static final String MOD_ID = "bejeweled";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Bejeweled(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        LOGGER.info("Bejeweled is registering items...");
        ModItems.register(modEventBus);

        LOGGER.info("Bejeweled is registering blocks...");
        ModBlocks.register(modEventBus);

        LOGGER.info("Bejeweled is registering block entities...");
        ModBlockEntities.register(modEventBus);

        LOGGER.info("Bejeweled is registering menus...");
        ModMenus.register(modEventBus);

        LOGGER.info("Bejeweled is registering loot modifiers...");
        ModLootModifiers.register(modEventBus);

        LOGGER.info("Bejeweled is registering features...");
        ModFeatures.register(modEventBus);

        LOGGER.info("Bejeweled is registering recipes...");
        ModRecipes.register(modEventBus);

        LOGGER.info("Bejeweled is registering network channels...");
        ModNetwork.register();

        LOGGER.info("Bejeweled is registering creative tab...");
        ModCreativeTabs.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(
            modid = MOD_ID,
            bus = Mod.EventBusSubscriber.Bus.MOD,
            value = Dist.CLIENT
    )
    public static class ClientSetup {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                MenuScreens.register(
                        ModMenus.GEM_CUTTING_TABLE.get(),
                        GemCuttingTableScreen::new
                );
            });
        }
    }


}
