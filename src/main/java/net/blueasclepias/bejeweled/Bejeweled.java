package net.blueasclepias.bejeweled;

import com.mojang.logging.LogUtils;
import net.blueasclepias.bejeweled.registry.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/**
 * Main mod class
 */
@Mod(Bejeweled.MOD_ID)
public class Bejeweled {
    public static final String MOD_ID = "bejeweled";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Bejeweled(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        LOGGER.info("Bejeweled is registering items...");
        ModItems.register(modEventBus);

        LOGGER.info("Bejeweled is registering blocks...");
        ModBlocks.register(modEventBus);

        LOGGER.info("Bejeweled is registering baseBlock entities...");
        ModBlockEntities.register(modEventBus);

        LOGGER.info("Bejeweled is registering menus...");
        ModMenus.register(modEventBus);

        // TODO should this run in common setup?
        LOGGER.info("Bejeweled is registering loot modifiers...");
        ModLootModifiers.register(modEventBus);

        LOGGER.info("Bejeweled is registering features...");
        ModFeatures.register(modEventBus);

        // TODO should this run in common setup?
        LOGGER.info("Bejeweled is registering recipes...");
        ModRecipes.register(modEventBus);

        LOGGER.info("Bejeweled is registering network channels...");
        ModNetwork.register();

        LOGGER.info("Bejeweled is registering creative tab...");
        ModCreativeTabs.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
