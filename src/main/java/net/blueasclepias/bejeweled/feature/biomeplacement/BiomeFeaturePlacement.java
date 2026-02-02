package net.blueasclepias.bejeweled.feature.biomeplacement;

import com.mojang.datafixers.util.Either;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public record BiomeFeaturePlacement(
        ResourceKey<PlacedFeature> feature,
        Either<ResourceKey<Biome>, TagKey<Biome>> biome,
        GenerationStep.Decoration step
) {}
