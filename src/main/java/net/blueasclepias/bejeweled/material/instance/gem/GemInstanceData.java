package net.blueasclepias.bejeweled.material.instance.gem;

import net.blueasclepias.bejeweled.material.definition.gem.GemGrade;
import net.blueasclepias.bejeweled.material.definition.gem.GemRarity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

/**
 * NBT Helper Class
 */
public class GemInstanceData {

    public static final String ROOT = "bejeweled";
    public static final String GEM = "gem";
    public static final String GRADE = "grade";
    public static final String RARITY = "rarity";

    public static void setGem(ItemStack stack, GemGrade gemGrade) {
        CompoundTag tag = stack.getOrCreateTagElement(ROOT);
        tag.putString(GRADE, gemGrade.name());
    }

    public static Optional<String> getGem(ItemStack stack) {
        CompoundTag tag = stack.getTagElement(ROOT);
        if (tag == null || !tag.contains(GEM)) return Optional.empty();
        return Optional.of(tag.getString(GEM));
    }

    public static Optional<GemGrade> getGrade(ItemStack stack) {
        CompoundTag tag = stack.getTagElement(ROOT);
        if (tag == null || !tag.contains(GRADE)) return Optional.empty();
        return Optional.of(GemGrade.valueOf(tag.getString(GRADE)));
    }

    public static Optional<GemRarity> getRarity(ItemStack stack) {
        CompoundTag tag = stack.getTagElement(ROOT);
        if (tag == null || !tag.contains(RARITY)) return Optional.empty();
        return Optional.of(GemRarity.valueOf(tag.getString(RARITY)));
    }

    // TODO: passives & effects

    public static boolean isGem(ItemStack stack) {
        return getGem(stack).isPresent();
    }
}
