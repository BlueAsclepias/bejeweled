package net.blueasclepias.bejeweled.common.item.model;

import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.registry.GemDefinitionRegistry;
import net.blueasclepias.bejeweled.common.data.gem.state.GemState;
import net.blueasclepias.bejeweled.common.data.jewel.definition.JewelMaterial;
import net.blueasclepias.bejeweled.common.data.jewel.state.JewelState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Client-side bridge between gem or jewelry NBT and the model and color hooks that render those items.
 * The registered item-property predicates expose a reload-time gem index and a {@link JewelMaterial} ordinal so
 * ordinary model overrides can pick the correct processed-gem or metal variant without per-gem item classes. When a
 * gem does not ship its own dedicated processed texture, the tint functions recolor the shared fallback layers from
 * the loaded definition, and {@link #hasCustomTexture(ResourceLocation)} gates that dedicated-texture path.
 */
public class DynamicItemModelHandler {

    /**
     * Registers the {@code bejeweled:material} predicate for a jewelry item. The resulting override value is the
     * stored {@link JewelMaterial#ordinal()}, or {@code 0} when no material NBT is present.
     */
    public static void registerMaterialPredicate(Item item) {
        ItemProperties.register(
                item,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "material"),
                (stack, level, entity, seed) -> getMaterialValue(stack)
        );
    }

    /**
     * Registers the {@code bejeweled:gem} predicate for the shared cut-gem item. The resulting override value is the
     * 1-based index assigned by {@link GemDefinitionRegistry} during the current reload, or {@code 0} when unresolved.
     */
    public static void registerGemPredicate(Item item) {
        ItemProperties.register(
                item,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "gem"),
                (stack, level, entity, seed) -> getGemValue(stack)
        );
    }

    private static float getGemValue(ItemStack stack) {
        Optional<GemDefinition> def = GemState.getDefinition(stack);
        if(def.isEmpty()) return 0F;

        Integer index = GemDefinitionRegistry.getIndex(def.get().id());
        return index == null ? 0F : index;
    }

    public static float getMaterialValue(ItemStack stack) {
        Optional<JewelMaterial> material = JewelState.getMaterial(stack);
        return material.isPresent() ? material.get().ordinal() : 0F;
    }

    /**
     * Tints the generic processed-gem fallback texture with the loaded gem color. Gems that provide their own
     * dedicated texture return {@code -1} so the original texture colors are used instead.
     */
    public static int tintGemLayer(ItemStack stack, int tintIndex) {
        Optional<GemDefinition> def = GemState.getDefinition(stack);

        // only tint default texture
        if (def.isEmpty() || DynamicItemModelHandler.hasCustomTexture(def.get()))
            return -1;

        return 0xFF000000 | def.get().color(); // add alpha
    }

    /**
     * Tints only the socket overlay layer on jewelry items. Non-overlay layers are left white so the base jewelry
     * texture renders unchanged even when no gem is present.
     */
    public static int tintSocketLayer(ItemStack stack, int tintIndex) {
        // Only tint overlay
        if (tintIndex != 1) return 0xFFFFFFFF;

        Optional<GemDefinition> def = GemState.getDefinition(stack);
        // add alpha
        return def.map(gemDefinition -> 0xFF000000 | gemDefinition.color()).orElse(0xFFFFFFFF);

    }

    public static boolean hasCustomTexture(GemDefinition def) {
        return hasCustomTexture(def.id());
    }

    /**
     * Checks whether a dedicated processed-gem texture exists at the resource path derived by
     * {@link #processedTextureId(ResourceLocation)}.
     */
    public static boolean hasCustomTexture(ResourceLocation gemId) {
        ResourceLocation atlasId = processedTextureId(gemId);
        ResourceLocation tex = ResourceLocation.fromNamespaceAndPath(
                atlasId.getNamespace(),
                "textures/" + atlasId.getPath() + ".png"
        );

        return Minecraft.getInstance()
                .getResourceManager()
                .getResource(tex)
                .isPresent();
    }

    /**
     * Returns the atlas-relative sprite id for a gem's dedicated processed texture, without the
     * {@code textures/} prefix or {@code .png} suffix. This is the path used both for existence checks and for
     * baking a per-gem model when the gem opts out of the generic tinted fallback.
     */
    public static ResourceLocation processedTextureId(ResourceLocation gemId) {
        return ResourceLocation.fromNamespaceAndPath(
                gemId.getNamespace(),
                "item/gem/processed/" + gemId.getPath()
        );
    }

}