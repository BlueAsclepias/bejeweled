package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.material.definition.gem.GemCategory;
import net.blueasclepias.bejeweled.material.registry.ModGemRegistry;
import net.blueasclepias.bejeweled.material.registry.ModOreRegistry;
import net.blueasclepias.bejeweled.material.registry.ModStorageBlockRegistry;
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

        ModOreRegistry.allBlocksByFeature().forEach((feat, block) -> {
            Item item = block.asItem();
            String path = feat.definition().id();
            tag(ModTags.Items.FORGE_GENERIC_ORES).add(item);
            tag(ModTags.Items.CROSS_GENERIC_ORES).add(item);
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("forge", "ores/" + path))).add(item);
            tag(TagKey.create(Registries.ITEM,
                    fromNamespaceAndPath("c", "ores/" + path))).add(item);
        });

        ModStorageBlockRegistry.allBlocks().forEach((block) -> {
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

        ModGemRegistry.getAll(GemCategory.GEMSTONE, false)
                .forEach((item, def) -> {
                    String path = def.id();
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

        ModGemRegistry.getAll(GemCategory.BEAD, false)
                .forEach((item, def) -> {
                    String path = def.id();
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

        ModGemRegistry.getAll(GemCategory.GEMSTONE, true)
                .forEach((item, def) -> {
                    String path = def.id();
                    tag(ModTags.Items.PROCESSED_GEMSTONES).add(item);
                    tag(ModTags.Items.CROSS_GENERIC_GEMS).add(item);
                    tag(ModTags.Items.FORGE_GENERIC_GEMS).add(item);
                    tag(TagKey.create(Registries.ITEM,
                            fromNamespaceAndPath("forge", "gems/" + path))).add(item);
                    tag(TagKey.create(Registries.ITEM,
                            fromNamespaceAndPath("c", "gems/" + path))).add(item);
        });

        ModGemRegistry.getAll(GemCategory.BEAD, true)
                .forEach((item, def) -> {
                    String path = def.id();
                    tag(ModTags.Items.PROCESSED_BEADS).add(item);
                    tag(ModTags.Items.CROSS_GENERIC_GEMS).add(item);
                    tag(ModTags.Items.FORGE_GENERIC_GEMS).add(item);
                    tag(TagKey.create(Registries.ITEM,
                            fromNamespaceAndPath("forge", "gems/" + path))).add(item);
                    tag(TagKey.create(Registries.ITEM,
                            fromNamespaceAndPath("c", "gems/" + path))).add(item);
        });
    }
}
