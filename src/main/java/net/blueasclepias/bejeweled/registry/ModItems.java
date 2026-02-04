package net.blueasclepias.bejeweled.registry;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Register Mod Items and Block Items
 */
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    // Addons may add to these
    public static final List<RegistryObject<Item>> ROUGH_CORAL_POLYPS = new ArrayList<>();
    public static final List<RegistryObject<Item>> GEMS = new ArrayList<>();
    public static final List<RegistryObject<Item>> ROUGH_GEMS = new ArrayList<>();

    // ===== ROUGH GEMS =====
    public static final RegistryObject<Item> ROUGH_AQUAMARINE = ITEMS.register(
            "rough_aquamarine",
            () -> new RawGemItem(new Item.Properties())
    );

    public static final RegistryObject<Item> ROUGH_RUBY = ITEMS.register(
            "rough_ruby",
            () -> new RawGemItem(new Item.Properties())
    );

    public static final RegistryObject<Item> ROUGH_SAPPHIRE = ITEMS.register(
            "rough_sapphire",
            () -> new RawGemItem(new Item.Properties())
    );

    public static final RegistryObject<Item> ROUGH_GARNET = ITEMS.register(
            "rough_garnet",
            () -> new RawGemItem(new Item.Properties())
    );

    public static final RegistryObject<Item> ROUGH_TOPAZ = ITEMS.register(
            "rough_topaz",
            () -> new RawGemItem(new Item.Properties())
    );

    public static final RegistryObject<Item> ROUGH_TURQUOISE = ITEMS.register(
            "rough_turquoise",
            () -> new RawGemItem(new Item.Properties())
    );

    public static final RegistryObject<Item> ROUGH_JADE = ITEMS.register(
            "rough_jade",
            () -> new RawGemItem(new Item.Properties())
    );

    public static final RegistryObject<Item> ROUGH_OPAL = ITEMS.register(
            "rough_opal",
            () -> new RawGemItem(new Item.Properties())
    );

    public static final RegistryObject<Item> ROUGH_EMERALD = ITEMS.register(
            "rough_emerald",
            () -> new RawGemItem(new Item.Properties())
    );

    public static final RegistryObject<Item> ROUGH_PERIDOT = ITEMS.register(
            "rough_peridot",
            () -> new RawGemItem(new Item.Properties())
    );

    public static final RegistryObject<Item> ROUGH_DIAMOND = ITEMS.register(
            "rough_diamond",
            () -> new RawGemItem(new Item.Properties())
    );

    // ===== CORAL POLYPS =====
    public static final RegistryObject<Item> ROUGH_TUBE_CORAL_POLYP =
            ITEMS.register("rough_tube_coral_polyp", () -> new RawBeadItem(new Item.Properties()));
    public static final RegistryObject<Item> ROUGH_BRAIN_CORAL_POLYP =
            ITEMS.register("rough_brain_coral_polyp", () -> new RawBeadItem(new Item.Properties()));
    public static final RegistryObject<Item> ROUGH_BUBBLE_CORAL_POLYP =
            ITEMS.register("rough_bubble_coral_polyp", () -> new RawBeadItem(new Item.Properties()));
    public static final RegistryObject<Item> ROUGH_FIRE_CORAL_POLYP =
            ITEMS.register("rough_fire_coral_polyp", () -> new RawBeadItem(new Item.Properties()));
    public static final RegistryObject<Item> ROUGH_HORN_CORAL_POLYP =
            ITEMS.register("rough_horn_coral_polyp", () -> new RawBeadItem(new Item.Properties()));

    // ===== GEMS =====
    public static final RegistryObject<Item> CUT_AQUAMARINE =
            ITEMS.register("cut_aquamarine", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUT_RUBY =
            ITEMS.register("cut_ruby", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUT_SAPPHIRE =
            ITEMS.register("cut_sapphire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUT_GARNET =
            ITEMS.register("cut_garnet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUT_AMETHYST =
            ITEMS.register("cut_amethyst", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUT_TOPAZ =
            ITEMS.register("cut_topaz", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUT_TURQUOISE =
            ITEMS.register("cut_turquoise", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUT_JADE =
            ITEMS.register("cut_jade", () -> new Item(new Item.Properties()));

    // TODO: BLACK OPAL
    public static final RegistryObject<Item> CUT_OPAL =
            ITEMS.register("cut_opal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUT_PERIDOT =
            ITEMS.register("cut_peridot", () -> new Item(new Item.Properties()));

    // TODO: FLUORINE
    // TODO: UNAKITE
    // TODO: AVENTURINE
    // TODO: SODALITE
    // TODO: JASPE
    // TODO: ONYX
    // TODO: BLOODSTONE

    // TODO: BLACK PEARL
    public static final RegistryObject<Item> PEARL =
            ITEMS.register("pearl", () -> new RawBeadItem(new Item.Properties()));

    // ===== Coral Variants =====
    public static final RegistryObject<Item> TUBE_CORAL_BEAD =
            ITEMS.register("tube_coral_bead", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRAIN_CORAL_BEAD =
            ITEMS.register("brain_coral_bead", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BUBBLE_CORAL_BEAD =
            ITEMS.register("bubble_coral_bead", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FIRE_CORAL_BEAD =
            ITEMS.register("fire_coral_bead", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HORN_CORAL_BEAD =
            ITEMS.register("horn_coral_bead", () -> new Item(new Item.Properties()));

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

    //                          ===== BLOCK ITEMS =====

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
            entry.forEach( (base, block) ->
                    ITEMS.register(block.getId().getPath(),
                            () -> new BlockItem(block.get(), new Item.Properties()))
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
    public static class RawGemItem extends Item {
        public RawGemItem(Properties properties) {
            super(properties);
        }

        @Override
        public void appendHoverText(ItemStack stack,
                                    @Nullable Level level,
                                    List<Component> tooltip,
                                    TooltipFlag flag) {
            tooltip.add(Component
                    .translatable("tooltip.bejeweled.raw_gem")
                    .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }
    }

    public static class RawBeadItem extends Item {
        public RawBeadItem(Properties properties) {
            super(properties);
        }

        @Override
        public void appendHoverText(ItemStack stack,
                                    @Nullable Level level,
                                    List<Component> tooltip,
                                    TooltipFlag flag) {
            tooltip.add(Component
                    .translatable("tooltip.bejeweled.raw_bead")
                    .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    // ===== Static Initializer =====
    static {
        // === Block Items ===
        registerOreBlocksItemType();
        registerBlocksOfItem();
        registerCoralPolypBlockItem();

        // === Items ===
        ROUGH_GEMS.addAll(
                List.of(
                        ROUGH_AQUAMARINE,
                        ROUGH_RUBY,
                        ROUGH_SAPPHIRE,
                        ROUGH_GARNET,
                        ROUGH_TOPAZ,
                        ROUGH_TURQUOISE,
                        ROUGH_JADE,
                        ROUGH_OPAL,
                        ROUGH_EMERALD,
                        ROUGH_PERIDOT,
                        ROUGH_DIAMOND
                )
        );

        ROUGH_CORAL_POLYPS.addAll(
                List.of(
                        ROUGH_TUBE_CORAL_POLYP,
                        ROUGH_BRAIN_CORAL_POLYP,
                        ROUGH_BUBBLE_CORAL_POLYP,
                        ROUGH_FIRE_CORAL_POLYP,
                        ROUGH_HORN_CORAL_POLYP
                )
        );

        GEMS.addAll(
                List.of(
                        CUT_AQUAMARINE,
                        CUT_RUBY,
                        CUT_SAPPHIRE,
                        CUT_GARNET,
                        CUT_TOPAZ,
                        CUT_TURQUOISE,
                        CUT_JADE,
                        CUT_OPAL,
                        // TODO: REPLACE EMERALD ONCE WE ADD NBTs
                        CUT_PERIDOT,
                        // TODO: REPLACE DIAMOND ONCE WE ADD NBTs
                        TUBE_CORAL_BEAD,
                        BRAIN_CORAL_BEAD,
                        BUBBLE_CORAL_BEAD,
                        FIRE_CORAL_BEAD,
                        HORN_CORAL_BEAD
                )
        );
    }
}
