package net.blueasclepias.bejeweled.item;

import net.blueasclepias.bejeweled.data.accessor.GemDefinitionAccessor;
import net.blueasclepias.bejeweled.data.definition.gem.GemDefinition;
import net.blueasclepias.bejeweled.data.definition.gem.GemGrade;
import net.blueasclepias.bejeweled.data.state.gem.GemState;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class GemItem extends Item{
    public GemItem(Item.Properties props) {
        super(props);
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {

        CompoundTag tag = stack.getTagElement(MOD_ID);
        if (tag == null) return super.getName(stack);


        // Get Grade or Default to lowest
        GemGrade grade = GemState.getGrade(stack).orElse(GemGrade.D);

        Optional<String> path = GemState.getGem(stack);
        if(path.isEmpty()) return super.getName(stack);;

        ResourceLocation gemId = ResourceLocation.parse(path.get());
        GemDefinition def = GemDefinitionAccessor.getDefinition(gemId);
        if (def == null) return super.getName(stack);

        Item item = ForgeRegistries.ITEMS.getValue(def.id());

        Component gradeComponent = Component.translatable(grade.translationKey);
        Component gemComponent = item != null
                ? item.getDescription()
                : Component.literal(def.id().getPath());

        return Component.translatable(
                "item.bejeweled.gem_name",
                gradeComponent,
                gemComponent
        ).withStyle(grade.color);
    }

    @Override
    public @NotNull Rarity getRarity(@NotNull ItemStack stack) {
        return GemState.getGrade(stack)
                .map(GemItem::rarityForGrade)
                .orElse(super.getRarity(stack));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable Level level,
                                @NotNull List<Component> tooltip,
                                @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        GemState.getGrade(stack).ifPresent(grade -> {
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
