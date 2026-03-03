package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.data.accessor.OreAccessor;
import net.blueasclepias.bejeweled.data.accessor.StorageBlockAccessor;
import net.blueasclepias.bejeweled.data.definition.gem.GemCategory;
import net.blueasclepias.bejeweled.data.instance.gem.DefaultGemDefinitions;
import net.blueasclepias.bejeweled.registry.ModItems;
import net.blueasclepias.bejeweled.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
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
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        OreAccessor.allBlocksByFeature().forEach((feat, block) -> {
            Item item = block.asItem();
            String path = feat.definition().id();
            tag(ModTags.Items.FORGE_GENERIC_ORES).add(item);
            tag(ModTags.Items.CROSS_GENERIC_ORES).add(item);
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("forge", "ores/" + path))).add(item);
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("c", "ores/" + path))).add(item);
        });

        StorageBlockAccessor.allBlocks().forEach((block) -> {
            ResourceLocation blockId = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block));
            Item item = block.asItem();
            String path = blockId.getPath();
            tag(ModTags.Items.FORGE_GENERIC_STORAGE_BLOCKS).add(item);
            tag(ModTags.Items.CROSS_GENERIC_STORAGE_BLOCKS).add(item);
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("forge", "storage_blocks/" +
                            path))).add(item);
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("c", "storage_blocks/" +
                            path))).add(item);
        });

        DefaultGemDefinitions.getAllByCategory(GemCategory.GEMSTONE)
                .forEach((id, def) -> {
                    String path = id.getPath();
                    Item item = Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(id));
                    tag(ModTags.Items.RAW_GEMSTONES).add(item);
                    tag(ModTags.Items.CROSS_GENERIC_GEMS).add(item);
                    tag(ModTags.Items.FORGE_GENERIC_GEMS).add(item);
                    tag(TagKey.create(Registries.ITEM,
                            fromNamespaceAndPath("forge", "gems/" + path))).add(item);
                    tag(TagKey.create(Registries.ITEM,
                            fromNamespaceAndPath("c", "gems/" + path))).add(item);
                    tag(TagKey.create(Registries.ITEM,
                            fromNamespaceAndPath("forge", "raw_materials/" + path))).add(item);
                    tag(TagKey.create(Registries.ITEM,
                            fromNamespaceAndPath("c", "raw_materials/" + path))).add(item);
        });

        DefaultGemDefinitions.getAllByCategory(GemCategory.BEAD)
                .forEach((id, def) -> {
                    String path = id.getPath();
                    Item item = Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(id));
                    tag(ModTags.Items.RAW_BEADS).add(item);
                    tag(ModTags.Items.CROSS_GENERIC_GEMS).add(item);
                    tag(ModTags.Items.FORGE_GENERIC_GEMS).add(item);
                    tag(TagKey.create(Registries.ITEM,
                            fromNamespaceAndPath("forge", "gems/" + path))).add(item);
                    tag(TagKey.create(Registries.ITEM,
                            fromNamespaceAndPath("c", "gems/" + path))).add(item);
                    tag(TagKey.create(Registries.ITEM,
                            fromNamespaceAndPath("forge", "raw_materials/" + path))).add(item);
                    tag(TagKey.create(Registries.ITEM,
                            fromNamespaceAndPath("c", "raw_materials/" + path))).add(item);
        });

        tag(ModTags.Items.CROSS_GENERIC_GEMS).add(ModItems.GEM_ITEM.get());
        tag(ModTags.Items.FORGE_GENERIC_GEMS).add(ModItems.GEM_ITEM.get());

    }
}
