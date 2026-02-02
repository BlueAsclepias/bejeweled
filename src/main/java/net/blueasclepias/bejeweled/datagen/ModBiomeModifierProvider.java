package net.blueasclepias.bejeweled.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.blueasclepias.bejeweled.feature.biomeplacement.BiomeFeaturePlacement;
import net.blueasclepias.bejeweled.feature.biomeplacement.ModBiomePlacements;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModBiomeModifierProvider implements DataProvider {

    private final PackOutput output;

    public ModBiomeModifierProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (BiomeFeaturePlacement placement : ModBiomePlacements.all()) {
            JsonObject json = new JsonObject();
            json.addProperty("type", "forge:add_features");

            // biome or biome tag
            placement.biome().ifLeft(
                    biome -> json.addProperty("biomes", biome.location().toString())
            );
            placement.biome().ifRight(
                    tag -> json.addProperty("biomes", "#" + tag.location())
            );

            JsonArray features = new JsonArray();
            features.add(placement.feature().location().toString());
            json.add("features", features);

            json.addProperty(
                    "step",
                    placement.step().getName()
            );

            Path path = output.getOutputFolder().resolve(
                    "data/bejeweled/forge/biome_modifier/"
                            + placement.feature().location().getPath()
                            + ".json"
            );

            futures.add(DataProvider.saveStable(cache, json, path));
        }

        return CompletableFuture.allOf(
                futures.toArray(CompletableFuture[]::new)
        );
    }

    @Override
    public String getName() {
        return "Bejeweled Biome Modifiers";
    }
}

