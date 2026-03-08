package net.blueasclepias.bejeweled.common.data.coral.registry;

import net.blueasclepias.bejeweled.common.block.CoralPolypBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CoralPolypRegistry {
    private static final Map<ResourceLocation, CoralPolypBlock> POLYPS = new HashMap<>();
    private static final Map<Block, CoralPolypBlock> BLOCKS_BY_POLYP = new HashMap<>();

    public static void bind(CoralPolypBlock polypBlock, Block coralBlock, @NotNull ResourceLocation id) {
        POLYPS.put(id, polypBlock);
        BLOCKS_BY_POLYP.put(coralBlock, polypBlock);
    }

    public static Map<ResourceLocation, CoralPolypBlock> all() {
        return POLYPS;
    }

    public static Collection<CoralPolypBlock> allBlocks() {
        return POLYPS.values();
    }

    public static CoralPolypBlock findCoralPolyp(Block coralBlock) {
        return BLOCKS_BY_POLYP.getOrDefault(coralBlock, null);
    }

}
