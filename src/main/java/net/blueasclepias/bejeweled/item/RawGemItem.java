package net.blueasclepias.bejeweled.item;

import net.blueasclepias.bejeweled.material.definition.gem.GemDefinition;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class RawGemItem extends AbstractGemItem<GemDefinition> {
    public RawGemItem(GemDefinition def) {
        super(def, new Properties());
    }
    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component
                .translatable("tooltip.bejeweled.raw_" + definition.category().name().toLowerCase())
                .withStyle(ChatFormatting.GRAY));
    }
}
