package net.blueasclepias.bejeweled.common.data.gem.loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemCategory;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemRarity;
import net.blueasclepias.bejeweled.common.data.gem.registry.GemDefinitionRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.HashMap;
import java.util.Map;

import static net.blueasclepias.bejeweled.Bejeweled.LOGGER;

public class GemDefinitionLoader extends SimpleJsonResourceReloadListener {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public GemDefinitionLoader() {
        super(GSON, "gems");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager manager, ProfilerFiller profiler) {
        LOGGER.info("Reloading {} gem definitions", jsons.size());
        Map<ResourceLocation, GemDefinition> loaded = new HashMap<>();

        for (Map.Entry<ResourceLocation, JsonElement> entry : jsons.entrySet()) {

            JsonObject json = entry.getValue().getAsJsonObject();

            ResourceLocation itemId = ResourceLocation.parse(json.get("id").getAsString());
            int color = Integer.decode(json.get("color").getAsString());
            boolean generateLoot = json.has("generate_loot") && json.get("generate_loot").getAsBoolean();
            GemRarity rarity = GemRarity.fromString(json.get("rarity").getAsString());
            GemCategory category = GemCategory.fromString(json.get("category").getAsString());
            //GemEffects
            //GemPassives
            GemDefinition def = new GemDefinition(itemId, color, generateLoot, rarity, category, null, null);

            loaded.put(itemId, def);
        }

        GemDefinitionRegistry.setDefinitions(loaded);
    }
}
