package net.blueasclepias.bejeweled.content.ore;

import net.blueasclepias.bejeweled.record.ore.OreVariant;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.HashMap;
import java.util.Map;

public class OreVariants {

    public static final Map<Block, OreVariant> ALL = new HashMap<>();

    public static final OreVariant STONE = create(Blocks.STONE, false, false, new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),3.0f, 3.0f, SoundType.STONE);
    public static final OreVariant DEEPSLATE = create(Blocks.DEEPSLATE, true, false, new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), 4.5f, 3.0f, SoundType.DEEPSLATE);
    public static final OreVariant GRANITE = create(Blocks.GRANITE, false, false, new BlockMatchTest(Blocks.GRANITE), 3.0f, 3.0f, SoundType.STONE);
    public static final OreVariant BASALT = create(Blocks.BASALT, true, true, new BlockMatchTest(Blocks.BASALT),2.5f, 4.2f, SoundType.BASALT);
    public static final OreVariant TERRACOTTA = create(Blocks.TERRACOTTA, false, false, new BlockMatchTest(Blocks.TERRACOTTA), 3.5f, 4.2f, SoundType.STONE);

    public static OreVariant create(Block block, boolean hasTop, boolean hasSide, RuleTest ruleTest, float hardness, float resistance, SoundType soundType) {
        OreVariant oreVariant = new OreVariant(block, hasTop, hasSide, ruleTest, hardness, resistance, soundType);
        ALL.put(block, oreVariant);
        return oreVariant;

    }

}
