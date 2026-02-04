package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.registry.ModBlocks;
import net.blueasclepias.bejeweled.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

/**
 * Provides crafting, smelting, and blasting recipes for data the mod.
 */
public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        ModBlocks.STORAGE_BLOCKS.forEach(block -> {
            ResourceLocation blockId = ForgeRegistries.BLOCKS.getKey(block.get());
            if (blockId == null) return;

            String blockName = blockId.getPath();

            // Determine item name
            String itemName;
            if (blockName.startsWith("block_of_")) {
                itemName = blockName.replace("block_of_", "");
            } else {
                return;
            }

            RegistryObject<Item> item = ModItems.ITEMS.getEntries().stream()
                    .filter(i -> i.getId().getPath().equals(itemName))
                    .findFirst()
                    .orElse(null);

            if (item == null) return;

            // ===== Compression (9 → 1) =====
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block.get())
                    .define('#', item.get())
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_" + itemName, has(item.get()))
                    .save(consumer);

            // ===== Decompression (1 → 9) =====
            ShapelessRecipeBuilder.shapeless(
                            RecipeCategory.MISC,
                            item.get(),
                            9
                    )
                    .requires(block.get())
                    .unlockedBy("has_" + blockName, has(block.get()))
                    .save(consumer, fromNamespaceAndPath(
                            MOD_ID,
                            itemName + "_from_" + blockName
                    ));
        });

        // ===== Smelting =====
        ModBlocks.ORE_BLOCKS.forEach((type, entry) ->
                entry.forEach((base, block) -> {
                    Item result = type.drop().get();
                    gemOreCooking(consumer,
                            result,
                            MOD_ID,
                            ForgeRegistries.ITEMS.getKey(result).getPath(),
                            block.get().asItem()
                            );
                })
        );

        // TODO: add recipes for crafting vanilla coral blocks using rough polyps
        ModItems.ROUGH_CORAL_POLYPS
                .forEach(item -> {
                    String itemName = item.getId().getPath();
                    String blockName = itemName
                            .replace("_polyp", "_block")
                            .replace("rough_", "");
                    Block block = ForgeRegistries.BLOCKS.getValue(
                            fromNamespaceAndPath("minecraft", blockName)
                    );
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                            .define('#', item.get())
                            .pattern("###")
                            .pattern("###")
                            .pattern("###")
                            .unlockedBy("has_" + itemName, has(item.get()))
                            .save(consumer);
                });

        // ===== VANILLA GEMS COOKING =====
        gemOreCooking(consumer,
                ModItems.ROUGH_DIAMOND.get(),
                "minecraft",
                "diamond",
                Items.DIAMOND_ORE, Items.DEEPSLATE_DIAMOND_ORE
        );

        gemOreCooking(consumer,
                ModItems.ROUGH_EMERALD.get(),
                "minecraft",
                "emerald",
                Items.EMERALD_ORE, Items.DEEPSLATE_EMERALD_ORE
        );
    }

    private void gemOreCooking(
            Consumer<FinishedRecipe> consumer,
            ItemLike result,
            String namespace,
            String name,
            ItemLike... ores
    ) {
        for(ItemLike ore : ores){
            Item itemIngredient = ore.asItem();
            String itemIngredientName = ForgeRegistries.ITEMS.getKey(itemIngredient).getPath();
            SimpleCookingRecipeBuilder.smelting(
                            Ingredient.of(itemIngredient),
                            RecipeCategory.MISC,
                            result,
                            1.0f,
                            200)
                    .unlockedBy("has_" + name + "_ore", has(ore.asItem()))
                    .save(consumer, fromNamespaceAndPath(namespace, name + "_from_smelting_" + itemIngredientName));

            SimpleCookingRecipeBuilder.blasting(
                            Ingredient.of(itemIngredient),
                            RecipeCategory.MISC,
                            result,
                            1.0f,
                            100)
                    .unlockedBy("has_" + name + "_ore", has(ore.asItem()))
                    .save(consumer, fromNamespaceAndPath(namespace, name + "_from_blasting_" + itemIngredientName));
        }
    }

}
