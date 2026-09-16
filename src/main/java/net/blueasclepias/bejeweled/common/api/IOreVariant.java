package net.blueasclepias.bejeweled.common.api;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

/**
 * Common contract for ore block variants.
 */
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
