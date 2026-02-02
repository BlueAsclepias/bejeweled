package net.blueasclepias.bejeweled.record;

import net.blueasclepias.bejeweled.interfaces.BiomeFilter;

import javax.annotation.Nullable;

public record OreGenSettings (
        int veinSize,
        int veinsPerChunk,
        int minY,
        int maxY,
        @Nullable BiomeFilter biomeFilter
){}
