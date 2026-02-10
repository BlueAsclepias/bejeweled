package net.blueasclepias.bejeweled.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class ModTags {

    public static class Items {
        // === MOD TAGS ===
        public static final TagKey<Item> ROUGH_GEMS =
                TagKey.create(Registries.ITEM, fromNamespaceAndPath(MOD_ID, "rough_gemstones"));

        public static final TagKey<Item> CUT_GEMS =
                TagKey.create(Registries.ITEM, fromNamespaceAndPath(MOD_ID, "cut_gemstones"));

        public static final TagKey<Item> ROUGH_BEADS =
                TagKey.create(Registries.ITEM, fromNamespaceAndPath(MOD_ID, "rough_beads"));

        public static final TagKey<Item> POLISHED_BEADS =
                TagKey.create(Registries.ITEM, fromNamespaceAndPath(MOD_ID, "polished_beads"));

        // === LOADER TAGS ===
        public static final TagKey<Item> FORGE_GENERIC_GEMS =
                TagKey.create(Registries.ITEM, fromNamespaceAndPath("forge", "gems"));

        public static final TagKey<Item> CROSS_GENERIC_GEMS =
                TagKey.create(Registries.ITEM, fromNamespaceAndPath("c", "gems"));

        public static final TagKey<Item> FORGE_GENERIC_STORAGE_BLOCKS =
                TagKey.create(Registries.ITEM, fromNamespaceAndPath("forge", "storage_blocks"));

        public static final TagKey<Item> CROSS_GENERIC_STORAGE_BLOCKS =
                TagKey.create(Registries.ITEM, fromNamespaceAndPath("c", "storage_blocks"));

        public static final TagKey<Item> FORGE_GENERIC_ORES =
                TagKey.create(Registries.ITEM, fromNamespaceAndPath("forge", "ores"));

        public static final TagKey<Item> CROSS_GENERIC_ORES =
                TagKey.create(Registries.ITEM, fromNamespaceAndPath("c", "ores"));
    }
}

