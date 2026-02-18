package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.registry.ModBlocks;
import net.blueasclepias.bejeweled.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Generates Block Tags for the mod.
 */
public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup, MOD_ID, null);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        // All ores require pickaxe
        TagAppender<Block> pickaxe = tag(BlockTags.MINEABLE_WITH_PICKAXE);
        TagAppender<Block> iron = tag(BlockTags.NEEDS_IRON_TOOL);
        TagAppender<Block> gemOres = tag(ModTags.Blocks.GEM_ORES);
        TagAppender<Block> forgeOre = tag(ModTags.Blocks.FORGE_GENERIC_ORES);
        TagAppender<Block> crossOre = tag(ModTags.Blocks.CROSS_GENERIC_ORES);

        ModBlocks.oreBlocks().forEach(block -> {
            pickaxe.addOptional(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));
            iron.addOptional(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));
            gemOres.addOptional(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));
            forgeOre.addOptional(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));
            crossOre.addOptional(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));
        });

        TagAppender<Block> storage = tag(ModTags.Blocks.STORAGE_BLOCKS);
        ModBlocks.storageBlocks().forEach(block ->
                storage.addOptional(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)))
        );

        TagAppender<Block> polyps = tag(ModTags.Blocks.CORAL_POLYPS);
        ModBlocks.coralPolypBlocks().forEach(block -> {
            polyps.addOptional(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));
        });
    }
}

