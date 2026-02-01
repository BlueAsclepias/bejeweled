package net.blueasclepias.bejeweled.registry;

import net.blueasclepias.bejeweled.Bejeweled;
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
import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Bejeweled.MOD_ID);

    static {
        registerOreBlocksItemType();
        registerBlocksOfItem();
    }

    public static final RegistryObject<Item> CORAL_POLYP = ITEMS.register(
            ModBlocks.CORAL_POLYP.getId().getPath(),
            () -> new BlockItem(ModBlocks.CORAL_POLYP.get(), new Item.Properties())
    );

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

    public static final RegistryObject<Item> ROUGH_CORAL_POLYP = ITEMS.register(
            "rough_coral_polyp",
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

    public static final RegistryObject<Item> CUT_OPAL =
            ITEMS.register("cut_opal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PEARL =
            ITEMS.register("pearl", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUT_CORAL =
            ITEMS.register("cut_coral", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUT_PERIDOT =
            ITEMS.register("cut_peridot", () -> new Item(new Item.Properties()));

    // TODO: Onyx, Jaspe, Sodalite, Aventurine, Unakite, Fluorine

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
            entry.forEach( (base, block) ->
                    ITEMS.register(block.getId().getPath(),
                            () -> new BlockItem(block.get(), new Item.Properties()))
            );
        });
    }

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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
