package net.blueasclepias.bejeweled.datagen.bootstrap;

import net.blueasclepias.bejeweled.datagen.provider.block.BlockStateProvider;
import net.blueasclepias.bejeweled.datagen.provider.gem.GemDefinitionProvider;
import net.blueasclepias.bejeweled.datagen.provider.item.ItemModelProvider;
import net.blueasclepias.bejeweled.datagen.provider.lang.LangProvider;
import net.blueasclepias.bejeweled.datagen.provider.loot.LootModifierProvider;
import net.blueasclepias.bejeweled.datagen.provider.loot.LootTableProvider;
import net.blueasclepias.bejeweled.datagen.provider.recipe.RecipeProvider;
import net.blueasclepias.bejeweled.datagen.provider.tag.BlockTagsProvider;
import net.blueasclepias.bejeweled.datagen.provider.tag.ItemTagsProvider;
import net.blueasclepias.bejeweled.datagen.provider.worldgen.BiomeModifierProvider;
import net.blueasclepias.bejeweled.datagen.provider.worldgen.DatapackEntries;
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
            gen.addProvider(true, new ItemModelProvider(packOutput, existingFileHelper));
            gen.addProvider(true, new LangProvider(packOutput));
            gen.addProvider(true, new BlockStateProvider(packOutput, existingFileHelper));
        }

        if (event.includeServer()) {
            BlockTagsProvider blockTagsProvider = new BlockTagsProvider(
                    packOutput,
                    event.getLookupProvider()
            );

            gen.addProvider(event.includeServer(),
                    new GemDefinitionProvider(packOutput));

            gen.addProvider(true, blockTagsProvider);

            gen.addProvider(true, new ItemTagsProvider(
                    packOutput,
                    lookupProvider,
                    blockTagsProvider.contentsGetter(),
                    existingFileHelper)
            );

            gen.addProvider(true, new DatapackEntries(packOutput, event.getLookupProvider()));
            gen.addProvider(true, new BiomeModifierProvider(packOutput));

            gen.addProvider(true, new LootTableProvider(packOutput));
            gen.addProvider(true, new LootModifierProvider(packOutput));

            gen.addProvider(true, new RecipeProvider(packOutput));
        }
    }
}