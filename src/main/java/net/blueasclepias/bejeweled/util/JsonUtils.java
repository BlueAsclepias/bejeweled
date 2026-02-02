package net.blueasclepias.bejeweled.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public final class JsonUtils {
    public static JsonObject obj(String typeKey, String typeValue) {
        JsonObject obj = new JsonObject();
        obj.addProperty(typeKey, typeValue);
        return obj;
    }

    public static JsonObject obj(String typeKey, String typeValue, String key, int value) {
        JsonObject obj = new JsonObject();
        obj.addProperty(typeKey, typeValue);
        obj.addProperty(key, value);
        return obj;
    }

    public static JsonObject obj(String typeKey, String typeValue, String key, JsonObject value) {
        JsonObject obj = new JsonObject();
        obj.addProperty(typeKey, typeValue);
        obj.add(key, value);
        return obj;
    }

    public static JsonObject abs(int value) {
        JsonObject obj = new JsonObject();
        obj.addProperty("absolute", value);
        return obj;
    }

    public static JsonPrimitive tag(String tagId) {
        return new JsonPrimitive("#" + tagId);
    }
}
