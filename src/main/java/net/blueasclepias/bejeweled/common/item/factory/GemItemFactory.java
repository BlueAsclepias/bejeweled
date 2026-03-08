package net.blueasclepias.bejeweled.common.item.factory;

import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemGrade;
import net.blueasclepias.bejeweled.common.data.gem.state.GemState;
import net.blueasclepias.bejeweled.common.registry.ModItems;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GemItemFactory {
    public static ItemStack create(@NotNull GemDefinition def, @NotNull GemGrade grade) {
        ItemStack result = new ItemStack(ModItems.GEM_ITEM.get());
        GemState.setGem(result, def);
        GemState.setGrade(result, grade);
        return result;
    }
}
