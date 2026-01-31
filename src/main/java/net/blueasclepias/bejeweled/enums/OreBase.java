package net.blueasclepias.bejeweled.enums;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;

public enum OreBase {
    STONE(Blocks.STONE, 3.0f, 3.0f, SoundType.STONE),
    DEEPSLATE(Blocks.DEEPSLATE, 4.5f, 3.0f, SoundType.DEEPSLATE),
    GRANITE(Blocks.GRANITE, 3.0f, 3.0f, SoundType.STONE),
    BASALT(Blocks.BASALT, 2.5f, 4.2f, SoundType.BASALT),
    TERRACOTTA(Blocks.TERRACOTTA, 3.5f, 4.2f, SoundType.STONE);

    private final Block vanilla;
    private final float hardness;
    private final float resistance;
    private final SoundType soundType;

    OreBase(Block vanilla, float hardness, float resistance, SoundType soundType) {
        this.vanilla = vanilla;
        this.hardness = hardness;
        this.resistance = resistance;
        this.soundType = soundType;
    }

    public Block vanilla() { return vanilla; }
    public float hardness() { return hardness; }
    public float resistance() { return resistance; }
    public SoundType sound() { return soundType; }

}
