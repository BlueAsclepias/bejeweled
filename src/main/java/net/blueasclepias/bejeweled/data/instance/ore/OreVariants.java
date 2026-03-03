package net.blueasclepias.bejeweled.data.instance.ore;

import net.blueasclepias.bejeweled.data.definition.ore.OreVariant;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class OreVariants {

    public static final OreVariant STONE =
            new OreVariant(
                    "stone",
                Blocks.STONE,
                false,
                false,
                new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                3.0f,
                3.0f,
                SoundType.STONE
            );

    public static final OreVariant DEEPSLATE =
            new OreVariant(
                    "deepslate",
                    Blocks.DEEPSLATE,
                    true,
                    false,
                    new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                    4.5f,
                    3.0f,
                    SoundType.DEEPSLATE
            );

    public static final OreVariant GRANITE =
            new OreVariant(
                    "granite",
                    Blocks.GRANITE,
                    false,
                    false,
                    new BlockMatchTest(Blocks.GRANITE),
                    3.0f,
                    3.0f,
                    SoundType.STONE
            );

    public static final OreVariant BASALT =
            new OreVariant(
                    "basalt",
                    Blocks.BASALT,
                    true,
                    true,
                    new BlockMatchTest(Blocks.BASALT),
                    2.5f,
                    4.2f,
                    SoundType.BASALT);

    public static final OreVariant TERRACOTTA =
            new OreVariant(
                    "terracotta",
                    Blocks.TERRACOTTA,
                    false,
                    false,
                    new BlockMatchTest(Blocks.TERRACOTTA),
                    3.5f,
                    4.2f,
                    SoundType.STONE
            );

}
