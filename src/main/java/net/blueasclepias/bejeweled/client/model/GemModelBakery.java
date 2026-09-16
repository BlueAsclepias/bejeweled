package net.blueasclepias.bejeweled.client.model;

import com.mojang.datafixers.util.Either;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.item.model.DynamicItemModelHandler;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Lazily bakes and caches a real BakedModel.
 * for every distinct gem "processed" texture encountered at render time, so
 * that cut gems added by this mod, other mods, or datapacks all get proper
 * 3D extrusion identical to a normal item - without
 * needing a custom {@link BlockEntityWithoutLevelRenderer}.
 * Gems that don't ship a dedicated texture keep using the generic tinted
 * "gem_item" model (handled entirely by {@link DynamicItemModelHandler#tintGemLayer}),
 * so nothing is baked for them.
 */
public final class GemModelBakery {

    private static final Map<ResourceLocation, BakedModel> CACHE = new HashMap<>();

    private GemModelBakery() {
    }

    /**
     * Returns the baked model to use for the given gem, baking and caching
     * it on first use. The result is cached until the next
     * resource reload.
     */
    public static BakedModel resolve(GemDefinition def, BakedModel fallback) {
        return CACHE.computeIfAbsent(def.id(), id -> bakeOrFallback(id, fallback));
    }

    private static BakedModel bakeOrFallback(ResourceLocation gemId, BakedModel fallback) {
        if (!DynamicItemModelHandler.hasCustomTexture(gemId)) return fallback;

        ResourceLocation texture = DynamicItemModelHandler.processedTextureId(gemId);
        Material material = new Material(TextureAtlas.LOCATION_BLOCKS, texture);

        BlockModel template = new BlockModel(
                null,
                List.of(),
                Map.of("layer0", Either.left(material)),
                null,
                null,
                fallback.getTransforms(),
                List.of()
        );
        template.parent = ModelBakery.GENERATION_MARKER;

        return template.bake(null, Material::sprite, BlockModelRotation.X0_Y0, gemId);
    }

    /**
     * Clears all cached models. Must be called on resource reload, since
     * baked quads embed UV coordinates tied to a specific atlas layout.
     */
    public static void clear() {
        CACHE.clear();
    }
}
