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
 * Lazily bakes and caches a real vanilla-style item model for each processed gem texture
 * that is actually encountered at render time. Instead of hand-building quads in a custom
 * {@link BlockEntityWithoutLevelRenderer}, this class creates a tiny synthetic
 * {@link BlockModel} and lets Minecraft's normal item model pipeline generate the familiar
 * extruded geometry. That means gems with dedicated processed textures render exactly like
 * any other flat vanilla item, while gems without such a texture simply keep using the
 * generic tinted fallback model handled elsewhere by {@link DynamicItemModelHandler}.
 */
public final class GemModelBakery {

    private static final Map<ResourceLocation, BakedModel> CACHE = new HashMap<>();

    private GemModelBakery() {
    }

    /**
     * Resolves the model for a specific gem definition, baking it once on first use and then
     * reusing the cached result until resources are reloaded. Gems without a dedicated
     * processed texture skip baking entirely and keep the supplied fallback model.
     */
    public static BakedModel resolve(GemDefinition def, BakedModel fallback) {
        return CACHE.computeIfAbsent(def.id(), id -> bakeOrFallback(id, fallback));
    }

    /**
     * Bakes a gem-specific model only when that gem provides its own processed texture.
     * The synthetic template's parent is set to the exact {@link ModelBakery#GENERATION_MARKER}
     * sentinel used by vanilla, which causes Forge's baking path to run the model through
     * Minecraft's normal {@code ItemModelGenerator} extrusion logic before final quad baking.
     * This produces the same geometry vanilla would have generated from an
     * {@code item/generated} JSON model, while gems without a dedicated texture just reuse the
     * already-baked fallback model unchanged.
     */
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
     * Clears the cached baked models on resource reload. Baked quads embed sprite/UV data from
     * the current atlas layout, so they must not survive across reloads.
     */
    public static void clear() {
        CACHE.clear();
    }
}
