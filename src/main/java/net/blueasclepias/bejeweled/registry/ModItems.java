package net.blueasclepias.bejeweled.registry;

import net.blueasclepias.bejeweled.item.ProcessedGemItem;
import net.blueasclepias.bejeweled.item.RawGemItem;
import net.blueasclepias.bejeweled.material.definition.gem.GemDefinition;
import net.blueasclepias.bejeweled.material.instance.gem.GemDefinitions;
import net.blueasclepias.bejeweled.material.registry.ModGemRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

/**
 * Register Mod Items and Block Items
 */
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    // ===== GEM CUTTING TABLE =====
    public static final RegistryObject<Item> GEM_CUTTING_TABLE =
            ITEMS.register(
                    "gem_cutting_table",
                    () -> new BlockItem(
                            ModBlocks.GEM_CUTTING_TABLE.get(),
                            new Item.Properties()
                    )
            );

    // ===== GEMSTONES =====
    // === RAW ===
    public static final RegistryObject<Item> RAW_AQUAMARINE =
            registerRawGemItem("raw_aquamarine", GemDefinitions.AQUAMARINE);
    public static final RegistryObject<Item> RAW_RUBY =
            registerRawGemItem("raw_ruby", GemDefinitions.RUBY);
    public static final RegistryObject<Item> RAW_SAPPHIRE =
            registerRawGemItem("raw_sapphire", GemDefinitions.SAPPHIRE);
    public static final RegistryObject<Item> RAW_GARNET =
            registerRawGemItem("raw_garnet", GemDefinitions.GARNET);
    public static final RegistryObject<Item> RAW_TOPAZ =
            registerRawGemItem("raw_topaz", GemDefinitions.TOPAZ);
    public static final RegistryObject<Item> RAW_TURQUOISE =
            registerRawGemItem("raw_turquoise", GemDefinitions.TURQUOISE);
    public static final RegistryObject<Item> RAW_JADE =
            registerRawGemItem("raw_jade", GemDefinitions.JADE);
    public static final RegistryObject<Item> RAW_OPAL =
            registerRawGemItem("raw_opal", GemDefinitions.OPAL);
    public static final RegistryObject<Item> RAW_PERIDOT =
            registerRawGemItem("raw_peridot", GemDefinitions.PERIDOT);
    // === PROCESSED ===
    public static final RegistryObject<Item> AQUAMARINE =
            registerProcessedGemItem("aquamarine", GemDefinitions.AQUAMARINE);
    public static final RegistryObject<Item> RUBY =
            registerProcessedGemItem("ruby", GemDefinitions.RUBY);
    public static final RegistryObject<Item> SAPPHIRE =
            registerProcessedGemItem("sapphire", GemDefinitions.SAPPHIRE);
    public static final RegistryObject<Item> GARNET =
            registerProcessedGemItem("garnet", GemDefinitions.GARNET);
    public static final RegistryObject<Item> AMETHYST =
            registerProcessedGemItem("amethyst", GemDefinitions.AMETHYST);
    public static final RegistryObject<Item> DIAMOND =
            registerProcessedGemItem("diamond", GemDefinitions.DIAMOND);
    public static final RegistryObject<Item> EMERALD =
            registerProcessedGemItem("emerald", GemDefinitions.EMERALD);
    public static final RegistryObject<Item> TOPAZ =
            registerProcessedGemItem("topaz", GemDefinitions.TOPAZ);
    public static final RegistryObject<Item> TURQUOISE =
            registerProcessedGemItem("turquoise", GemDefinitions.TURQUOISE);
    public static final RegistryObject<Item> JADE =
            registerProcessedGemItem("jade", GemDefinitions.JADE);
    public static final RegistryObject<Item> OPAL =
            registerProcessedGemItem("opal", GemDefinitions.OPAL);
    public static final RegistryObject<Item> PERIDOT =
            registerProcessedGemItem("peridot", GemDefinitions.PERIDOT);

    // ===== BEADS =====
    // === RAW ===
    public static final RegistryObject<Item> RAW_PEARL =
            registerRawGemItem("raw_pearl", GemDefinitions.PEARL);
    public static final RegistryObject<Item> RAW_TUBE_CORAL_POLYP =
            registerRawGemItem("raw_tube_coral_polyp", GemDefinitions.TUBE_CORAL);
    public static final RegistryObject<Item> RAW_BRAIN_CORAL_POLYP =
            registerRawGemItem("raw_brain_coral_polyp", GemDefinitions.BRAIN_CORAL);
    public static final RegistryObject<Item> RAW_BUBBLE_CORAL_POLYP =
            registerRawGemItem("raw_bubble_coral_polyp", GemDefinitions.BUBBLE_CORAL);
    public static final RegistryObject<Item> RAW_FIRE_CORAL_POLYP =
            registerRawGemItem("raw_fire_coral_polyp", GemDefinitions.FIRE_CORAL);
    public static final RegistryObject<Item> RAW_HORN_CORAL_POLYP =
            registerRawGemItem("raw_horn_coral_polyp", GemDefinitions.HORN_CORAL);

    // === PROCESSED ===
    public static final RegistryObject<Item> PEARL =
            registerProcessedGemItem("pearl", GemDefinitions.PEARL);
    public static final RegistryObject<Item> TUBE_CORAL_BEAD =
            registerProcessedGemItem("tube_coral_bead", GemDefinitions.TUBE_CORAL);
    public static final RegistryObject<Item> BRAIN_CORAL_BEAD =
            registerProcessedGemItem("brain_coral_bead", GemDefinitions.BRAIN_CORAL);
    public static final RegistryObject<Item> BUBBLE_CORAL_BEAD =
            registerProcessedGemItem("bubble_coral_bead", GemDefinitions.BUBBLE_CORAL);
    public static final RegistryObject<Item> FIRE_CORAL_BEAD =
            registerProcessedGemItem("fire_coral_bead", GemDefinitions.FIRE_CORAL);
    public static final RegistryObject<Item> HORN_CORAL_BEAD =
            registerProcessedGemItem("horn_coral_bead", GemDefinitions.HORN_CORAL);

    // ===== BLOCK ITEMS =====

    // ==== ORE BLOCKS ====
    public static final RegistryObject<Item> STONE_BERYL_ORE =
            registerBlockItem("stone_beryl_ore", ModBlocks.STONE_BERYL_ORE);
    public static final RegistryObject<Item> GRANITE_BERYL_ORE =
            registerBlockItem("granite_beryl_ore", ModBlocks.GRANITE_BERYL_ORE);

    public static final RegistryObject<Item> STONE_RED_CORUNDUM_ORE =
            registerBlockItem("stone_red_corundum_ore", ModBlocks.STONE_RED_CORUNDUM_ORE);
    public static final RegistryObject<Item> DEEPSLATE_RED_CORUNDUM_ORE =
            registerBlockItem("deepslate_red_corundum_ore", ModBlocks.DEEPSLATE_RED_CORUNDUM_ORE);

    public static final RegistryObject<Item> DEEPSLATE_BLUE_CORUNDUM_ORE =
            registerBlockItem("deepslate_blue_corundum_ore", ModBlocks.DEEPSLATE_BLUE_CORUNDUM_ORE);
    public static final RegistryObject<Item> BASALT_BLUE_CORUNDUM_ORE =
            registerBlockItem("basalt_blue_corundum_ore", ModBlocks.BASALT_BLUE_CORUNDUM_ORE);

    public static final RegistryObject<Item> STONE_GARNET_ORE =
            registerBlockItem("stone_garnet_ore", ModBlocks.STONE_GARNET_ORE);
    public static final RegistryObject<Item> DEEPSLATE_GARNET_ORE =
            registerBlockItem("deepslate_garnet_ore", ModBlocks.DEEPSLATE_GARNET_ORE);

    public static final RegistryObject<Item> STONE_TOPAZ_ORE =
            registerBlockItem("stone_topaz_ore", ModBlocks.STONE_TOPAZ_ORE);
    public static final RegistryObject<Item> GRANITE_TOPAZ_ORE =
            registerBlockItem("granite_topaz_ore", ModBlocks.GRANITE_TOPAZ_ORE);
    public static final RegistryObject<Item> DEEPSLATE_TOPAZ_ORE =
            registerBlockItem("deepslate_topaz_ore", ModBlocks.DEEPSLATE_TOPAZ_ORE);

    public static final RegistryObject<Item> STONE_TURQUOISE_ORE =
            registerBlockItem("stone_turquoise_ore", ModBlocks.STONE_TURQUOISE_ORE);

    public static final RegistryObject<Item> STONE_NEPHRITE_ORE =
            registerBlockItem("stone_nephrite_ore", ModBlocks.STONE_NEPHRITE_ORE);
    public static final RegistryObject<Item> DEEPSLATE_NEPHRITE_ORE =
            registerBlockItem("deepslate_nephrite_ore", ModBlocks.DEEPSLATE_NEPHRITE_ORE);

    public static final RegistryObject<Item> STONE_JADEITE_ORE =
            registerBlockItem("stone_jadeite_ore", ModBlocks.STONE_JADEITE_ORE);
    public static final RegistryObject<Item> DEEPSLATE_JADEITE_ORE =
            registerBlockItem("deepslate_jadeite_ore", ModBlocks.DEEPSLATE_JADEITE_ORE);


    public static final RegistryObject<Item> TERRACOTTA_OPAL_ORE =
            registerBlockItem("terracotta_opal_ore", ModBlocks.TERRACOTTA_OPAL_ORE);

    public static final RegistryObject<Item> DEEPSLATE_OLIVINE_ORE =
            registerBlockItem("deepslate_olivine_ore", ModBlocks.DEEPSLATE_OLIVINE_ORE);

    // ==== STORAGE BLOCKS ====

    // === RAW ===
    public static final RegistryObject<Item> BLOCK_OF_RAW_AQUAMARINE =
            registerBlockItem("block_of_raw_aquamarine", ModBlocks.BLOCK_OF_RAW_AQUAMARINE);
    public static final RegistryObject<Item> BLOCK_OF_RAW_RUBY =
            registerBlockItem("block_of_raw_ruby", ModBlocks.BLOCK_OF_RAW_RUBY);
    public static final RegistryObject<Item> BLOCK_OF_RAW_SAPPHIRE =
            registerBlockItem("block_of_raw_sapphire", ModBlocks.BLOCK_OF_RAW_SAPPHIRE);
    public static final RegistryObject<Item> BLOCK_OF_RAW_GARNET =
            registerBlockItem("block_of_raw_garnet", ModBlocks.BLOCK_OF_RAW_GARNET);
    public static final RegistryObject<Item> BLOCK_OF_RAW_TOPAZ =
            registerBlockItem("block_of_raw_topaz", ModBlocks.BLOCK_OF_RAW_TOPAZ);
    public static final RegistryObject<Item> BLOCK_OF_RAW_TURQUOISE =
            registerBlockItem("block_of_raw_turquoise", ModBlocks.BLOCK_OF_RAW_TURQUOISE);
    public static final RegistryObject<Item> BLOCK_OF_RAW_JADE =
            registerBlockItem("block_of_raw_jade", ModBlocks.BLOCK_OF_RAW_JADE);
    public static final RegistryObject<Item> BLOCK_OF_RAW_OPAL =
            registerBlockItem("block_of_raw_opal", ModBlocks.BLOCK_OF_RAW_OPAL);
    public static final RegistryObject<Item> BLOCK_OF_RAW_PERIDOT =
            registerBlockItem("block_of_raw_peridot", ModBlocks.BLOCK_OF_RAW_PERIDOT);
    public static final RegistryObject<Item> BLOCK_OF_RAW_PEARL =
            registerBlockItem("block_of_raw_pearl", ModBlocks.BLOCK_OF_RAW_PEARL);

    // ==== CORAL POLYP BLOCKS ====
    public static final RegistryObject<Item> FIRE_CORAL_BLOCK_POLYP =
            registerBlockItem("fire_coral_block_polyp",ModBlocks.FIRE_CORAL_BLOCK_POLYP);
    public static final RegistryObject<Item> BRAIN_CORAL_BLOCK_POLYP =
            registerBlockItem("brain_coral_block_polyp",ModBlocks.BRAIN_CORAL_BLOCK_POLYP);
    public static final RegistryObject<Item> BUBBLE_CORAL_BLOCK_POLYP =
            registerBlockItem("bubble_coral_block_polyp",ModBlocks.BUBBLE_CORAL_BLOCK_POLYP);
    public static final RegistryObject<Item> HORN_CORAL_BLOCK_POLYP =
            registerBlockItem("horn_coral_block_polyp",ModBlocks.HORN_CORAL_BLOCK_POLYP);
    public static final RegistryObject<Item> TUBE_CORAL_BLOCK_POLYP =
            registerBlockItem("tube_coral_block_polyp",ModBlocks.TUBE_CORAL_BLOCK_POLYP);

    // ===== JEWEL BASES STUB =====
    public static final RegistryObject<Item> GEM_SOCKET =
            ITEMS.register("gem_socket", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RING =
            ITEMS.register("ring", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BRACELET =
            ITEMS.register("bracelet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMULET =
            ITEMS.register("amulet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CIRCLET =
            ITEMS.register("circlet", () -> new Item(new Item.Properties()));

    // ===== HELPER METHODS =====
    private static RegistryObject<Item> registerRawGemItem(String path, GemDefinition def) {
        return ITEMS.register(path, () -> {
            Item item = new RawGemItem(def);
            ModGemRegistry.bind(item, fromNamespaceAndPath(MOD_ID, def.id()));
            return item;
        });
    }

    private static RegistryObject<Item> registerProcessedGemItem(String path, GemDefinition def) {
        return ITEMS.register(path, () -> {
            Item item = new ProcessedGemItem(def);
            ModGemRegistry.bind(item, fromNamespaceAndPath(MOD_ID, def.id()));
            return item;
        });
    }

    private static RegistryObject<Item> registerBlockItem(String path, RegistryObject<Block> block){
        return ITEMS.register(path, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        ModGemRegistry.bind(Items.DIAMOND, fromNamespaceAndPath(MOD_ID, "diamond"));
        ModGemRegistry.bind(Items.EMERALD, fromNamespaceAndPath(MOD_ID, "emerald"));
        ModGemRegistry.bind(Items.AMETHYST_SHARD, fromNamespaceAndPath(MOD_ID, "amethyst"));
        ITEMS.register(eventBus);
    }
}
