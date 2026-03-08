package net.blueasclepias.bejeweled.common.item;

import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemGrade;
import net.blueasclepias.bejeweled.common.data.gem.registry.GemDefinitionRegistry;
import net.blueasclepias.bejeweled.common.data.gem.state.GemState;
import net.blueasclepias.bejeweled.common.data.jewel.definition.JewelMaterial;
import net.blueasclepias.bejeweled.common.data.jewel.definition.JewelType;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class SocketedJewelItem extends Item implements ICurioItem {
    public SocketedJewelItem(Properties pProperties) {
        super(pProperties.stacksTo(1));
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
        GemDefinition def = GemDefinitionRegistry.getDefinition(gemId);
        JewelType type = JewelType.valueOf(tag.getString("type"));
        JewelMaterial material = JewelMaterial.valueOf(tag.getString("material"));

        if (def == null) return super.getName(stack);

        Item item = ForgeRegistries.ITEMS.getValue(def.id());

        Component gradeComponent = Component.translatable(grade.translationKey);
        Component gemComponent = item != null
                ? item.getDescription()
                : Component.literal(def.id().getPath());
        Component materialComponent = Component.translatable(material.translationKey);
        Component typeComponent = Component.translatable(type.translationKey);

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
                            .append(Component.translatable(type.translationKey))
            );

            tooltip.add(
                    Component
                            .translatable("tooltip.bejeweled.material")
                            .append(Component.translatable(material.translationKey))
            );
        }
    }
}
