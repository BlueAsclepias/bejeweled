package net.blueasclepias.bejeweled.common.item;

import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemGrade;
import net.blueasclepias.bejeweled.common.data.gem.state.GemState;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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

/**
 * Single processed-gem item class reused for every cut gemstone Bejeweled can represent.
 * The specific gem is not encoded in the item id at all; it is resolved entirely from {@link GemState} NBT, which
 * lets this one item represent this mod's gems, vanilla gems that intentionally keep their own textures, and gem
 * definitions supplied by other mods or datapacks. Display name, tooltip styling, and rarity are all derived at
 * render time from the stored grade and referenced {@link GemDefinition}.
 */
public class GemItem extends Item {
    public GemItem(Item.Properties props) {
        super(props);
    }

    /**
     * Builds the display name as the localized grade plus the referenced gem's own item name. If the Bejeweled tag or
     * gem definition is missing, this falls back to the base item name.
     */
    @Override
    public @NotNull Component getName(ItemStack stack) {

        CompoundTag tag = stack.getTagElement(MOD_ID);
        if (tag == null) return super.getName(stack);


        // Get Grade or Default to lowest
        GemGrade grade = GemState.getGrade(stack).orElse(GemGrade.D);

        Optional<GemDefinition> def = GemState.getDefinition(stack);
        if (def.isEmpty()) return super.getName(stack);

        Item item = ForgeRegistries.ITEMS.getValue(def.get().id());

        Component gradeComponent = Component.translatable(grade.translationKey);
        Component gemComponent = item != null
                ? item.getDescription()
                : Component.literal(def.get().id().getPath());

        return Component.translatable(
                "item.bejeweled.gem_name",
                gradeComponent,
                gemComponent
        ).withStyle(grade.color);
    }

    /**
     * Maps the stored gem grade onto vanilla rarity tiers so stack name coloring reflects the cut quality. Untagged
     * stacks keep the base item's default rarity.
     */
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

    /**
     * Collapses Bejeweled's five grade tiers onto the four vanilla {@link Rarity} values used for item name coloring.
     */
    public static Rarity rarityForGrade(GemGrade grade) {
        return switch (grade) {
            case S -> Rarity.EPIC;
            case A -> Rarity.RARE;
            case B -> Rarity.UNCOMMON;
            default -> Rarity.COMMON;
        };
    }
}
