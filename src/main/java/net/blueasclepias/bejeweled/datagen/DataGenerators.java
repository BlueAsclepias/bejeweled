package net.blueasclepias.bejeweled.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        if (event.includeClient()) {
            gen.addProvider(true, new ModItemModelProvider(packOutput, existingFileHelper));
            gen.addProvider(true, new ModLangProvider(packOutput));
            gen.addProvider(true, new ModBlockStateProvider(packOutput, existingFileHelper));
        }

        if (event.includeServer()) {
            ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(
                    packOutput,
                    event.getLookupProvider()
            );

            gen.addProvider(event.includeServer(),
                    new GemDefinitionProvider(packOutput));

            gen.addProvider(true, blockTagsProvider);

            gen.addProvider(true, new ModItemTagsProvider(
                    packOutput,
                    lookupProvider,
                    blockTagsProvider.contentsGetter(),
                    existingFileHelper)
            );

            gen.addProvider(true, new ModDatapackEntries(packOutput, event.getLookupProvider()));
            gen.addProvider(true, new ModBiomeModifierProvider(packOutput));

            gen.addProvider(true, new ModLootTableProvider(packOutput));
            gen.addProvider(true, new ModLootModifierProvider(packOutput));

            gen.addProvider(true, new ModRecipeProvider(packOutput));
        }
    }
}