package net.blueasclepias.bejeweled.record.ore;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.registries.ForgeRegistries;

public record OreVariant(
        Block block,
        boolean hasTop,
        boolean hasSide,
        RuleTest ruleTest,
        float hardness,
        float resistance,
        SoundType soundType
) {
    public String name(){
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }
}
