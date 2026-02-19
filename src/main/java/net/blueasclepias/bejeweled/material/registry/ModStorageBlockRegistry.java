package net.blueasclepias.bejeweled.material.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ModStorageBlockRegistry {
    private static final Map<ResourceLocation, Block> BLOCKS = new HashMap<>();

    public static void bind(Block block, @NotNull ResourceLocation id) {
        BLOCKS.put(id, block);
    }

    public static Map<ResourceLocation, Block> all() {
        return BLOCKS;
    }

    public static Collection<Block> allBlocks() {
        return BLOCKS.values();
    }

}
