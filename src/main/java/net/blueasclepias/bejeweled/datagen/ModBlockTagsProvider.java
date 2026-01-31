package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.Bejeweled;
import net.blueasclepias.bejeweled.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup, Bejeweled.MOD_ID, null);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        // All ores require pickaxe
        TagAppender<Block> pickaxe = tag(BlockTags.MINEABLE_WITH_PICKAXE);
        TagAppender<Block> iron = tag(BlockTags.NEEDS_IRON_TOOL);

        ModBlocks.ORE_BLOCKS.forEach((type, entry) -> {
            entry.forEach( (base, block) -> {
                pickaxe.addOptional(ForgeRegistries.BLOCKS.getKey(block.get()));
                iron.addOptional(ForgeRegistries.BLOCKS.getKey(block.get()));
            });
        });
    }
}

