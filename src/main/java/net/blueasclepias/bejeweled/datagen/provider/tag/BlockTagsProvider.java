package net.blueasclepias.bejeweled.datagen.provider.tag;

import net.blueasclepias.bejeweled.common.data.coral.registry.CoralPolypRegistry;
import net.blueasclepias.bejeweled.common.data.ore.registry.OreFeatureRegistry;
import net.blueasclepias.bejeweled.common.data.storage.registry.StorageBlockRegistry;
import net.blueasclepias.bejeweled.common.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Generates Block Tags for the mod.
 */
public class BlockTagsProvider extends net.minecraftforge.common.data.BlockTagsProvider {

    public BlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
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

        OreFeatureRegistry.all().forEach((id, feat) -> {
            pickaxe.addOptional(id);
            iron.addOptional(id);
            gemOres.addOptional(id);
            forgeOre.addOptional(id);
            crossOre.addOptional(id);
        });

        TagAppender<Block> storage = tag(ModTags.Blocks.STORAGE_BLOCKS);
        StorageBlockRegistry.all().forEach((id, block) ->
                storage.addOptional(id)
        );

        TagAppender<Block> polyps = tag(ModTags.Blocks.CORAL_POLYPS);
        CoralPolypRegistry.all().forEach((id, block) ->
                polyps.addOptional(id)
        );
    }
}

