package net.blueasclepias.bejeweled.common.item.factory;

import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemGrade;
import net.blueasclepias.bejeweled.common.registry.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GemItemFactory {

    public static ItemStack create(@NotNull GemDefinition def, @NotNull GemGrade grade) {
        ItemStack result = new ItemStack(ModItems.GEM_ITEM.get());

        // Build structured NBT
        CompoundTag bejeweled = new CompoundTag();
        bejeweled.putString("gem", def.id().toString());
        bejeweled.putString("grade", grade.name());

        CompoundTag root = new CompoundTag();
        root.put("bejeweled", bejeweled);

        result.setTag(root);

        return result;
    }
}
