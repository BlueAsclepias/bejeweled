package net.blueasclepias.bejeweled.item;

import net.blueasclepias.bejeweled.material.definition.gem.GemDefinition;
import net.blueasclepias.bejeweled.material.definition.gem.GemGrade;
import net.blueasclepias.bejeweled.material.definition.jewel.JewelType;
import net.blueasclepias.bejeweled.registry.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class SocketedJewelItemFactory {

    private static final Map<JewelType, RegistryObject<Item>> RESULT_ITEMS = Map.of(
            JewelType.RING, ModItems.SOCKETED_RING,
            JewelType.AMULET, ModItems.SOCKETED_AMULET,
            JewelType.BRACELET, ModItems.SOCKETED_BRACELET,
            JewelType.CIRCLET, ModItems.SOCKETED_CIRCLET
    );

    public static ItemStack create(ItemStack gemStack, ItemStack baseStack) {

        ProcessedGemItem gemItem = (ProcessedGemItem) gemStack.getItem();
        BaseJewelItem baseItem = (BaseJewelItem) baseStack.getItem();
        GemDefinition gemDef = gemItem.definition();

        // Extract grade from gem NBT
        CompoundTag gemData = gemStack.getTagElement("bejeweled");
        if (gemData == null) {
            return ItemStack.EMPTY;
        }

        String gradeName = gemData.getString("grade");
        GemGrade grade = GemGrade.valueOf(gradeName);

        // Determine correct socketed result item
        RegistryObject<Item> resultRegistry =
                RESULT_ITEMS.get(baseItem.getType());

        if (resultRegistry == null) {
            return ItemStack.EMPTY;
        }

        ItemStack result = new ItemStack(resultRegistry.get());

        // Build structured NBT
        CompoundTag bejeweled = new CompoundTag();
        bejeweled.putString("gem", fromNamespaceAndPath(MOD_ID, gemDef.id()).toString());
        bejeweled.putString("grade", grade.name());
        bejeweled.putString("type", baseItem.getType().name());
        bejeweled.putString("material", baseItem.getMaterial().name());

        CompoundTag root = new CompoundTag();
        root.put("bejeweled", bejeweled);

        result.setTag(root);

        return result;
    }
}
