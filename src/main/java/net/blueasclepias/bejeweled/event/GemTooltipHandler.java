package net.blueasclepias.bejeweled.event;

import net.blueasclepias.bejeweled.material.instance.gem.GemInstanceData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

@Mod.EventBusSubscriber(
        modid = MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class GemTooltipHandler {
    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        List<Component> tooltip = event.getToolTip();
        GemInstanceData.getGrade(stack).ifPresent(grade -> {
            ChatFormatting format = switch (grade){
                case S -> ChatFormatting.DARK_PURPLE;
                case A -> ChatFormatting.RED;
                case B -> ChatFormatting.GOLD;
                case C -> ChatFormatting.YELLOW;
                default -> ChatFormatting.GRAY;
            };
            tooltip.add(1,
                    Component
                            .translatable("tooltip.bejeweled.processed_gem_category")
                            .withStyle(ChatFormatting.BLUE)

            );
            tooltip.add(2,
                    Component
                            .translatable("tooltip.bejeweled.grade")
                            .append(Component.literal(grade.name())
                                    .withStyle(format, ChatFormatting.BOLD)
                            )
            );
        });
    }
}
