package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.data.definition.jewel.JewelMaterial;
import net.blueasclepias.bejeweled.data.instance.gem.DefaultGemDefinitions;
import net.blueasclepias.bejeweled.item.BaseJewelItem;
import net.blueasclepias.bejeweled.item.SocketedJewelItem;
import net.blueasclepias.bejeweled.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

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
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(item);
        String path = id.getPath();
        String folder = "item/";

        if (DefaultGemDefinitions.containsKey(id)) {
            folder += "gem/raw/";
        }
        else if (item instanceof BaseJewelItem) {
            folder += "jewel/";
        }
        else if (item instanceof SocketedJewelItem) {
            registerSocketedModel(path);
            return;
        }

        withExistingParent(path, mcLoc("item/generated"))
                .texture("layer0", modLoc(folder + path));
    }

    private void registerSocketedModel(String path) {

        String overlay = "";
        String baseType = "";

        switch (path) {
            case "socketed_amulet" -> {
                overlay = "amulet_socket";
                baseType = "amulet";
            }
            case "socketed_bracelet" -> {
                overlay = "bracelet_socket";
                baseType = "bracelet";
            }
            case "socketed_circlet" -> {
                overlay = "circlet_socket";
                baseType = "circlet";
            }
            default -> {
                overlay = "ring_socket";
                baseType = "ring";
            }
        };
        // Default model - no base material, looks like steel.
        ItemModelBuilder builder =
                withExistingParent(path, mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/socket/socketed_" + baseType))
                        .texture("layer1", modLoc("item/socket/" + overlay));

        for (JewelMaterial material : JewelMaterial.values()) {
            String overrideModelName = path + "_" + material.name().toLowerCase();

            withExistingParent(overrideModelName, mcLoc("item/generated"))
                    .texture("layer0",
                            modLoc("item/jewel/"
                                    + material.name().toLowerCase()
                                    + "_" + baseType))
                    .texture("layer1",
                            modLoc("item/socket/" + overlay));

            builder.override()
                    .predicate(
                            fromNamespaceAndPath(MOD_ID, "material"),
                            material.ordinal()
                    )
                    .model(getExistingFile(modLoc("item/" + overrideModelName)))
                    .end();
        }
    }

}