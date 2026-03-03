package net.blueasclepias.bejeweled.data.definition.ore;

import net.blueasclepias.bejeweled.interfaces.IOreVariant;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public record OreVariant(
        String id,
        Block baseBlock,
        boolean hasTop,
        boolean hasSide,
        RuleTest ruleTest,
        float hardness,
        float resistance,
        SoundType soundType
) implements IOreVariant {
    public OreVariant(
            String id,
            Block baseBlock,
            boolean hasTop,
            boolean hasSide,
            RuleTest ruleTest,
            float hardness,
            float resistance,
            SoundType soundType){
        this.id = id;
        this.baseBlock = baseBlock;
        this.hasTop = hasTop;
        this.hasSide = hasSide;
        this.ruleTest = ruleTest;
        this.hardness = hardness;
        this.resistance = resistance;
        this.soundType = soundType;
    }
}
