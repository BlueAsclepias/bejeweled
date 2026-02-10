package net.blueasclepias.bejeweled.registry;

import net.blueasclepias.bejeweled.recipe.GemCuttingRecipe;
import net.blueasclepias.bejeweled.recipe.GemCuttingRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, MOD_ID);

    public static final RegistryObject<RecipeType<GemCuttingRecipe>> GEM_CUTTING_TYPE =
            TYPES.register("gem_cutting", () -> new RecipeType<>() {});

    public static final RegistryObject<RecipeSerializer<GemCuttingRecipe>> GEM_CUTTING_SERIALIZER =
            SERIALIZERS.register("gem_cutting", GemCuttingRecipeSerializer::new);

    // TODO: GRINDSTONE RECIPES

    public static void register(IEventBus bus) {
        SERIALIZERS.register(bus);
        TYPES.register(bus);
    }
}

