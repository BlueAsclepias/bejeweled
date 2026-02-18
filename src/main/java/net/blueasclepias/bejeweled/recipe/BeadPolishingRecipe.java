package net.blueasclepias.bejeweled.recipe;

import net.blueasclepias.bejeweled.material.definition.gem.GemGrade;
import net.blueasclepias.bejeweled.material.instance.gem.GemInstanceData;
import net.blueasclepias.bejeweled.registry.ModRecipes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class BeadPolishingRecipe implements Recipe<Container> {

    private final ResourceLocation id;
    final Ingredient input;
    final ItemStack output;

    public BeadPolishingRecipe(ResourceLocation id, Ingredient input, ItemStack output) {
        this.id = id;
        this.input = input;
        this.output = output;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return input.test(container.getItem(0));
    }

    // TODO: Grade Gamble
    @Override
    public ItemStack assemble(Container container, RegistryAccess access) {
        ItemStack result = output.copy();
        RandomSource random = RandomSource.create();
        GemGrade gemGrade = GemGrade.random(random);
        GemInstanceData.setGem(result, gemGrade);
        return result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return output;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.BEAD_POLISHING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.BEAD_POLISHING_TYPE.get();
    }
}
