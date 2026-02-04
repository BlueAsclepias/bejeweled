package net.blueasclepias.bejeweled;

import com.mojang.logging.LogUtils;
import net.blueasclepias.bejeweled.modifier.ModLootModifiers;
import net.blueasclepias.bejeweled.registry.ModBlocks;
import net.blueasclepias.bejeweled.registry.ModCreativeTabs;
import net.blueasclepias.bejeweled.registry.ModFeatures;
import net.blueasclepias.bejeweled.registry.ModItems;
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
    private static final Logger LOGGER = LogUtils.getLogger();

    public Bejeweled(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        LOGGER.info("Registering Bejeweled items...");
        ModItems.register(modEventBus);
        LOGGER.info("Registering Bejeweled blocks...");
        ModBlocks.register(modEventBus);
        LOGGER.info("Registering Bejeweled creative tab...");
        ModCreativeTabs.register(modEventBus);
        LOGGER.info("Registering Bejeweled loot modifiers...");
        ModLootModifiers.register(modEventBus);
        LOGGER.info("Registering Features");
        ModFeatures.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

}
