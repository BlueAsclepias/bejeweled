package net.blueasclepias.bejeweled.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.blueasclepias.bejeweled.enums.OreBase;
import net.blueasclepias.bejeweled.interfaces.BiomeFilter;
import net.blueasclepias.bejeweled.oredata.OreTypes;
import net.blueasclepias.bejeweled.record.OreBaseGen;
import net.blueasclepias.bejeweled.record.OreGenSettings;
import net.blueasclepias.bejeweled.record.OreType;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.blueasclepias.bejeweled.util.JsonUtils.*;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class ModOreWorldGenProvider implements DataProvider {
    private final PackOutput output;
    private final PackOutput.PathProvider worldgenPath;
    private final PackOutput.PathProvider biomeModifierPath;

    public ModOreWorldGenProvider(PackOutput output) {
        this.output = output;
        this.worldgenPath = output.createPathProvider(
                PackOutput.Target.DATA_PACK,
                "worldgen"
        );
        this.biomeModifierPath = output.createPathProvider(
                PackOutput.Target.DATA_PACK,
                "forge/biome_modifier"
        );
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> tasks = new ArrayList<>();

        for (OreType ore : OreTypes.ALL) {
            for (OreBaseGen baseGen : ore.bases()) {

                String id = baseGen.base().name().toLowerCase() + "_" + ore.name() + "_ore";

                tasks.add(writeConfiguredFeature(cache, baseGen, id));
                tasks.add(writePlacedFeature(cache, baseGen, id));
                tasks.add(writeBiomeModifier(cache, id, baseGen.genSettings()));
            }
        }

        return CompletableFuture.allOf(tasks.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Bejeweled Worldgen";
    }

    private CompletableFuture<?> writeConfiguredFeature(
            CachedOutput cache,
            OreBaseGen baseGen,
            String id
    ) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "minecraft:ore");

        JsonObject config = new JsonObject();
        config.addProperty("size", baseGen.genSettings().veinSize());
        config.addProperty("discard_chance_on_air_exposure", 0.0);

        JsonArray targets = new JsonArray();
        JsonObject target = new JsonObject();

        JsonObject ruleTest = new JsonObject();
        OreBase base = baseGen.base();

        switch (base) {
            case STONE -> {
                ruleTest.addProperty("predicate_type", "minecraft:tag_match");
                ruleTest.addProperty("tag", "minecraft:stone_ore_replaceables");
            }
            case DEEPSLATE -> {
                ruleTest.addProperty("predicate_type", "minecraft:tag_match");
                ruleTest.addProperty("tag", "minecraft:deepslate_ore_replaceables");
            }
            default -> {
                ruleTest.addProperty("predicate_type", "minecraft:block_match");
                ruleTest.addProperty(
                        "block",
                        ForgeRegistries.BLOCKS.getKey(base.vanilla()).toString()
                );
            }
        }

        JsonObject state = new JsonObject();
        state.addProperty(
                "Name",
                MOD_ID + ":" + id
        );

        target.add("target", ruleTest);
        target.add("state", state);
        targets.add(target);

        config.add("targets", targets);
        root.add("config", config);

        return DataProvider.saveStable(
                cache,
                root,
                worldgenPath.json(
                        ResourceLocation.fromNamespaceAndPath(
                                MOD_ID,
                                "configured_feature/" + id
                        )
                )
        );
    }

    private CompletableFuture<?> writePlacedFeature(
            CachedOutput cache,
            OreBaseGen baseGen,
            String id
    ) {
        OreGenSettings gen = baseGen.genSettings();

        JsonObject root = new JsonObject();
        root.addProperty("feature", MOD_ID + ":" + id);

        JsonArray placement = new JsonArray();

        placement.add(obj("type", "minecraft:count", "count", gen.veinsPerChunk()));
        placement.add(obj("type", "minecraft:in_square"));

        JsonObject height = new JsonObject();
        height.addProperty("type", "minecraft:uniform");
        height.add("min_inclusive", abs(gen.minY()));
        height.add("max_inclusive", abs(gen.maxY()));

        placement.add(obj("type", "minecraft:height_range", "height", height));
        placement.add(obj("type", "minecraft:biome"));

        root.add("placement", placement);

        return DataProvider.saveStable(
                cache,
                root,
                worldgenPath.json(
                        ResourceLocation.fromNamespaceAndPath(
                                MOD_ID,
                                "placed_feature/" + id
                        )
                )
        );
    }

    private CompletableFuture<?> writeBiomeModifier(
            CachedOutput cache,
            String id,
            OreGenSettings gen
    ) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "forge:add_features");
        root.addProperty("step", "underground_ores");

        BiomeFilter filter = gen.biomeFilter();

        if (filter == null) {
            root.add("biomes", tag("minecraft:is_overworld"));
        }
        else if (filter instanceof BiomeFilter.Tag t) {
            root.add("biomes", tag(t.tag().toString()));
        }
        else if (filter instanceof BiomeFilter.List l) {
            JsonArray arr = new JsonArray();
            l.biomes().forEach(b -> arr.add(b.toString()));
            root.add("biomes", arr);
        }

        JsonArray features = new JsonArray();
        features.add(MOD_ID + ":" + id);
        root.add("features", features);

        return DataProvider.saveStable(
                cache,
                root,
                biomeModifierPath.json(
                        fromNamespaceAndPath(
                                MOD_ID,
                                id
                        )
                )
        );
    }
}
