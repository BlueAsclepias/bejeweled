package net.blueasclepias.bejeweled.common.data.gem.state;

import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemGrade;
import net.blueasclepias.bejeweled.common.data.gem.registry.GemDefinitionRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * NBT Helper Class
 */
public class GemState {

    public static final String ROOT = MOD_ID;
    public static final String GEM = "gem";
    public static final String GRADE = "grade";

    private static CompoundTag root(ItemStack stack) {
        return stack.getOrCreateTagElement(ROOT);
    }

    public static void setGem(ItemStack stack, GemDefinition def) {
        root(stack).putString(GEM, def.id().toString());
    }

    public static void setGrade(ItemStack stack, GemGrade grade) {
        root(stack).putString(GRADE, grade.name());
    }

    public static Optional<String> getGem(ItemStack stack) {
        CompoundTag tag = stack.getTagElement(ROOT);
        if (tag == null || !tag.contains(GEM)) return Optional.empty();
        try {
            return Optional.of(tag.getString(GEM));
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    public static Optional<GemGrade> getGrade(ItemStack stack) {
        CompoundTag tag = stack.getTagElement(ROOT);
        if (tag == null || !tag.contains(GRADE)) return Optional.empty();
        try {
            return Optional.of(GemGrade.valueOf(tag.getString(GRADE).toUpperCase()));
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    public static Optional<GemDefinition> getDefinition(ItemStack stack) {
        return getGem(stack)
                .map(ResourceLocation::parse)
                .map(GemDefinitionRegistry::getDefinition);
    }
}
