package net.blueasclepias.bejeweled.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

public class GemCuttingRecipeSerializer implements RecipeSerializer<GemCuttingRecipe> {

    @Override
    public @NotNull GemCuttingRecipe fromJson(
            @NotNull ResourceLocation id,
            JsonObject json
    ) {
        Ingredient input = Ingredient.fromJson(json.get("input"));
        ItemStack output = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("output"));
        return new GemCuttingRecipe(id, input, output);
    }

    @Override
    public GemCuttingRecipe fromNetwork(
            @NotNull ResourceLocation id,
            @NotNull FriendlyByteBuf buf
    ) {
        Ingredient input = Ingredient.fromNetwork(buf);
        ItemStack output = buf.readItem();
        return new GemCuttingRecipe(id, input, output);
    }

    @Override
    public void toNetwork(
            @NotNull FriendlyByteBuf buf,
            GemCuttingRecipe recipe
    ) {
        recipe.input.toNetwork(buf);
        buf.writeItem(recipe.output);
    }
}
