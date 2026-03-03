package net.blueasclepias.bejeweled.data.state.gem;

import net.blueasclepias.bejeweled.data.definition.gem.GemGrade;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

/**
 * NBT Helper Class
 */
public class GemState {

    public static final String ROOT = "bejeweled";
    public static final String GEM = "gem";
    public static final String GRADE = "grade";

    public static void set(ItemStack stack, GemGrade gemGrade) {
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
}
