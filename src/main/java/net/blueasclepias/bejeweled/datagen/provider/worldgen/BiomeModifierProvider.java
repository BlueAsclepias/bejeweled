package net.blueasclepias.bejeweled.datagen.provider.worldgen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.blueasclepias.bejeweled.server.worldgen.placement.BiomeFeaturePlacement;
import net.blueasclepias.bejeweled.server.worldgen.placement.BiomePlacements;
import net.blueasclepias.bejeweled.server.worldgen.util.IBiomeFilter;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Writes Forge biome modifier JSON files from the mod's {@link BiomeFeaturePlacement} definitions.
 * Each entry becomes a {@code forge:add_features} modifier whose biome targeting is encoded either as a biome tag
 * reference or as an explicit biome id list, depending on the {@link IBiomeFilter} variant in use.
 */
public class BiomeModifierProvider implements DataProvider {

    private final PackOutput output;

    public BiomeModifierProvider(PackOutput output) {
        this.output = output;
    }

    /**
     * Serializes every placement in {@link BiomePlacements#ALL} to
     * {@code data/bejeweled/forge/biome_modifier/<name>.json}.
     * {@link net.blueasclepias.bejeweled.server.worldgen.util.IBiomeFilter.Tag} filters become a single
     * {@code "#namespace:path"} {@code biomes} value, while
     * {@link net.blueasclepias.bejeweled.server.worldgen.util.IBiomeFilter.List} filters become a JSON array of biome
     * ids; the placed feature ids and generation step are copied directly into Forge's {@code add_features} schema.
     */
    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (BiomeFeaturePlacement placement : BiomePlacements.ALL.values()) {
            JsonObject json = new JsonObject();
            json.addProperty("type", "forge:add_features");

            IBiomeFilter filter = placement.IBiomeFilter();
            if(filter instanceof IBiomeFilter.Tag tagFilter){
                json.addProperty(
                        "biomes",
                        "#" + tagFilter.tag().toString()
                );
            } else if(filter instanceof IBiomeFilter.List biomeList) {
                JsonArray arr = new JsonArray();
                biomeList.biomes().forEach(b -> arr.add(b.toString()));
                json.add("biomes", arr);
            }

            JsonArray features = new JsonArray();
            for (ResourceKey<PlacedFeature> feature : placement.features()) {
                features.add(feature.location().toString());
            }
            json.add("features", features);

            json.addProperty(
                    "step",
                    placement.step().getName()
            );

            Path path = output.getOutputFolder().resolve(
                    "data/bejeweled/forge/biome_modifier/"
                            + placement.name()
                            + ".json"
            );

            futures.add(DataProvider.saveStable(cache, json, path));
        }

        return CompletableFuture.allOf(
                futures.toArray(CompletableFuture[]::new)
        );
    }

    @Override
    public @NotNull String getName() {
        return "Bejeweled Biome Modifiers";
    }
}
