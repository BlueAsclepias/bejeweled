package net.blueasclepias.bejeweled.common.data.jewel.state;

import net.blueasclepias.bejeweled.common.data.jewel.definition.JewelMaterial;
import net.blueasclepias.bejeweled.common.data.jewel.definition.JewelType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class JewelState {

    public static final String ROOT = MOD_ID;
    public static final String MATERIAL = "material";
    public static final String TYPE = "type";

    private static CompoundTag root(ItemStack stack) {
        return stack.getOrCreateTagElement(ROOT);
    }

    public static void setMaterial(ItemStack stack, JewelMaterial material) {
        root(stack).putString(MATERIAL, material.name());
    }

    public static void setType(ItemStack stack, JewelType type) {
        root(stack).putString(TYPE, type.name());
    }

    public static Optional<JewelMaterial> getMaterial(ItemStack stack) {
        CompoundTag tag = stack.getTagElement(ROOT);
        if (tag == null || !tag.contains(MATERIAL)) return Optional.empty();
        try {
            return Optional.of(JewelMaterial.valueOf(tag.getString(MATERIAL).toUpperCase()));
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    public static Optional<JewelType> getType(ItemStack stack) {
        CompoundTag tag = stack.getTagElement(ROOT);
        if (tag == null || !tag.contains(TYPE)) return Optional.empty();
        try {
            return Optional.of(JewelType.valueOf(tag.getString(TYPE).toUpperCase()));
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }
}
