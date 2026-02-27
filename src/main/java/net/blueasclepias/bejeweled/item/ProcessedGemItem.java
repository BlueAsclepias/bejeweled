package net.blueasclepias.bejeweled.item;

import net.blueasclepias.bejeweled.material.definition.gem.GemDefinition;
import net.blueasclepias.bejeweled.material.definition.gem.GemGrade;
import net.blueasclepias.bejeweled.material.instance.gem.GemInstanceData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

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

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable Level level,
                                @NotNull List<Component> tooltip,
                                @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        GemInstanceData.getGrade(stack).ifPresent(grade -> {
            tooltip.add(
                    Component
                            .translatable("tooltip.bejeweled.grade")
                            .append(Component.literal(grade.name())
                                    .withStyle(grade.color, ChatFormatting.BOLD)
                            )
            );
        });
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

