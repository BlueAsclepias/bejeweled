package net.blueasclepias.bejeweled.common.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class ModTags {

    public static class Items {
        // === MOD TAGS ===
        public static final TagKey<Item> RAW_GEMSTONES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, "raw_gemstones"));

        public static final TagKey<Item> PROCESSED_GEMSTONES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, "processed_gemstones"));

        public static final TagKey<Item> RAW_BEADS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, "raw_beads"));

        public static final TagKey<Item> PROCESSED_BEADS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, "processed_beads"));

        // === LOADER TAGS ===
        public static final TagKey<Item> FORGE_GENERIC_GEMS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "gems"));

        public static final TagKey<Item> CROSS_GENERIC_GEMS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gems"));

        public static final TagKey<Item> FORGE_GENERIC_STORAGE_BLOCKS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "storage_blocks"));

        public static final TagKey<Item> CROSS_GENERIC_STORAGE_BLOCKS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "storage_blocks"));

        public static final TagKey<Item> FORGE_GENERIC_ORES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "ores"));

        public static final TagKey<Item> CROSS_GENERIC_ORES =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ores"));
    }

    public static class Blocks {
        // === MOD TAGS ===
        public static final TagKey<Block> STORAGE_BLOCKS =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "storage_blocks"));

        public static final TagKey<Block> GEM_ORES =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "gem_ores"));

        public static final TagKey<Block> CORAL_POLYPS =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "coral_polyps"));

        // === LOADER TAGS ===
        public static final TagKey<Block> FORGE_GENERIC_ORES =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("forge", "ores"));

        public static final TagKey<Block> CROSS_GENERIC_ORES =
                TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", "ores"));
    }

}

