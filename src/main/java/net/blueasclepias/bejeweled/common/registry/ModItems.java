package net.blueasclepias.bejeweled.common.registry;

import net.blueasclepias.bejeweled.common.data.gem.defaults.DefaultGemDefinitions;
import net.blueasclepias.bejeweled.common.data.jewel.definition.JewelMaterial;
import net.blueasclepias.bejeweled.common.data.jewel.definition.JewelType;
import net.blueasclepias.bejeweled.common.item.BaseJewelryItem;
import net.blueasclepias.bejeweled.common.item.GemItem;
import net.blueasclepias.bejeweled.common.item.RawGemItem;
import net.blueasclepias.bejeweled.common.item.SocketedJewelryItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
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

    // ===== RAW GEMSTONES & BEADS =====
    // One item per DefaultGemDefinitions entry that belongs to this mod's own namespace, keyed by id, so adding a
    // new gem or bead only requires a change in DefaultGemDefinitions rather than also hand-registering an item here.
    // Vanilla-sourced definitions (diamond, emerald, amethyst shard) are skipped, since they already have their own
    // vanilla item.
    public static final Map<String, RegistryObject<Item>> RAW_GEM_ITEMS = registerRawGemItems();

    // ===== BLOCK ITEMS =====
    // ==== ORE BLOCKS ====
    // One item per ModBlocks.ORE_BLOCKS entry, keyed by ore id (see OreEntries for the ore list itself).
    public static final Map<String, RegistryObject<Item>> ORE_ITEMS = registerBlockItems(ModBlocks.ORE_BLOCKS);

    // ==== STORAGE BLOCKS ====
    // One item per ModBlocks.STORAGE_BLOCKS entry, keyed by block id (see StorageBlockEntries for the block list).
    public static final Map<String, RegistryObject<Item>> STORAGE_BLOCK_ITEMS = registerBlockItems(ModBlocks.STORAGE_BLOCKS);

    // ==== CORAL POLYP BLOCKS ====
    // One item per ModBlocks.CORAL_POLYP_BLOCKS entry, keyed by block id (see CoralPolypEntries for the block list).
    public static final Map<String, RegistryObject<Item>> CORAL_POLYP_ITEMS = registerBlockItems(ModBlocks.CORAL_POLYP_BLOCKS);

    // ====== CRAFTABLE JEWEL BASES =====
    // One item per JewelType x JewelMaterial combination, keyed by type then material, so adding a new jewel type or
    // material only requires adding the enum constant rather than also hand-registering every combination here.
    public static final Map<JewelType, Map<JewelMaterial, RegistryObject<Item>>> JEWELRY_ITEMS = registerJewelryItems();

    // ===== SOCKETED JEWELS =====
    // One item per JewelType, keyed by type. Shared with SocketedJewelryItemFactory so the result item for a given
    // type is only defined in one place.
    public static final Map<JewelType, RegistryObject<Item>> SOCKETED_JEWELRY_ITEMS = registerSocketedJewelryItems();

    // ===== HELPER METHODS =====
    private static RegistryObject<Item> registerBlockItem(String path, RegistryObject<Block> block){
        return ITEMS.register(path, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static Map<String, RegistryObject<Item>> registerBlockItems(Map<String, RegistryObject<Block>> blocks) {
        Map<String, RegistryObject<Item>> items = new LinkedHashMap<>();
        blocks.forEach((id, block) -> items.put(id, registerBlockItem(id, block)));
        return items;
    }

    private static Map<String, RegistryObject<Item>> registerRawGemItems() {
        Map<String, RegistryObject<Item>> items = new LinkedHashMap<>();
        DefaultGemDefinitions.getAll().forEach((id, def) -> {
            if (!id.getNamespace().equals(MOD_ID)) return;
            items.put(id.getPath(), ITEMS.register(id.getPath(), () -> new RawGemItem(new Item.Properties())));
        });
        return items;
    }

    private static Map<JewelType, Map<JewelMaterial, RegistryObject<Item>>> registerJewelryItems() {
        Map<JewelType, Map<JewelMaterial, RegistryObject<Item>>> result = new EnumMap<>(JewelType.class);
        for (JewelType type : JewelType.values()) {
            Map<JewelMaterial, RegistryObject<Item>> byMaterial = new EnumMap<>(JewelMaterial.class);
            for (JewelMaterial material : JewelMaterial.values()) {
                String path = material.name().toLowerCase() + "_" + type.name().toLowerCase();
                byMaterial.put(material, ITEMS.register(path,
                        () -> new BaseJewelryItem(new Item.Properties(), type, material)));
            }
            result.put(type, byMaterial);
        }
        return result;
    }

    private static Map<JewelType, RegistryObject<Item>> registerSocketedJewelryItems() {
        Map<JewelType, RegistryObject<Item>> result = new EnumMap<>(JewelType.class);
        for (JewelType type : JewelType.values()) {
            String path = "socketed_" + type.name().toLowerCase();
            result.put(type, ITEMS.register(path, () -> new SocketedJewelryItem(new Item.Properties())));
        }
        return result;
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
