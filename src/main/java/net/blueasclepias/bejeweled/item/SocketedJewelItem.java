package net.blueasclepias.bejeweled.item;

import net.blueasclepias.bejeweled.material.definition.gem.GemDefinition;
import net.blueasclepias.bejeweled.material.definition.gem.GemGrade;
import net.blueasclepias.bejeweled.material.definition.jewel.JewelMaterial;
import net.blueasclepias.bejeweled.material.definition.jewel.JewelType;
import net.blueasclepias.bejeweled.material.registry.ModGemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class SocketedJewelItem extends Item implements ICurioItem {
    public SocketedJewelItem(Properties pProperties) {
        super(pProperties.stacksTo(1));
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {

        CompoundTag tag = stack.getTagElement(MOD_ID);
        if (tag == null) {
            return super.getName(stack);
        }

        String gemId = tag.getString("gem");
        GemGrade grade = GemGrade.valueOf(tag.getString("grade"));
        JewelType type = JewelType.valueOf(tag.getString("type"));
        JewelMaterial material = JewelMaterial.valueOf(tag.getString("material"));

        GemDefinition def = ModGemRegistry.get(ResourceLocation.parse(gemId));
        if (def == null) {
            return super.getName(stack);
        }

        Component gradeComponent = Component.translatable(grade.translationKey);
        Component gemComponent = Component.translatable("item.bejeweled." + def.id());
        Component materialComponent = Component.translatable(material.getTranslationKey());
        Component typeComponent = Component.translatable(type.getTranslationKey());

        return Component.translatable(
                "item.bejeweled.jewel_name",
                gradeComponent,
                gemComponent,
                materialComponent,
                typeComponent
        ).withStyle(grade.color);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable Level level,
                                @NotNull List<Component> tooltip,
                                @NotNull TooltipFlag flag) {

        super.appendHoverText(stack, level, tooltip, flag);

        CompoundTag bejeweled = stack.getTagElement(MOD_ID);
        if (bejeweled != null) {
            GemGrade grade =  GemGrade.valueOf(bejeweled.getString("grade"));
            JewelType type = JewelType.valueOf(bejeweled.getString("type"));
            JewelMaterial material = JewelMaterial.valueOf(bejeweled.getString("material"));

            tooltip.add(
                    Component
                            .translatable("tooltip.bejeweled.grade")
                            .append(Component.literal(grade.name())
                                    .withStyle(grade.color, ChatFormatting.BOLD)
                            )
            );

            tooltip.add(
                    Component
                            .translatable("tooltip.bejeweled.type")
                            .append(Component.literal(type.name()))
            );

            tooltip.add(
                    Component
                            .translatable("tooltip.bejeweled.material")
                            .append(Component.literal(material.name()))
            );
        }
    }
}
