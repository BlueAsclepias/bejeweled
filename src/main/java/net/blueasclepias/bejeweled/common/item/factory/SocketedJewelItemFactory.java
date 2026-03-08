package net.blueasclepias.bejeweled.common.item.factory;

import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemGrade;
import net.blueasclepias.bejeweled.common.data.gem.registry.GemDefinitionRegistry;
import net.blueasclepias.bejeweled.common.data.gem.state.GemState;
import net.blueasclepias.bejeweled.common.data.jewel.definition.JewelType;
import net.blueasclepias.bejeweled.common.item.BaseJewelItem;
import net.blueasclepias.bejeweled.common.registry.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;
import java.util.Optional;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class SocketedJewelItemFactory {

    private static final Map<JewelType, RegistryObject<Item>> RESULT_ITEMS = Map.of(
            JewelType.RING, ModItems.SOCKETED_RING,
            JewelType.AMULET, ModItems.SOCKETED_AMULET,
            JewelType.BRACELET, ModItems.SOCKETED_BRACELET,
            JewelType.CIRCLET, ModItems.SOCKETED_CIRCLET
    );

    public static ItemStack create(ItemStack gemStack, ItemStack baseStack) {

        BaseJewelItem baseItem = (BaseJewelItem) baseStack.getItem();

        // Check if NBT root exists
        if (gemStack.getTagElement(MOD_ID) == null) return ItemStack.EMPTY;

        // Get Grade or Default to lowest grade
        GemGrade grade = GemState.getGrade(gemStack).orElse(GemGrade.D);

        Optional<String> path = GemState.getGem(gemStack);
        if(path.isEmpty()) return ItemStack.EMPTY;

        ResourceLocation gemId = ResourceLocation.parse(path.get());
        GemDefinition def = GemDefinitionRegistry.getDefinition(gemId);
        if(def == null) return ItemStack.EMPTY;

        // Determine correct socketed result item
        RegistryObject<Item> resultRegistry = RESULT_ITEMS.get(baseItem.getType());
        if (resultRegistry == null) return ItemStack.EMPTY;

        ItemStack result = new ItemStack(resultRegistry.get());

        // Build structured NBT
        CompoundTag bejeweled = new CompoundTag();
        bejeweled.putString("gem", gemId.toString());
        bejeweled.putString("grade", grade.name());
        bejeweled.putString("type", baseItem.getType().name());
        bejeweled.putString("material", baseItem.getMaterial().name());

        CompoundTag root = new CompoundTag();
        root.put("bejeweled", bejeweled);

        result.setTag(root);

        return result;
    }
}
