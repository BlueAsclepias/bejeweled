package net.blueasclepias.bejeweled.common.data.ore.definition;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

/**
 * A base-block flavor an ore can generate in (stone, deepslate, granite, ...), along with the block-model and
 * worldgen-replacement metadata that flavor needs.
 */
public record OreBlockVariant(
        String id,
        Block baseBlock,
        boolean hasTop,
        boolean hasSide,
        RuleTest ruleTest,
        float hardness,
        float resistance,
        SoundType soundType
) {
}
