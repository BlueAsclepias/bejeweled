package net.blueasclepias.bejeweled.common.item;

import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.registry.GemDefinitionRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Unprocessed gemstone or bead item, registered once per loaded {@link GemDefinition} that belongs to this mod's own
 * namespace (vanilla gems such as diamond or emerald keep their own vanilla item). The item itself carries no gem
 * identity; it resolves its own definition by registry lookup, which is enough to know whether it can be cut at the
 * Gem Cutting Table or polished at a Grindstone.
 */
public class RawGemItem extends Item {
    public RawGemItem(Properties props) {
        super(props);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable Level level,
                                @NotNull List<Component> tooltip,
                                @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        GemDefinition definition = GemDefinitionRegistry.getDefinition(this);
        if (definition == null) return;

        tooltip.add(Component
                .translatable("tooltip.bejeweled.raw_" + definition.category().name().toLowerCase())
                .withStyle(ChatFormatting.GRAY));
    }
}
