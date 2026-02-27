package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.item.BaseJewelItem;
import net.blueasclepias.bejeweled.item.ProcessedGemItem;
import net.blueasclepias.bejeweled.item.RawGemItem;
import net.blueasclepias.bejeweled.item.SocketedJewelItem;
import net.blueasclepias.bejeweled.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Provides item models data for the mod.
 */
public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Non-baseBlock items >> registerItemModel
        ModItems.ITEMS.getEntries().stream()
                .map(RegistryObject::get)
                .filter(item -> !(item instanceof BlockItem))
                .forEach(this::registerItemModel);
    }

    private void registerItemModel(Item item) {
        String path = ForgeRegistries.ITEMS.getKey(item).getPath();
        String folder = "item/";

        if (item instanceof ProcessedGemItem) {
            folder += "gem/processed/";
        }
        else if (item instanceof RawGemItem) { // if you have one
            folder += "gem/raw/";
        }
        else if (item instanceof BaseJewelItem) {
            folder += "jewel/";
        }
        else if (item instanceof SocketedJewelItem) {
            folder += "socket/";
        }

        withExistingParent(path, mcLoc("item/generated"))
                .texture("layer0", modLoc(folder + path));
    }

}