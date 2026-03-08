package net.blueasclepias.bejeweled.common.data.storage.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class StorageBlockRegistry {
    private static final Map<ResourceLocation, Block> BLOCKS = new HashMap<>();
    private static final Map<ResourceLocation, ResourceLocation> INGREDIENTS_BY_ID = new HashMap<>();

    public static void bind(Block block, @NotNull ResourceLocation ingredient,@NotNull ResourceLocation id) {
        BLOCKS.put(id, block);
        INGREDIENTS_BY_ID.put(id, ingredient);
    }

    public static Map<ResourceLocation, Block> all() {
        return BLOCKS;
    }

    public static Collection<Block> allBlocks() {
        return BLOCKS.values();
    }

    public static ResourceLocation getIngredient(ResourceLocation id) {
        return INGREDIENTS_BY_ID.getOrDefault(id, null);
    }

}
