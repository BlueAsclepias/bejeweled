package net.blueasclepias.bejeweled.client.texture;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class GemSpriteCache {
    private static final Map<ResourceLocation, TextureAtlasSprite> CACHE = new HashMap<>();

    public static TextureAtlasSprite get(ResourceLocation texture) {
        return CACHE.computeIfAbsent(texture, tex ->
                Minecraft.getInstance()
                        .getTextureAtlas(TextureAtlas.LOCATION_BLOCKS)
                        .apply(tex)
        );
    }

    public static void clear() {
        CACHE.clear();
    }
}
