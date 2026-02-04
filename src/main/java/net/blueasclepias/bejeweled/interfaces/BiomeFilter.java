package net.blueasclepias.bejeweled.interfaces;

import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public sealed interface BiomeFilter
        permits BiomeFilter.Tag, BiomeFilter.List {
    record Tag(ResourceLocation tag) implements BiomeFilter {}
    record List(Set<ResourceLocation> biomes) implements BiomeFilter {
        public List {
            if (biomes.isEmpty()) {
                throw new IllegalArgumentException("Biome list must not be empty");
            }
        }
    }
}

