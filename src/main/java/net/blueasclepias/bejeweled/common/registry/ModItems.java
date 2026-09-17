package net.blueasclepias.bejeweled.common.registry;

import net.blueasclepias.bejeweled.common.data.jewel.definition.JewelMaterial;
import net.blueasclepias.bejeweled.common.data.jewel.definition.JewelType;
import net.blueasclepias.bejeweled.common.item.BaseJewelryItem;
import net.blueasclepias.bejeweled.common.item.GemItem;
import net.blueasclepias.bejeweled.common.item.SocketedJeweleryItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.Map;

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
    // One item per ModBlocks.ORE_BLOCKS entry, keyed by ore id (see OreEntries for the ore list itself).
    public static final Map<String, RegistryObject<Item>> ORE_ITEMS = registerOreItems();

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
    public static final RegistryObject<Item> COPPER_RING = ITEMS.register("copper_ring", () -> new BaseJewelryItem(new Item.Properties(), JewelType.RING, JewelMaterial.COPPER));
    public static final RegistryObject<Item> BRONZE_RING = ITEMS.register("bronze_ring", () -> new BaseJewelryItem(new Item.Properties(), JewelType.RING, JewelMaterial.BRONZE));
    public static final RegistryObject<Item> SILVER_RING = ITEMS.register("silver_ring", () -> new BaseJewelryItem(new Item.Properties(), JewelType.RING, JewelMaterial.SILVER));
    public static final RegistryObject<Item> IRON_RING = ITEMS.register("iron_ring", () -> new BaseJewelryItem(new Item.Properties(), JewelType.RING, JewelMaterial.IRON));
    public static final RegistryObject<Item> GOLD_RING = ITEMS.register("gold_ring", () -> new BaseJewelryItem(new Item.Properties(), JewelType.RING, JewelMaterial.GOLD));
    public static final RegistryObject<Item> STEEL_RING = ITEMS.register("steel_ring", () -> new BaseJewelryItem(new Item.Properties(), JewelType.RING, JewelMaterial.STEEL));
    public static final RegistryObject<Item> NETHERITE_RING = ITEMS.register("netherite_ring", () -> new BaseJewelryItem(new Item.Properties(), JewelType.RING, JewelMaterial.NETHERITE));

    public static final RegistryObject<Item> COPPER_AMULET = ITEMS.register("copper_amulet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.AMULET, JewelMaterial.COPPER));
    public static final RegistryObject<Item> BRONZE_AMULET = ITEMS.register("bronze_amulet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.AMULET, JewelMaterial.BRONZE));
    public static final RegistryObject<Item> SILVER_AMULET = ITEMS.register("silver_amulet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.AMULET, JewelMaterial.SILVER));
    public static final RegistryObject<Item> IRON_AMULET = ITEMS.register("iron_amulet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.AMULET, JewelMaterial.IRON));
    public static final RegistryObject<Item> GOLD_AMULET = ITEMS.register("gold_amulet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.AMULET, JewelMaterial.GOLD));
    public static final RegistryObject<Item> STEEL_AMULET = ITEMS.register("steel_amulet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.AMULET, JewelMaterial.STEEL));
    public static final RegistryObject<Item> NETHERITE_AMULET = ITEMS.register("netherite_amulet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.AMULET, JewelMaterial.NETHERITE));

    public static final RegistryObject<Item> COPPER_BRACELET = ITEMS.register("copper_bracelet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.BRACELET, JewelMaterial.COPPER));
    public static final RegistryObject<Item> BRONZE_BRACELET = ITEMS.register("bronze_bracelet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.BRACELET, JewelMaterial.BRONZE));
    public static final RegistryObject<Item> SILVER_BRACELET = ITEMS.register("silver_bracelet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.BRACELET, JewelMaterial.SILVER));
    public static final RegistryObject<Item> IRON_BRACELET = ITEMS.register("iron_bracelet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.BRACELET, JewelMaterial.IRON));
    public static final RegistryObject<Item> GOLD_BRACELET = ITEMS.register("gold_bracelet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.BRACELET, JewelMaterial.GOLD));
    public static final RegistryObject<Item> STEEL_BRACELET = ITEMS.register("steel_bracelet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.BRACELET, JewelMaterial.STEEL));
    public static final RegistryObject<Item> NETHERITE_BRACELET = ITEMS.register("netherite_bracelet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.BRACELET, JewelMaterial.NETHERITE));

    public static final RegistryObject<Item> COPPER_CIRCLET = ITEMS.register("copper_circlet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.CIRCLET, JewelMaterial.COPPER));
    public static final RegistryObject<Item> BRONZE_CIRCLET = ITEMS.register("bronze_circlet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.CIRCLET, JewelMaterial.BRONZE));
    public static final RegistryObject<Item> SILVER_CIRCLET = ITEMS.register("silver_circlet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.CIRCLET, JewelMaterial.SILVER));
    public static final RegistryObject<Item> IRON_CIRCLET = ITEMS.register("iron_circlet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.CIRCLET, JewelMaterial.IRON));
    public static final RegistryObject<Item> GOLD_CIRCLET = ITEMS.register("gold_circlet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.CIRCLET, JewelMaterial.GOLD));
    public static final RegistryObject<Item> STEEL_CIRCLET = ITEMS.register("steel_circlet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.CIRCLET, JewelMaterial.STEEL));
    public static final RegistryObject<Item> NETHERITE_CIRCLET = ITEMS.register("netherite_circlet", () -> new BaseJewelryItem(new Item.Properties(), JewelType.CIRCLET, JewelMaterial.NETHERITE));

    // ===== SOCKETED JEWELS =====
    public static final RegistryObject<Item> SOCKETED_RING =
            ITEMS.register("socketed_ring", () -> new SocketedJeweleryItem(new Item.Properties()));

    public static final RegistryObject<Item> SOCKETED_BRACELET =
            ITEMS.register("socketed_bracelet", () -> new SocketedJeweleryItem(new Item.Properties()));

    public static final RegistryObject<Item> SOCKETED_AMULET =
            ITEMS.register("socketed_amulet", () -> new SocketedJeweleryItem(new Item.Properties()));

    public static final RegistryObject<Item> SOCKETED_CIRCLET =
            ITEMS.register("socketed_circlet", () -> new SocketedJeweleryItem(new Item.Properties()));

    // ===== HELPER METHODS =====
    private static RegistryObject<Item> registerBlockItem(String path, RegistryObject<Block> block){
        return ITEMS.register(path, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static Map<String, RegistryObject<Item>> registerOreItems() {
        Map<String, RegistryObject<Item>> items = new LinkedHashMap<>();
        ModBlocks.ORE_BLOCKS.forEach((id, block) -> items.put(id, registerBlockItem(id, block)));
        return items;
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
