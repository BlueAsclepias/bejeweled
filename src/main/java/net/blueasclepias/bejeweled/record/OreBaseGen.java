package net.blueasclepias.bejeweled.record;

import net.blueasclepias.bejeweled.enums.OreBase;

public record OreBaseGen(
        OreBase base,
        OreGenSettings genSettings
) {}