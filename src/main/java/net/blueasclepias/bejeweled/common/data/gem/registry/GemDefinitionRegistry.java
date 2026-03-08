package net.blueasclepias.bejeweled.common.data.gem.registry;

import net.blueasclepias.bejeweled.common.data.gem.definition.GemCategory;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class GemDefinitionRegistry {
    private static Map<ResourceLocation, GemDefinition> DEFINITIONS = new HashMap<>();
    private static final Map<ResourceLocation, Integer> GEM_INDEX = new HashMap<>();

    public static void setDefinitions(Map<ResourceLocation, GemDefinition> defs) {
        DEFINITIONS = defs;
        GEM_INDEX.clear();

        int i = 1;
        for (ResourceLocation id : defs.keySet()) {
            GEM_INDEX.put(id, i++);
        }
    }

    public static GemDefinition getDefinition(Item item) {
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(item);
        return DEFINITIONS.get(id);
    }

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

    public static Map<ResourceLocation, GemDefinition> getAllLoot() {
        return DEFINITIONS.entrySet().stream()
                .filter(e -> e.getValue().generateLoot())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<ResourceLocation, GemDefinition> getAllByCategory(GemCategory category){
        return DEFINITIONS.entrySet().stream()
                .filter(e -> e.getValue().category() == category)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<ResourceLocation, GemDefinition> getAllLootByCategory(GemCategory category){
        return  getAllLoot().entrySet().stream()
                .filter(e -> e.getValue().category() == category)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
