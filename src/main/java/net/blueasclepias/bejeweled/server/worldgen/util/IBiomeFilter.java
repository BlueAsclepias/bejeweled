package net.blueasclepias.bejeweled.server.worldgen.util;

import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public sealed interface IBiomeFilter
        permits IBiomeFilter.Tag, IBiomeFilter.List {
    record Tag(ResourceLocation tag) implements IBiomeFilter {}
    record List(Set<ResourceLocation> biomes) implements IBiomeFilter {
        public List {
            if (biomes.isEmpty()) {
                throw new IllegalArgumentException("Biome list must not be empty");
            }
        }
    }
}

