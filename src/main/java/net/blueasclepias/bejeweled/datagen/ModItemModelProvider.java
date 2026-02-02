package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Non-block items >> basicItem
        ModItems.ITEMS.getEntries().stream()
                .map(RegistryObject::get)
                .filter(item -> !(item instanceof BlockItem))
                .forEach(this::basicItem);
    }

}