package net.blueasclepias.bejeweled.interfaces;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public interface IOreVariant {
    String id();
    Block baseBlock();
    boolean hasTop();
    boolean hasSide();
    RuleTest ruleTest();
    float hardness();
    float resistance();
    SoundType soundType();
}
