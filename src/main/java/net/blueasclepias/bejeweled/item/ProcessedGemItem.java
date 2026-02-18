package net.blueasclepias.bejeweled.item;

import net.blueasclepias.bejeweled.material.definition.gem.GemDefinition;
import net.blueasclepias.bejeweled.material.definition.gem.GemGrade;
import net.blueasclepias.bejeweled.material.instance.gem.GemInstanceData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;

public class ProcessedGemItem extends AbstractGemItem<GemDefinition> {
    public ProcessedGemItem(GemDefinition def) {
        super(def, new Properties().stacksTo(1));
    }

    @Override
    public @NotNull Rarity getRarity(@NotNull ItemStack stack) {
        return GemInstanceData.getGrade(stack)
                .map(ProcessedGemItem::rarityForGrade)
                .orElse(super.getRarity(stack));
    }

    public static Rarity rarityForGrade(GemGrade grade) {
        return switch (grade) {
            case S -> Rarity.EPIC;
            case A -> Rarity.RARE;
            case B -> Rarity.UNCOMMON;
            default -> Rarity.COMMON;
        };
    }
}

