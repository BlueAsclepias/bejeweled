package net.blueasclepias.bejeweled.common.item;

import net.blueasclepias.bejeweled.client.render.gem.GemItemRenderer;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemGrade;
import net.blueasclepias.bejeweled.common.data.gem.registry.GemDefinitionRegistry;
import net.blueasclepias.bejeweled.common.data.gem.state.GemState;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class GemItem extends Item implements IClientItemExtensions {
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
        if(path.isEmpty()) return super.getName(stack);

        ResourceLocation gemId = ResourceLocation.parse(path.get());
        GemDefinition def = GemDefinitionRegistry.getDefinition(gemId);
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

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private final GemItemRenderer renderer = new GemItemRenderer();
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer;
            }
        });
    }
}
