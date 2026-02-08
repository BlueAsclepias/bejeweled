package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.registry.ModItems;
import net.blueasclepias.bejeweled.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagLookup<Block>> blockTags,
            ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        ModItems.ORE_BLOCK_ITEMS.forEach((item, type) -> {
            tag(ModTags.Items.FORGE_GENERIC_ORES).add(item.get());
            tag(ModTags.Items.CROSS_GENERIC_ORES).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("forge", "ores/" + type.name()))).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("c", "ores/" + type.name()))).add(item.get());
        });

        ModItems.STORAGE_BLOCK_ITEMS.forEach((item, type) -> {
            tag(ModTags.Items.FORGE_GENERIC_STORAGE_BLOCKS).add(item.get());
            tag(ModTags.Items.CROSS_GENERIC_STORAGE_BLOCKS).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("forge", "storage_blocks/" +
                            type))).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("c", "storage_blocks/" +
                            type))).add(item.get());
        });

        ModItems.ROUGH_GEMS.forEach((item, type) -> {
            tag(ModTags.Items.ROUGH_GEMS).add(item.get());
            tag(ModTags.Items.CROSS_GENERIC_GEMS).add(item.get());
            tag(ModTags.Items.FORGE_GENERIC_GEMS).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("forge", "gems/" + type.name()))).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("c", "gems/" + type.name()))).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("forge", "raw_materials/" + type.name()))).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("c", "raw_materials/" + type.name()))).add(item.get());
        });

        ModItems.ROUGH_BEADS.forEach((item, type) -> {
            tag(ModTags.Items.ROUGH_BEADS).add(item.get());
            tag(ModTags.Items.CROSS_GENERIC_GEMS).add(item.get());
            tag(ModTags.Items.FORGE_GENERIC_GEMS).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("forge", "gems/" + type.name()))).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("c", "gems/" + type.name()))).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("forge", "raw_materials/" + type.name()))).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("c", "raw_materials/" + type.name()))).add(item.get());
        });

        ModItems.CUT_GEMS.forEach((item, type) -> {
            tag(ModTags.Items.CUT_GEMS).add(item.get());
            tag(ModTags.Items.CROSS_GENERIC_GEMS).add(item.get());
            tag(ModTags.Items.FORGE_GENERIC_GEMS).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("forge", "gems/" + type.name()))).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("c", "gems/" + type.name()))).add(item.get());
        });

        ModItems.POLISHED_BEADS.forEach((item, type) -> {
            tag(ModTags.Items.POLISHED_BEADS).add(item.get());
            tag(ModTags.Items.CROSS_GENERIC_GEMS).add(item.get());
            tag(ModTags.Items.FORGE_GENERIC_GEMS).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("forge", "gems/" + type.name()))).add(item.get());
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("c", "gems/" + type.name()))).add(item.get());
        });
    }
}
