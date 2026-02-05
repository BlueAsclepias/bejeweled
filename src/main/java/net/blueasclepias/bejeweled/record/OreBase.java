package net.blueasclepias.bejeweled.record;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.registries.ForgeRegistries;

public record OreBase (
        Block block,
        RuleTest ruleTest,
        float hardness,
        float resistance,
        SoundType soundType
) {
    public String name(){
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }
}
