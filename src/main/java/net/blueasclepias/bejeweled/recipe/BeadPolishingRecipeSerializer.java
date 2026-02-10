package net.blueasclepias.bejeweled.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class BeadPolishingRecipeSerializer implements RecipeSerializer<BeadPolishingRecipe> {

    @Override
    public BeadPolishingRecipe fromJson(
            ResourceLocation id,
            JsonObject json
    ) {
        Ingredient input = Ingredient.fromJson(json.get("input"));
        ItemStack output = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("output"));
        return new BeadPolishingRecipe(id, input, output);
    }

    @Override
    public BeadPolishingRecipe fromNetwork(
            ResourceLocation id,
            FriendlyByteBuf buf
    ) {
        Ingredient input = Ingredient.fromNetwork(buf);
        ItemStack output = buf.readItem();
        return new BeadPolishingRecipe(id, input, output);
    }

    @Override
    public void toNetwork(
            FriendlyByteBuf buf,
            BeadPolishingRecipe recipe
    ) {
        recipe.input.toNetwork(buf);
        buf.writeItem(recipe.output);
    }
}
