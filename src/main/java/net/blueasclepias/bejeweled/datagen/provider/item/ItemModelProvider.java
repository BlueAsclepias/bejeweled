package net.blueasclepias.bejeweled.datagen.provider.item;

import net.blueasclepias.bejeweled.common.data.gem.defaults.DefaultGemDefinitions;
import net.blueasclepias.bejeweled.common.data.jewel.definition.JewelMaterial;
import net.blueasclepias.bejeweled.common.item.BaseJewelItem;
import net.blueasclepias.bejeweled.common.item.SocketedJewelItem;
import net.blueasclepias.bejeweled.common.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Generates item model JSON for every non-block item registered by the mod.
 * Most items become simple single-layer generated models chosen by item category, while socketed jewelry also emits
 * predicate-based override models so the client can swap metal textures from stack NBT.
 */
public class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {

    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
    }

    /**
     * Walks the item registry and generates models only for non-{@link BlockItem} entries, leaving block items to the
     * blockstate provider.
     */
    @Override
    protected void registerModels() {
        // Non-baseBlock items >> registerItemModel
        ModItems.ITEMS.getEntries().stream()
                .map(RegistryObject::get)
                .filter(item -> !(item instanceof BlockItem))
                .forEach(this::registerItemModel);
    }

    /**
     * Chooses the texture folder for a simple generated item model or delegates socketed jewelry to the override-based
     * generator.
     * Raw gems use {@code item/gem/raw/}, unsocketed jewelry uses {@code item/jewel/}, and other non-block items use
     * the default {@code item/} folder.
     */
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

    /**
     * Builds the fallback socketed model plus one material-specific override model per {@link JewelMaterial}.
     * Each override reuses the same socket overlay but swaps the metal base texture, then binds that model to the
     * {@code bejeweled:material} predicate value matching the material's ordinal so
     * {@code DynamicItemModelHandler.registerMaterialPredicate} can select the correct skin at runtime.
     */
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
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "material"),
                            material.ordinal()
                    )
                    .model(getExistingFile(modLoc("item/" + overrideModelName)))
                    .end();
        }
    }

}