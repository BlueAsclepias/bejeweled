package net.blueasclepias.bejeweled.oretype;

import net.blueasclepias.bejeweled.record.OreBase;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.HashMap;
import java.util.Map;

public class OreBases {

    public static final Map<Block, OreBase> ALL = new HashMap<>();

    public static final OreBase STONE = create(Blocks.STONE, new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),3.0f, 3.0f, SoundType.STONE);
    public static final OreBase DEEPSLATE = create(Blocks.DEEPSLATE, new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), 4.5f, 3.0f, SoundType.DEEPSLATE);
    public static final OreBase GRANITE = create(Blocks.GRANITE, new BlockMatchTest(Blocks.GRANITE), 3.0f, 3.0f, SoundType.STONE);
    public static final OreBase BASALT = create(Blocks.BASALT, new BlockMatchTest(Blocks.BASALT),2.5f, 4.2f, SoundType.BASALT);
    public static final OreBase TERRACOTTA = create(Blocks.TERRACOTTA, new BlockMatchTest(Blocks.TERRACOTTA), 3.5f, 4.2f, SoundType.STONE);

    public static OreBase create(Block block, RuleTest ruleTest, float hardness, float resistance, SoundType soundType) {
        OreBase oreBase = new OreBase(block, ruleTest, hardness, resistance, soundType);
        ALL.put(block, oreBase);
        return oreBase;

    }

}
