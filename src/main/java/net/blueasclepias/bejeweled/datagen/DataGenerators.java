package net.blueasclepias.bejeweled.datagen;

import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var packOutput = gen.getPackOutput();
        var existingFileHelper = event.getExistingFileHelper();

        if (event.includeClient()) {
            gen.addProvider(true, new ModItemModelProvider(packOutput, existingFileHelper));
            gen.addProvider(true, new ModLangProvider(packOutput));
            gen.addProvider(true, new ModBlockStateProvider(packOutput, existingFileHelper));
        }

        if (event.includeServer()) {
            gen.addProvider(true, new ModRecipeProvider(packOutput));
            gen.addProvider(true, new ModBlockTagsProvider(
                    packOutput,
                    event.getLookupProvider()
            ));
            gen.addProvider(true, new ModLootTableProvider(packOutput));
            gen.addProvider(true, new ModLootModifierProvider(packOutput));

            gen.addProvider(true, new ModDatapackEntries(packOutput, event.getLookupProvider()));
            gen.addProvider(true, new ModBiomeModifierProvider(packOutput));
        }
    }
}