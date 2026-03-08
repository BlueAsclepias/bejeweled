package net.blueasclepias.bejeweled.common.data.gem.loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemCategory;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemRarity;
import net.blueasclepias.bejeweled.common.data.gem.registry.GemDefinitionRegistry;
import net.blueasclepias.bejeweled.common.data.gem.serialization.GemDefinitionCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static net.blueasclepias.bejeweled.Bejeweled.LOGGER;

public class GemDefinitionLoader extends SimpleJsonResourceReloadListener {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public GemDefinitionLoader() {
        super(GSON, "gems");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons,
                         @NotNull ResourceManager manager,
                         @NotNull ProfilerFiller profiler) {
        LOGGER.info("Reloading {} gem definitions", jsons.size());
        Map<ResourceLocation, GemDefinition> loaded = new HashMap<>();

        for (Map.Entry<ResourceLocation, JsonElement> entry : jsons.entrySet()) {
            ResourceLocation fileId = entry.getKey();
            try {
                JsonObject json = entry.getValue().getAsJsonObject();
                GemDefinition def = GemDefinitionCodec.fromJson(json);
                loaded.put(def.id(), def);
            } catch (Exception e) {
                LOGGER.error(
                        "Failed to load gem definition {}: {}",
                        fileId,
                        e.getMessage()
                );
            }
        }

        GemDefinitionRegistry.setDefinitions(loaded);
    }
}
