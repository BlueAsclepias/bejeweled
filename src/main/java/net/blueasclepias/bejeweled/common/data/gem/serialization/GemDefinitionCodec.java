package net.blueasclepias.bejeweled.common.data.gem.serialization;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemCategory;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemRarity;
import net.minecraft.resources.ResourceLocation;

public class GemDefinitionCodec {

    private static ResourceLocation getId(JsonObject json, String key) {
        if (!json.has(key))
            throw new JsonParseException("Missing required field: " + key);

        try {
            return ResourceLocation.parse(json.get(key).getAsString());
        } catch (Exception e) {
            throw new JsonParseException("Invalid ResourceLocation for " + key);
        }
    }

    private static <T extends Enum<T>> T getEnum(
            JsonObject json,
            String key,
            Class<T> type,
            T def
    ) {
        if (!json.has(key)) return def;

        try {
            return Enum.valueOf(
                    type,
                    json.get(key).getAsString().toUpperCase()
            );
        } catch (Exception ignored) {
            return def;
        }
    }

    private static boolean getBoolean(JsonObject json, String key, boolean bool) {
        if (!json.has(key)) return bool;
        try {
            return json.get(key).getAsBoolean();
        } catch (Exception ignored) {
            return bool;
        }
    }

    private static int getInt(JsonObject json, String key, int num) {
        if (!json.has(key)) return num;
        try {
            return Integer.decode(json.get(key).getAsString());
        } catch (Exception ignored) {
            return num;
        }
    }

    public static GemDefinition fromJson(JsonObject json) {

        ResourceLocation id = getId(json, "id");
        GemRarity rarity = getEnum(json, "rarity", GemRarity.class, GemRarity.COMMON);
        GemCategory category = getEnum(json, "category", GemCategory.class, GemCategory.GEMSTONE);
        int color = getInt(json, "color", 0);
        boolean generateLoot = getBoolean(json, "generate_loot", false);

        //GemEffects
        //GemPassives

        return new GemDefinition(
                id,
                color,
                generateLoot,
                rarity,
                category,
                null,
                null
        );
    }

}