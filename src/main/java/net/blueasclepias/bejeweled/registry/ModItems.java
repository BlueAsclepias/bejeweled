package net.blueasclepias.bejeweled.registry;

import net.blueasclepias.bejeweled.content.gem.GemDefinitions;
import net.blueasclepias.bejeweled.record.gem.GemDefinition;
import net.blueasclepias.bejeweled.record.ore.OreDefinition;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Register Mod Items and Block Items
 */
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    // Addons may add to these
    public static final Map<RegistryObject<Item>, String> STORAGE_BLOCK_ITEMS = new HashMap<>();
    public static final Map<RegistryObject<Item>, OreDefinition> ORE_BLOCK_ITEMS = new HashMap<>();
    public static final Map<Supplier<Item>, GemDefinition> ROUGH_GEMSTONES = new HashMap<>();
    public static final Map<Supplier<Item>, GemDefinition> CUT_GEMSTONES = new HashMap<>();
    public static final Map<Supplier<Item>, GemDefinition> ROUGH_BEADS = new HashMap<>();
    public static final Map<Supplier<Item>, GemDefinition> POLISHED_BEADS = new HashMap<>();

    // ===== WORKTABLE =====
    public static final RegistryObject<Item> GEM_CUTTING_TABLE =
            ITEMS.register(
                    "gem_cutting_table",
                    () -> new BlockItem(
                            ModBlocks.GEM_CUTTING_TABLE.get(),
                            new Item.Properties()
                    )
            );

    // ===== ROUGH GEMS =====
    public static final RegistryObject<Item> ROUGH_AQUAMARINE =
            registerRoughGemstoneItem(GemDefinitions.AQUAMARINE, "rough_aquamarine");
    public static final RegistryObject<Item> ROUGH_RUBY =
            registerRoughGemstoneItem(GemDefinitions.RUBY, "rough_ruby");
    public static final RegistryObject<Item> ROUGH_SAPPHIRE =
            registerRoughGemstoneItem(GemDefinitions.SAPPHIRE, "rough_sapphire");
    public static final RegistryObject<Item> ROUGH_GARNET =
            registerRoughGemstoneItem(GemDefinitions.GARNET, "rough_garnet");
    public static final RegistryObject<Item> ROUGH_TOPAZ =
            registerRoughGemstoneItem(GemDefinitions.TOPAZ, "rough_topaz");
    public static final RegistryObject<Item> ROUGH_TURQUOISE =
            registerRoughGemstoneItem(GemDefinitions.TURQUOISE, "rough_turquoise");
    public static final RegistryObject<Item> ROUGH_JADE =
            registerRoughGemstoneItem(GemDefinitions.JADE, "rough_jade");
    public static final RegistryObject<Item> ROUGH_OPAL =
            registerRoughGemstoneItem(GemDefinitions.OPAL, "rough_opal");
    public static final RegistryObject<Item> ROUGH_EMERALD =
            registerRoughGemstoneItem(GemDefinitions.EMERALD, "rough_emerald");
    public static final RegistryObject<Item> ROUGH_PERIDOT =
            registerRoughGemstoneItem(GemDefinitions.PERIDOT, "rough_peridot");
    public static final RegistryObject<Item> ROUGH_DIAMOND =
            registerRoughGemstoneItem(GemDefinitions.DIAMOND, "rough_diamond");

    // ===== CUT GEMS =====
    public static final RegistryObject<Item> CUT_AQUAMARINE =
            registerCutGemstoneItem(GemDefinitions.AQUAMARINE, "cut_aquamarine");
    public static final RegistryObject<Item> CUT_RUBY =
            registerCutGemstoneItem(GemDefinitions.RUBY, "cut_ruby");
    public static final RegistryObject<Item> CUT_SAPPHIRE =
            registerCutGemstoneItem(GemDefinitions.SAPPHIRE, "cut_sapphire");
    public static final RegistryObject<Item> CUT_GARNET =
            registerCutGemstoneItem(GemDefinitions.GARNET, "cut_garnet");
    public static final RegistryObject<Item> CUT_AMETHYST =
            registerCutGemstoneItem(GemDefinitions.AMETHYST, "cut_amethyst");
    public static final RegistryObject<Item> CUT_TOPAZ =
            registerCutGemstoneItem(GemDefinitions.TOPAZ, "cut_topaz");
    public static final RegistryObject<Item> CUT_TURQUOISE =
            registerCutGemstoneItem(GemDefinitions.TURQUOISE, "cut_turquoise");
    public static final RegistryObject<Item> CUT_JADE =
            registerCutGemstoneItem(GemDefinitions.JADE, "cut_jade");
    public static final RegistryObject<Item> CUT_OPAL =
            registerCutGemstoneItem(GemDefinitions.OPAL, "cut_opal");
    public static final RegistryObject<Item> CUT_PERIDOT =
            registerCutGemstoneItem(GemDefinitions.PERIDOT, "cut_peridot");

    // PEARL IS NEITHER A CORAL POLYP NOR A GEM.
    public static final RegistryObject<Item> UNPOLISHED_PEARL =
            registerRoughBeadItem(GemDefinitions.PEARL, "unpolished_pearl");
    public static final RegistryObject<Item> PEARL =
            registerRoughBeadItem(GemDefinitions.PEARL, "polished_pearl");

    // ===== CORAL POLYPS =====
    public static final RegistryObject<Item> ROUGH_TUBE_CORAL_POLYP =
            registerRoughBeadItem(GemDefinitions.TUBE_CORAL, "rough_tube_coral_polyp");
    public static final RegistryObject<Item> ROUGH_BRAIN_CORAL_POLYP =
            registerRoughBeadItem(GemDefinitions.BRAIN_CORAL, "rough_brain_coral_polyp");
    public static final RegistryObject<Item> ROUGH_BUBBLE_CORAL_POLYP =
            registerRoughBeadItem(GemDefinitions.BUBBLE_CORAL, "rough_bubble_coral_polyp");
    public static final RegistryObject<Item> ROUGH_FIRE_CORAL_POLYP =
            registerRoughBeadItem(GemDefinitions.FIRE_CORAL, "rough_fire_coral_polyp");
    public static final RegistryObject<Item> ROUGH_HORN_CORAL_POLYP =
            registerRoughBeadItem(GemDefinitions.HORN_CORAL, "rough_horn_coral_polyp");

    // ====== CORAL BEADS =====
    public static final RegistryObject<Item> POLISHED_TUBE_CORAL_BEAD =
            registerPolishedBeadItem(GemDefinitions.TUBE_CORAL, "polished_tube_coral_bead");
    public static final RegistryObject<Item> POLISHED_BRAIN_CORAL_BEAD =
            registerPolishedBeadItem(GemDefinitions.BRAIN_CORAL, "polished_brain_coral_bead");
    public static final RegistryObject<Item> POLISHED_BUBBLE_CORAL_BEAD =
            registerPolishedBeadItem(GemDefinitions.BUBBLE_CORAL, "polished_bubble_coral_bead");
    public static final RegistryObject<Item> POLISHED_FIRE_CORAL_BEAD =
            registerPolishedBeadItem(GemDefinitions.FIRE_CORAL, "polished_fire_coral_bead");
    public static final RegistryObject<Item> POLISHED_HORN_CORAL_BEAD =
            registerPolishedBeadItem(GemDefinitions.HORN_CORAL, "polished_horn_coral_bead");

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

    private static RegistryObject<Item> registerRoughGemstoneItem(GemDefinition def, String name) {
        RegistryObject<Item> registry = ITEMS.register(name, () -> new RoughGemstoneItem(new Item.Properties()));
        ROUGH_GEMSTONES.put(registry, def);
        return registry;
    }

    private static RegistryObject<Item> registerCutGemstoneItem(GemDefinition def, String name) {
        RegistryObject<Item> registry = ITEMS.register(name, () -> new Item(new Item.Properties()));
        CUT_GEMSTONES.put(registry, def);
        return registry;
    }

    private static RegistryObject<Item> registerRoughBeadItem(GemDefinition def, String name) {
        RegistryObject<Item> registry = ITEMS.register(name, () -> new RoughBeadItem(new Item.Properties()));
        ROUGH_BEADS.put(registry, def);
        return registry;
    }

    private static RegistryObject<Item> registerPolishedBeadItem(GemDefinition def, String name) {
        RegistryObject<Item> registry = ITEMS.register(name, () -> new Item(new Item.Properties()));
        POLISHED_BEADS.put(registry, def);
        return registry;
    }

    // ===== BLOCK ITEMS =====
    // === STORAGE BLOCK ===
    private static void registerBlocksOfItem() {
        ModBlocks.STORAGE_BLOCKS.forEach(storageBlock -> {
            ITEMS.register(storageBlock.getId().getPath(),
                    () -> new BlockItem(storageBlock.get(), new Item.Properties()));
        });
    }

    // === ORE BLOCKS ===
    private static void registerOreBlocksItem() {
        ModBlocks.ORE_BLOCKS.forEach((definition, entry) -> {
            entry.forEach((variant, block) ->
                    ORE_BLOCK_ITEMS.put(
                            ITEMS.register(block.getId().getPath(),
                            () -> new BlockItem(block.get(), new Item.Properties())),
                            definition
                    )
            );
        });
    }

    // === CORAL POLYP BLOCKS ===
    public static void registerCoralPolypBlockItem(){
        ModBlocks.CORAL_POLYP_BLOCKS.forEach(block -> {
            ITEMS.register(
                    block.getId().getPath(),
                    () -> new BlockItem(block.get(), new Item.Properties())
            );
        });
    }

    // TODO: make one for cut gems
    // ===== Item Distinction =====
    public static class RoughGemstoneItem extends Item {
        public RoughGemstoneItem(Properties properties) {
            super(properties);
        }

        @Override
        public void appendHoverText(ItemStack stack,
                                    @Nullable Level level,
                                    List<Component> tooltip,
                                    TooltipFlag flag) {
            tooltip.add(Component
                    .translatable("tooltip.bejeweled.rough_gemstone")
                    .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }
    }

    public static class RoughBeadItem extends Item {
        public RoughBeadItem(Properties properties) {
            super(properties);
        }

        @Override
        public void appendHoverText(ItemStack stack,
                                    @Nullable Level level,
                                    List<Component> tooltip,
                                    TooltipFlag flag) {
            tooltip.add(Component
                    .translatable("tooltip.bejeweled.rough_bead")
                    .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }
    }

    // ===== Static Initializer =====
    static {
        registerOreBlocksItem();
        registerBlocksOfItem();
        registerCoralPolypBlockItem();
        ROUGH_GEMSTONES.put(() -> Items.AMETHYST_SHARD, GemDefinitions.AMETHYST);
        CUT_GEMSTONES.put(() -> Items.DIAMOND, GemDefinitions.DIAMOND);
        CUT_GEMSTONES.put(() -> Items.EMERALD, GemDefinitions.EMERALD);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
