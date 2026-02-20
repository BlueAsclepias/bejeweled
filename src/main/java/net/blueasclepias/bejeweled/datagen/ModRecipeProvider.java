package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.material.definition.gem.GemCategory;
import net.blueasclepias.bejeweled.material.instance.gem.GemDefinitions;
import net.blueasclepias.bejeweled.material.registry.ModGemRegistry;
import net.blueasclepias.bejeweled.material.registry.ModOreRegistry;
import net.blueasclepias.bejeweled.material.registry.ModStorageBlockRegistry;
import net.blueasclepias.bejeweled.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
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
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {

        ModStorageBlockRegistry.all().forEach((id, block) -> {
            String blockPath = id.getPath();
            ResourceLocation ingredientPath = ModStorageBlockRegistry.getIngredient(id);
            String itemPath = ingredientPath.getPath();
            Item item = Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ingredientPath));
            if(item == Items.AIR)
                throw new IllegalStateException("No item for storage block recipe: " + itemPath);
            // ===== Compression (9 → 1) =====
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                    .define('#', item)
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .unlockedBy("has_" + itemPath, has(item))
                    .save(consumer);

            // ===== Decompression (1 → 9) =====
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item, 9)
                    .requires(block)
                    .unlockedBy("has_" + blockPath, has(block))
                    .save(consumer, fromNamespaceAndPath(
                            MOD_ID,
                            itemPath + "_from_" + blockPath
                    ));
        });

        // ===== Smelting =====
        ModOreRegistry.allBlocksByFeature().forEach((feat, block) -> {
            ResourceLocation id = feat.definition().drop();
            Item result = Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(id));
            if(result == Items.AIR)
                throw new IllegalStateException("No item for storage block recipe: " + id);
            gemOreCooking(consumer,
                    result,
                    id.getPath(),
                    block.asItem()
            );
        });

        ModGemRegistry.getAll(GemCategory.BEAD, false)
                .forEach((item, def) -> {
                    if(def.equals(GemDefinitions.PEARL)) return;
                    Block block = Objects.requireNonNull(
                            ForgeRegistries.BLOCKS.getValue(fromNamespaceAndPath("minecraft", def.id()))
                    );
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                            .define('#', item)
                            .pattern("###")
                            .pattern("###")
                            .pattern("###")
                            .unlockedBy("has_" + def.id(), has(item))
                            .save(consumer);
                });

        // ===== Workstation =====
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GEM_CUTTING_TABLE.get())
                .define('s', Items.SHEARS)
                .define('g', Items.SPYGLASS)
                .define('w', ItemTags.PLANKS)
                .pattern("sg ")
                .pattern("ww ")
                .pattern("ww ")
                .unlockedBy("has_shears", has(Items.SHEARS))
                .unlockedBy("has_spyglass", has(Items.SPYGLASS))
                .save(consumer);
    }

    private void gemOreCooking(
            Consumer<FinishedRecipe> consumer,
            ItemLike result,
            String path,
            ItemLike... ores
    ) {
        for(ItemLike ore : ores){
            Item itemIngredient = ore.asItem();
            String itemIngredientPath = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(itemIngredient)).getPath();
            SimpleCookingRecipeBuilder.smelting(
                            Ingredient.of(itemIngredient),
                            RecipeCategory.MISC,
                            result,
                            1.0f,
                            200)
                    .unlockedBy("has_" + path + "_ore", has(ore.asItem()))
                    .save(consumer, fromNamespaceAndPath(MOD_ID, path + "_from_smelting_" + itemIngredientPath));

            SimpleCookingRecipeBuilder.blasting(
                            Ingredient.of(itemIngredient),
                            RecipeCategory.MISC,
                            result,
                            1.0f,
                            100)
                    .unlockedBy("has_" + path + "_ore", has(ore.asItem()))
                    .save(consumer, fromNamespaceAndPath(net.blueasclepias.bejeweled.Bejeweled.MOD_ID, path + "_from_blasting_" + itemIngredientPath));
        }
    }

}
