package net.blueasclepias.bejeweled.registry;

import net.blueasclepias.bejeweled.record.CoreType;
import net.blueasclepias.bejeweled.record.OreType;
import net.blueasclepias.bejeweled.types.gem.CoreTypes;
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
    public static final Map<RegistryObject<Item>, OreType> ORE_BLOCK_ITEMS = new HashMap<>();
    public static final Map<Supplier<Item>, CoreType> ROUGH_GEMS = new HashMap<>();
    public static final Map<Supplier<Item>, CoreType> CUT_GEMS = new HashMap<>();
    public static final Map<Supplier<Item>, CoreType> ROUGH_BEADS = new HashMap<>();
    public static final Map<Supplier<Item>, CoreType> POLISHED_BEADS = new HashMap<>();

    // ===== ROUGH GEMS =====
    public static final RegistryObject<Item> ROUGH_AQUAMARINE =
            registerRoughGemItem(CoreTypes.AQUAMARINE, "rough_aquamarine");
    public static final RegistryObject<Item> ROUGH_RUBY =
            registerRoughGemItem(CoreTypes.RUBY, "rough_ruby");
    public static final RegistryObject<Item> ROUGH_SAPPHIRE =
            registerRoughGemItem(CoreTypes.SAPPHIRE, "rough_sapphire");
    public static final RegistryObject<Item> ROUGH_GARNET =
            registerRoughGemItem(CoreTypes.GARNET, "rough_garnet");
    public static final RegistryObject<Item> ROUGH_TOPAZ =
            registerRoughGemItem(CoreTypes.TOPAZ, "rough_topaz");
    public static final RegistryObject<Item> ROUGH_TURQUOISE =
            registerRoughGemItem(CoreTypes.TURQUOISE, "rough_turquoise");
    public static final RegistryObject<Item> ROUGH_JADE =
            registerRoughGemItem(CoreTypes.JADE, "rough_jade");
    public static final RegistryObject<Item> ROUGH_OPAL =
            registerRoughGemItem(CoreTypes.OPAL, "rough_opal");
    public static final RegistryObject<Item> ROUGH_EMERALD =
            registerRoughGemItem(CoreTypes.EMERALD, "rough_emerald");
    public static final RegistryObject<Item> ROUGH_PERIDOT =
            registerRoughGemItem(CoreTypes.PERIDOT, "rough_peridot");
    public static final RegistryObject<Item> ROUGH_DIAMOND =
            registerRoughGemItem(CoreTypes.DIAMOND, "rough_diamond");

    // ===== CUT GEMS =====
    public static final RegistryObject<Item> CUT_AQUAMARINE =
            registerCutGemItem(CoreTypes.AQUAMARINE, "cut_aquamarine");
    public static final RegistryObject<Item> CUT_RUBY =
            registerCutGemItem(CoreTypes.RUBY, "cut_ruby");
    public static final RegistryObject<Item> CUT_SAPPHIRE =
            registerCutGemItem(CoreTypes.SAPPHIRE, "cut_sapphire");
    public static final RegistryObject<Item> CUT_GARNET =
            registerCutGemItem(CoreTypes.GARNET, "cut_garnet");
    public static final RegistryObject<Item> CUT_AMETHYST =
            registerCutGemItem(CoreTypes.AMETHYST, "cut_amethyst");
    public static final RegistryObject<Item> CUT_TOPAZ =
            registerCutGemItem(CoreTypes.TOPAZ, "cut_topaz");
    public static final RegistryObject<Item> CUT_TURQUOISE =
            registerCutGemItem(CoreTypes.TURQUOISE, "cut_turquoise");
    public static final RegistryObject<Item> CUT_JADE =
            registerCutGemItem(CoreTypes.JADE, "cut_jade");
    public static final RegistryObject<Item> CUT_OPAL =
            registerCutGemItem(CoreTypes.OPAL, "cut_opal");
    public static final RegistryObject<Item> CUT_PERIDOT =
            registerCutGemItem(CoreTypes.PERIDOT, "cut_peridot");

    // TODO: BLACK OPAL
    // TODO: BLACK PEARL (Bead)
    // TODO: AMBAR (Bead)
    // TODO: JET (Bead)
    // TODO: FLUORITE
    // TODO: JASPER
    // TODO: ONYX (bead)
    // TODO: AGATE
    // TODO: ALEXANDRITE
    // TODO: CIRTINE
    // TODO: ZIRCON
    // TODO: BLOODSTONE


    // PEARL IS NEITHER A CORAL POLYP NOR A GEM.
    public static final RegistryObject<Item> UNPOLISHED_PEARL =
            registerRoughBeadItem(CoreTypes.PEARL, "unpolished_pearl");
    public static final RegistryObject<Item> PEARL =
            registerRoughBeadItem(CoreTypes.PEARL, "polished_pearl");

    // ===== CORAL POLYPS =====
    public static final RegistryObject<Item> ROUGH_TUBE_CORAL_POLYP =
            registerRoughBeadItem(CoreTypes.TUBE_CORAL, "rough_tube_coral_polyp");
    public static final RegistryObject<Item> ROUGH_BRAIN_CORAL_POLYP =
            registerRoughBeadItem(CoreTypes.BRAIN_CORAL, "rough_brain_coral_polyp");
    public static final RegistryObject<Item> ROUGH_BUBBLE_CORAL_POLYP =
            registerRoughBeadItem(CoreTypes.BUBBLE_CORAL, "rough_bubble_coral_polyp");
    public static final RegistryObject<Item> ROUGH_FIRE_CORAL_POLYP =
            registerRoughBeadItem(CoreTypes.FIRE_CORAL, "rough_fire_coral_polyp");
    public static final RegistryObject<Item> ROUGH_HORN_CORAL_POLYP =
            registerRoughBeadItem(CoreTypes.HORN_CORAL, "rough_horn_coral_polyp");

    // ====== CORAL BEADS =====
    public static final RegistryObject<Item> POLISHED_TUBE_CORAL_BEAD =
            registerPolishedBeadItem(CoreTypes.TUBE_CORAL, "polished_tube_coral_bead");
    public static final RegistryObject<Item> POLISHED_BRAIN_CORAL_BEAD =
            registerPolishedBeadItem(CoreTypes.BRAIN_CORAL, "polished_brain_coral_bead");
    public static final RegistryObject<Item> POLISHED_BUBBLE_CORAL_BEAD =
            registerPolishedBeadItem(CoreTypes.BUBBLE_CORAL, "polished_bubble_coral_bead");
    public static final RegistryObject<Item> POLISHED_FIRE_CORAL_BEAD =
            registerPolishedBeadItem(CoreTypes.FIRE_CORAL, "polished_fire_coral_bead");
    public static final RegistryObject<Item> POLISHED_HORN_CORAL_BEAD =
            registerPolishedBeadItem(CoreTypes.HORN_CORAL, "polished_horn_coral_bead");

    // ===== JEWEL BASES =====
    // TODO: THESE SHOULD BE CURIOS NOT REGULAR ITEMS
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

    private static RegistryObject<Item> registerRoughGemItem(CoreType type, String name) {
        RegistryObject<Item> registry = ITEMS.register(name, () -> new RoughGemItem(new Item.Properties()));
        ROUGH_GEMS.put(registry, type);
        return registry;
    }

    private static RegistryObject<Item> registerCutGemItem(CoreType type, String name) {
        RegistryObject<Item> registry = ITEMS.register(name, () -> new Item(new Item.Properties()));
        CUT_GEMS.put(registry, type);
        return registry;
    }

    private static RegistryObject<Item> registerRoughBeadItem(CoreType type, String name) {
        RegistryObject<Item> registry = ITEMS.register(name, () -> new RoughBeadItem(new Item.Properties()));
        ROUGH_BEADS.put(registry, type);
        return registry;
    }

    private static RegistryObject<Item> registerPolishedBeadItem(CoreType type, String name) {
        RegistryObject<Item> registry = ITEMS.register(name, () -> new Item(new Item.Properties()));
        POLISHED_BEADS.put(registry, type);
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
    private static void registerOreBlocksItemType() {
        ModBlocks.ORE_BLOCKS.forEach((type, entry) -> {
            entry.forEach((base, block) ->
                    ORE_BLOCK_ITEMS.put(
                            ITEMS.register(block.getId().getPath(),
                            () -> new BlockItem(block.get(), new Item.Properties())),
                            type
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
    public static class RoughGemItem extends Item {
        public RoughGemItem(Properties properties) {
            super(properties);
        }

        @Override
        public void appendHoverText(ItemStack stack,
                                    @Nullable Level level,
                                    List<Component> tooltip,
                                    TooltipFlag flag) {
            tooltip.add(Component
                    .translatable("tooltip.bejeweled.rough_gem")
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
        registerOreBlocksItemType();
        registerBlocksOfItem();
        registerCoralPolypBlockItem();
        ROUGH_GEMS.put(() -> Items.AMETHYST_SHARD, CoreTypes.AMETHYST);
        CUT_GEMS.put(() -> Items.DIAMOND, CoreTypes.DIAMOND);
        CUT_GEMS.put(() -> Items.EMERALD, CoreTypes.EMERALD);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
