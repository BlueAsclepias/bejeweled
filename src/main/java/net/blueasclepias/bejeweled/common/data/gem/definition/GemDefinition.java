package net.blueasclepias.bejeweled.common.data.gem.definition;

import com.google.gson.JsonObject;
import net.blueasclepias.bejeweled.common.api.IGemDefinition;
import net.minecraft.resources.ResourceLocation;

public record GemDefinition (
        ResourceLocation id,
        int color,
        boolean generateLoot,
        GemRarity rarity,
        GemCategory category,
        GemEffect effects, // TODO. null for now
        GemAttribute passives // TODO. null for now
) implements IGemDefinition {
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("id", id().toString());
        json.addProperty("color", color());
        json.addProperty("generate_loot", generateLoot());
        json.addProperty("rarity", rarity().name().toLowerCase());
        json.addProperty("category", category().name().toLowerCase());
        //json.addProperty("effects", );
        //json.addProperty("attributes", );
        return json;
    }
}
