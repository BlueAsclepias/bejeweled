package net.blueasclepias.bejeweled.registry;

import net.blueasclepias.bejeweled.data.definition.jewel.JewelMaterial;
import net.blueasclepias.bejeweled.data.definition.jewel.JewelType;
import net.blueasclepias.bejeweled.item.BaseJewelItem;
import net.blueasclepias.bejeweled.item.GemItem;
import net.blueasclepias.bejeweled.item.SocketedJewelItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

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

    // TODO: THIS IS THE NEW POC
    // ===== PROCESSED MASTER GEM =====
    public static final RegistryObject<Item> GEM_ITEM =
            ITEMS.register("gem_item", () -> new GemItem(new Item.Properties()));

    // ===== GEMSTONES =====
    public static final RegistryObject<Item> AQUAMARINE =
            ITEMS.register("aquamarine", () -> new Item(new Item.Properties()));
            
    public static final RegistryObject<Item> RUBY =
            ITEMS.register("ruby", () -> new Item(new Item.Properties()));
            
    public static final RegistryObject<Item> SAPPHIRE =
            ITEMS.register("sapphire", () -> new Item(new Item.Properties()));
            
    public static final RegistryObject<Item> GARNET =
            ITEMS.register("garnet", () -> new Item(new Item.Properties()));
            
    public static final RegistryObject<Item> TOPAZ =
            ITEMS.register("topaz", () -> new Item(new Item.Properties()));
            
    public static final RegistryObject<Item> TURQUOISE =
            ITEMS.register("turquoise", () -> new Item(new Item.Properties()));
            
    public static final RegistryObject<Item> JADE =
            ITEMS.register("jade", () -> new Item(new Item.Properties()));
            
    public static final RegistryObject<Item> OPAL =
            ITEMS.register("opal", () -> new Item(new Item.Properties()));
            
    public static final RegistryObject<Item> PERIDOT =
            ITEMS.register("peridot", () -> new Item(new Item.Properties()));

    // ===== BEADS =====
    public static final RegistryObject<Item> PEARL =
            ITEMS.register("pearl", () -> new Item(new Item.Properties()));
    
    public static final RegistryObject<Item> BLUE_CORAL =
            ITEMS.register("blue_coral", () -> new Item(new Item.Properties()));
            
    public static final RegistryObject<Item> PINK_CORAL =
            ITEMS.register("pink_coral", () -> new Item(new Item.Properties()));
            
    public static final RegistryObject<Item> PURPLE_CORAL =
            ITEMS.register("purple_coral", () -> new Item(new Item.Properties()));
            
    public static final RegistryObject<Item> RED_CORAL =
            ITEMS.register("red_coral", () -> new Item(new Item.Properties()));
            
    public static final RegistryObject<Item> YELLOW_CORAL =
            ITEMS.register("yellow_coral", () -> new Item(new Item.Properties()));
            
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
    public static final RegistryObject<Item> AQUAMARINE_BLOCK =
            registerBlockItem("aquamarine_block", ModBlocks.AQUAMARINE_BLOCK);
    public static final RegistryObject<Item> RUBY_BLOCK =
            registerBlockItem("ruby_block", ModBlocks.RUBY_BLOCK);
    public static final RegistryObject<Item> SAPPHIRE_BLOCK =
            registerBlockItem("sapphire_block", ModBlocks.SAPPHIRE_BLOCK);
    public static final RegistryObject<Item> GARNET_BLOCK =
            registerBlockItem("garnet_block", ModBlocks.GARNET_BLOCK);
    public static final RegistryObject<Item> TOPAZ_BLOCK =
            registerBlockItem("topaz_block", ModBlocks.TOPAZ_BLOCK);
    public static final RegistryObject<Item> TURQUOISE_BLOCK =
            registerBlockItem("turquoise_block", ModBlocks.TURQUOISE_BLOCK);
    public static final RegistryObject<Item> JADE_BLOCK =
            registerBlockItem("jade_block", ModBlocks.JADE_BLOCK);
    public static final RegistryObject<Item> OPAL_BLOCK =
            registerBlockItem("opal_block", ModBlocks.OPAL_BLOCK);
    public static final RegistryObject<Item> PERIDOT_BLOCK =
            registerBlockItem("peridot_block", ModBlocks.PERIDOT_BLOCK);
    public static final RegistryObject<Item> PEARL_BLOCK =
            registerBlockItem("pearl_block", ModBlocks.PEARL_BLOCK);

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

    // ====== CRAFTABLE JEWEL BASES =====
    public static final RegistryObject<Item> COPPER_RING = ITEMS.register("copper_ring", () -> new BaseJewelItem(new Item.Properties(), JewelType.RING, JewelMaterial.COPPER));
    public static final RegistryObject<Item> BRONZE_RING = ITEMS.register("bronze_ring", () -> new BaseJewelItem(new Item.Properties(), JewelType.RING, JewelMaterial.BRONZE));
    public static final RegistryObject<Item> SILVER_RING = ITEMS.register("silver_ring", () -> new BaseJewelItem(new Item.Properties(), JewelType.RING, JewelMaterial.SILVER));
    public static final RegistryObject<Item> IRON_RING = ITEMS.register("iron_ring", () -> new BaseJewelItem(new Item.Properties(), JewelType.RING, JewelMaterial.IRON));
    public static final RegistryObject<Item> GOLD_RING = ITEMS.register("gold_ring", () -> new BaseJewelItem(new Item.Properties(), JewelType.RING, JewelMaterial.GOLD));
    public static final RegistryObject<Item> STEEL_RING = ITEMS.register("steel_ring", () -> new BaseJewelItem(new Item.Properties(), JewelType.RING, JewelMaterial.STEEL));
    public static final RegistryObject<Item> NETHERITE_RING = ITEMS.register("netherite_ring", () -> new BaseJewelItem(new Item.Properties(), JewelType.RING, JewelMaterial.NETHERITE));

    public static final RegistryObject<Item> COPPER_AMULET = ITEMS.register("copper_amulet", () -> new BaseJewelItem(new Item.Properties(), JewelType.AMULET, JewelMaterial.COPPER));
    public static final RegistryObject<Item> BRONZE_AMULET = ITEMS.register("bronze_amulet", () -> new BaseJewelItem(new Item.Properties(), JewelType.AMULET, JewelMaterial.BRONZE));
    public static final RegistryObject<Item> SILVER_AMULET = ITEMS.register("silver_amulet", () -> new BaseJewelItem(new Item.Properties(), JewelType.AMULET, JewelMaterial.SILVER));
    public static final RegistryObject<Item> IRON_AMULET = ITEMS.register("iron_amulet", () -> new BaseJewelItem(new Item.Properties(), JewelType.AMULET, JewelMaterial.IRON));
    public static final RegistryObject<Item> GOLD_AMULET = ITEMS.register("gold_amulet", () -> new BaseJewelItem(new Item.Properties(), JewelType.AMULET, JewelMaterial.GOLD));
    public static final RegistryObject<Item> STEEL_AMULET = ITEMS.register("steel_amulet", () -> new BaseJewelItem(new Item.Properties(), JewelType.AMULET, JewelMaterial.STEEL));
    public static final RegistryObject<Item> NETHERITE_AMULET = ITEMS.register("netherite_amulet", () -> new BaseJewelItem(new Item.Properties(), JewelType.AMULET, JewelMaterial.NETHERITE));

    public static final RegistryObject<Item> COPPER_BRACELET = ITEMS.register("copper_bracelet", () -> new BaseJewelItem(new Item.Properties(), JewelType.BRACELET, JewelMaterial.COPPER));
    public static final RegistryObject<Item> BRONZE_BRACELET = ITEMS.register("bronze_bracelet", () -> new BaseJewelItem(new Item.Properties(), JewelType.BRACELET, JewelMaterial.BRONZE));
    public static final RegistryObject<Item> SILVER_BRACELET = ITEMS.register("silver_bracelet", () -> new BaseJewelItem(new Item.Properties(), JewelType.BRACELET, JewelMaterial.SILVER));
    public static final RegistryObject<Item> IRON_BRACELET = ITEMS.register("iron_bracelet", () -> new BaseJewelItem(new Item.Properties(), JewelType.BRACELET, JewelMaterial.IRON));
    public static final RegistryObject<Item> GOLD_BRACELET = ITEMS.register("gold_bracelet", () -> new BaseJewelItem(new Item.Properties(), JewelType.BRACELET, JewelMaterial.GOLD));
    public static final RegistryObject<Item> STEEL_BRACELET = ITEMS.register("steel_bracelet", () -> new BaseJewelItem(new Item.Properties(), JewelType.BRACELET, JewelMaterial.STEEL));
    public static final RegistryObject<Item> NETHERITE_BRACELET = ITEMS.register("netherite_bracelet", () -> new BaseJewelItem(new Item.Properties(), JewelType.BRACELET, JewelMaterial.NETHERITE));

    public static final RegistryObject<Item> COPPER_CIRCLET = ITEMS.register("copper_circlet", () -> new BaseJewelItem(new Item.Properties(), JewelType.CIRCLET, JewelMaterial.COPPER));
    public static final RegistryObject<Item> BRONZE_CIRCLET = ITEMS.register("bronze_circlet", () -> new BaseJewelItem(new Item.Properties(), JewelType.CIRCLET, JewelMaterial.BRONZE));
    public static final RegistryObject<Item> SILVER_CIRCLET = ITEMS.register("silver_circlet", () -> new BaseJewelItem(new Item.Properties(), JewelType.CIRCLET, JewelMaterial.SILVER));
    public static final RegistryObject<Item> IRON_CIRCLET = ITEMS.register("iron_circlet", () -> new BaseJewelItem(new Item.Properties(), JewelType.CIRCLET, JewelMaterial.IRON));
    public static final RegistryObject<Item> GOLD_CIRCLET = ITEMS.register("gold_circlet", () -> new BaseJewelItem(new Item.Properties(), JewelType.CIRCLET, JewelMaterial.GOLD));
    public static final RegistryObject<Item> STEEL_CIRCLET = ITEMS.register("steel_circlet", () -> new BaseJewelItem(new Item.Properties(), JewelType.CIRCLET, JewelMaterial.STEEL));
    public static final RegistryObject<Item> NETHERITE_CIRCLET = ITEMS.register("netherite_circlet", () -> new BaseJewelItem(new Item.Properties(), JewelType.CIRCLET, JewelMaterial.NETHERITE));

    // ===== SOCKETED JEWELS =====
    public static final RegistryObject<Item> SOCKETED_RING =
            ITEMS.register("socketed_ring", () -> new SocketedJewelItem(new Item.Properties()));

    public static final RegistryObject<Item> SOCKETED_BRACELET =
            ITEMS.register("socketed_bracelet", () -> new SocketedJewelItem(new Item.Properties()));

    public static final RegistryObject<Item> SOCKETED_AMULET =
            ITEMS.register("socketed_amulet", () -> new SocketedJewelItem(new Item.Properties()));

    public static final RegistryObject<Item> SOCKETED_CIRCLET =
            ITEMS.register("socketed_circlet", () -> new SocketedJewelItem(new Item.Properties()));

    // ===== HELPER METHODS =====
    private static RegistryObject<Item> registerBlockItem(String path, RegistryObject<Block> block){
        return ITEMS.register(path, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
