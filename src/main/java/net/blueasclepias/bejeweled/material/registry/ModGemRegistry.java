package net.blueasclepias.bejeweled.material.registry;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.blueasclepias.bejeweled.item.ProcessedGemItem;
import net.blueasclepias.bejeweled.item.RawGemItem;
import net.blueasclepias.bejeweled.material.definition.gem.GemCategory;
import net.blueasclepias.bejeweled.material.definition.gem.GemDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ModGemRegistry {

    private static final Map<ResourceLocation, GemDefinition> DEFINITIONS = new HashMap<>();
    private static final Map<Item, GemDefinition> ITEM_BINDINGS = new HashMap<>();
    private static final Multimap<GemDefinition, Item> ITEMS_BY_DEFINITION = HashMultimap.create();

    public static void registerDefinition(ResourceLocation id, GemDefinition def) {
        DEFINITIONS.put(id, def);
    }

    public static void bind(Item item, @NotNull ResourceLocation id) {
        GemDefinition def = Objects.requireNonNull(DEFINITIONS.get(id));
        ITEM_BINDINGS.put(item, def);
        ITEMS_BY_DEFINITION.put(def, item);
    }

    public static Collection<Item> getItems(GemDefinition def) {
        return ITEMS_BY_DEFINITION.get(def);
    }

    public static @Nullable GemDefinition get(ItemStack stack) {
        return ITEM_BINDINGS.get(stack.getItem());
    }

    public static boolean isProcessed(Item item) {
        return item instanceof ProcessedGemItem;
    }

    public static boolean isProcessed(ItemStack stack) {
        return isProcessed(stack.getItem());
    }

    public static boolean isRaw(Item item) {
        return item instanceof RawGemItem;
    }

    public static boolean isRaw(ItemStack stack) {
        return isRaw(stack.getItem());
    }

    public static Map<Item, GemDefinition> getAll(GemCategory category, boolean processed) {
        return ITEM_BINDINGS.entrySet().stream()
                .filter(entry -> entry.getValue().category() == category)
                .filter(entry -> isProcessed(entry.getKey()) == processed)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<Item, GemDefinition> getAllRaw() {
        return ITEM_BINDINGS.entrySet().stream()
                .filter(entry -> isRaw(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<Item, GemDefinition> getAllProcessed() {
        return ITEM_BINDINGS.entrySet().stream()
                .filter(entry -> isProcessed(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}