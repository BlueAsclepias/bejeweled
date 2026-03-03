package net.blueasclepias.bejeweled.item;

import net.blueasclepias.bejeweled.data.accessor.GemDefinitionAccessor;
import net.blueasclepias.bejeweled.data.definition.gem.GemDefinition;
import net.blueasclepias.bejeweled.data.definition.jewel.JewelMaterial;
import net.blueasclepias.bejeweled.data.state.gem.GemState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class DynamicItemModelHandler {

    public static void registerMaterialPredicate(Item item) {
        ItemProperties.register(
                item,
                fromNamespaceAndPath(MOD_ID, "material"),
                (stack, level, entity, seed) -> getMaterialValue(stack)
        );
    }

    public static void registerGemPredicate(Item item) {
        ItemProperties.register(
                item,
                fromNamespaceAndPath(MOD_ID, "gem"),
                (stack, level, entity, seed) -> getGemValue(stack)
        );
    }

    private static float getGemValue(ItemStack stack) {
        CompoundTag tag = stack.getTagElement(MOD_ID);
        if (tag == null) return 0F;

        Optional<String> path = GemState.getGem(stack);
        if(path.isEmpty()) return 0F;

        ResourceLocation gemId = ResourceLocation.parse(path.get());
        GemDefinition def = GemDefinitionAccessor.getDefinition(gemId);
        if(def == null) return 0F;

        Integer index = GemDefinitionAccessor.getIndex(def.id());
        return index == null ? 0F : index;
    }

    public static float getMaterialValue(ItemStack stack) {
        CompoundTag tag = stack.getTagElement(MOD_ID);
        if (tag == null) return 0F;
        try {
            JewelMaterial material = JewelMaterial.valueOf(tag.getString("material"));
            return material.ordinal();
        } catch (Exception e) {
            return 0F;
        }
    }

    public static int tintGemLayer(ItemStack stack, int tintIndex) {
        CompoundTag tag = stack.getTagElement(MOD_ID);
        if (tag == null) return -1;
        Optional<String> path = GemState.getGem(stack);
        if(path.isEmpty()) return -1;

        ResourceLocation gemId = ResourceLocation.parse(path.get());
        GemDefinition def = GemDefinitionAccessor.getDefinition(gemId);

        // only tint default texture
        if (def == null || DynamicItemModelHandler.hasCustomTexture(def))
            return -1;

        return 0xFF000000 | def.color(); // add alpha
    }

    public static int tintSocketLayer(ItemStack stack, int tintIndex) {
        // Only tint overlay
        if (tintIndex != 1) return 0xFFFFFFFF;

        CompoundTag tag = stack.getTagElement(MOD_ID);
        if (tag == null) return 0xFFFFFFFF;

        Optional<String> path = GemState.getGem(stack);
        if(path.isEmpty()) return 0xFFFFFFFF;

        ResourceLocation gemId = ResourceLocation.parse(path.get());
        GemDefinition def = GemDefinitionAccessor.getDefinition(gemId);
        if (def == null) return 0xFFFFFFFF;

        return 0xFF000000 | def.color();  // add alpha
    }

    public static boolean hasCustomTexture(GemDefinition def) {
        ResourceLocation tex = fromNamespaceAndPath(
                def.id().getNamespace(),
                "textures/item/gem/processed/" + def.id().getPath() + ".png"
        );

        return Minecraft.getInstance()
                .getResourceManager()
                .getResource(tex)
                .isPresent();
    }

}