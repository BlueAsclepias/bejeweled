package net.blueasclepias.bejeweled.common.data.ore.definition;

import net.blueasclepias.bejeweled.common.api.IOreVariant;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public record OreBlockVariant(
        String id,
        Block baseBlock,
        boolean hasTop,
        boolean hasSide,
        RuleTest ruleTest,
        float hardness,
        float resistance,
        SoundType soundType
) implements IOreVariant {
    public OreBlockVariant(
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
