package net.blueasclepias.bejeweled.common.data.gem.loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
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

/**
 * Datapack reload listener that discovers gem definition JSON files under any namespace's {@code gems/} folder.
 * This is the server-side entry point for Bejeweled's data-driven gem pipeline: this mod, other mods, and ordinary
 * datapacks can all add gemstones or beads by shipping compatible JSON, without a helper integration mod. Each file
 * is parsed independently through {@link GemDefinitionCodec}; failures are logged and skipped so one bad resource
 * does not abort the rest of the reload.
 */
public class GemDefinitionLoader extends SimpleJsonResourceReloadListener {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public GemDefinitionLoader() {
        super(GSON, "gems");
    }

    /**
     * Parses every discovered JSON resource, merges the successfully decoded definitions into a single map keyed by
     * each definition's declared id, and swaps the runtime registry once at the end of the reload pass.
     */
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
