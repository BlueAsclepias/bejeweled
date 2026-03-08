package net.blueasclepias.bejeweled.common.data.ore.defaults;

import net.blueasclepias.bejeweled.common.data.ore.definition.OreBlockVariant;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class OreVariants {

    public static final OreBlockVariant STONE =
            new OreBlockVariant(
                    "stone",
                Blocks.STONE,
                false,
                false,
                new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                3.0f,
                3.0f,
                SoundType.STONE
            );

    public static final OreBlockVariant DEEPSLATE =
            new OreBlockVariant(
                    "deepslate",
                    Blocks.DEEPSLATE,
                    true,
                    false,
                    new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                    4.5f,
                    3.0f,
                    SoundType.DEEPSLATE
            );

    public static final OreBlockVariant GRANITE =
            new OreBlockVariant(
                    "granite",
                    Blocks.GRANITE,
                    false,
                    false,
                    new BlockMatchTest(Blocks.GRANITE),
                    3.0f,
                    3.0f,
                    SoundType.STONE
            );

    public static final OreBlockVariant BASALT =
            new OreBlockVariant(
                    "basalt",
                    Blocks.BASALT,
                    true,
                    true,
                    new BlockMatchTest(Blocks.BASALT),
                    2.5f,
                    4.2f,
                    SoundType.BASALT);

    public static final OreBlockVariant TERRACOTTA =
            new OreBlockVariant(
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
