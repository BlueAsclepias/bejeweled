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

public class DynamicItemModelHandler {

    public static void registerMaterialPredicate(Item item) {
        ItemProperties.register(
                item,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "material"),
                (stack, level, entity, seed) -> getMaterialValue(stack)
        );
    }

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

    public static int tintGemLayer(ItemStack stack, int tintIndex) {
        Optional<GemDefinition> def = GemState.getDefinition(stack);

        // only tint default texture
        if (def.isEmpty() || DynamicItemModelHandler.hasCustomTexture(def.get()))
            return -1;

        return 0xFF000000 | def.get().color(); // add alpha
    }

    public static int tintSocketLayer(ItemStack stack, int tintIndex) {
        // Only tint overlay
        if (tintIndex != 1) return 0xFFFFFFFF;

        Optional<GemDefinition> def = GemState.getDefinition(stack);
        // add alpha
        return def.map(gemDefinition -> 0xFF000000 | gemDefinition.color()).orElse(0xFFFFFFFF);

    }

    public static boolean hasCustomTexture(GemDefinition def) {
        ResourceLocation tex = ResourceLocation.fromNamespaceAndPath(
                def.id().getNamespace(),
                "textures/item/gem/processed/" + def.id().getPath() + ".png"
        );

        return Minecraft.getInstance()
                .getResourceManager()
                .getResource(tex)
                .isPresent();
    }

}