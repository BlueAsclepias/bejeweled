package net.blueasclepias.bejeweled.common.data.gem.registry;

import net.blueasclepias.bejeweled.common.data.gem.definition.GemCategory;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Runtime registry of the gem definitions that were successfully loaded for the current datapack state.
 * The whole registry is replaced on each reload via {@link #setDefinitions(Map)}, and that same rebuild also
 * recreates the {@code GEM_INDEX} table in the iteration order of the incoming map. Those indices are only
 * stable for a given loaded set and are consumed by client model predicates to choose processed-gem variants.
 * Callers can resolve a definition from either an {@link Item} instance or an explicit {@link ResourceLocation},
 * and can also request filtered snapshots for loot-enabled or category-specific subsets.
 */
public final class GemDefinitionRegistry {
    private static Map<ResourceLocation, GemDefinition> DEFINITIONS = new HashMap<>();
    private static final Map<ResourceLocation, Integer> GEM_INDEX = new HashMap<>();

    /**
     * Replaces the current registry contents with the freshly reloaded definitions and rebuilds the model index map
     * from scratch. Indices start at {@code 1} and follow the iteration order of {@code defs.keySet()}.
     */
    public static void setDefinitions(Map<ResourceLocation, GemDefinition> defs) {
        DEFINITIONS = defs;
        GEM_INDEX.clear();

        int i = 1;
        for (ResourceLocation id : defs.keySet()) {
            GEM_INDEX.put(id, i++);
        }
    }

    /**
     * Resolves a definition from the registry name of a concrete item. This is mainly used when other systems only
     * have an {@link Item} instance and need to see whether that item corresponds to a loaded gem definition.
     */
    public static GemDefinition getDefinition(Item item) {
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(item);
        return DEFINITIONS.get(id);
    }

    /**
     * Resolves a definition directly from its declared gem id, such as the id stored in {@code GemState} NBT or read
     * from datapack JSON.
     */
    public static GemDefinition getDefinition(ResourceLocation id) {
        return DEFINITIONS.get(id);
    }

    public static Integer getIndex(Item item) {
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(item);
        return GEM_INDEX.get(id);
    }

    public static Integer getIndex(ResourceLocation id) {
        return GEM_INDEX.get(id);
    }

    public static boolean isGem(Item item) {
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(item);
        return DEFINITIONS.containsKey(id);
    }

    public static Map<ResourceLocation, GemDefinition> getAll() {
        return DEFINITIONS;
    }

    /**
     * Returns a new map containing only definitions that opted into loot generation.
     */
    public static Map<ResourceLocation, GemDefinition> getAllLoot() {
        return DEFINITIONS.entrySet().stream()
                .filter(e -> e.getValue().generateLoot())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Returns a new map containing only definitions in the requested category.
     */
    public static Map<ResourceLocation, GemDefinition> getAllByCategory(GemCategory category){
        return DEFINITIONS.entrySet().stream()
                .filter(e -> e.getValue().category() == category)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Returns a new map containing only loot-enabled definitions in the requested category.
     */
    public static Map<ResourceLocation, GemDefinition> getAllLootByCategory(GemCategory category){
        return  getAllLoot().entrySet().stream()
                .filter(e -> e.getValue().category() == category)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
