package net.blueasclepias.bejeweled.datagen.provider.gem;

import net.blueasclepias.bejeweled.common.data.gem.defaults.DefaultGemDefinitions;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class GemDefinitionProvider implements DataProvider {

    private final PackOutput output;

    public GemDefinitionProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Path basePath = output.getOutputFolder()
                .resolve("data")
                .resolve(MOD_ID)
                .resolve("gems");

        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (Map.Entry<ResourceLocation, GemDefinition> entry : DefaultGemDefinitions.getAll().entrySet()) {
            ResourceLocation id = entry.getKey();
            GemDefinition def = entry.getValue();
            Path path = basePath.resolve(id.getPath() + ".json");
            futures.add(DataProvider.saveStable(cache, def.toJson(), path));
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    @Override
    public String getName() {
        return "Bejeweled Gem Definitions";
    }

}
