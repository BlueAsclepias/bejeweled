package net.blueasclepias.bejeweled.server.worldgen.placement;

import net.blueasclepias.bejeweled.server.worldgen.feature.PlacedFeatures;
import net.blueasclepias.bejeweled.server.worldgen.util.IBiomeFilter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Defines biome placements for the mod's features.
 */

public class BiomePlacements {

    // Allow Addons to modify this so they don't need to replicate our systems
    public static final Map<String, BiomeFeaturePlacement> ALL = new HashMap<>();

    // ===== Generics =====
    public static final BiomeFeaturePlacement BASIC_GEM_ORE =
            create(
                    "basic_gem_ore",
                    Set.of(),
                    new IBiomeFilter.Tag(
                            ResourceLocation.fromNamespaceAndPath("minecraft", "is_overworld")
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            );

    public static final BiomeFeaturePlacement CORAL_POLYP =
            create("coral_polyp",
            Set.of(PlacedFeatures.CORAL_POLYP),
            new IBiomeFilter.List(
                    Set.of(
                            ResourceLocation.fromNamespaceAndPath("minecraft", "warm_ocean")
                    )
            ),
            GenerationStep.Decoration.VEGETAL_DECORATION);

    public static BiomeFeaturePlacement create(String name,
                                               Set<ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature>> placedFeatures,
                                               IBiomeFilter IBiomeFilter,
                                               GenerationStep.Decoration step) {
        BiomeFeaturePlacement placement = new BiomeFeaturePlacement(
                name,
                placedFeatures,
                IBiomeFilter,
                step
        );
        ALL.put(name, placement);
        return placement;
    }

    public static BiomeFeaturePlacement update(BiomeFeaturePlacement existing,
                                               ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> placedFeature) {
        if(ALL.containsValue(existing)){
            BiomeFeaturePlacement updatedExisting = ALL.get(existing.name());
            updatedExisting.features().add(placedFeature);
            return updatedExisting;
        } else {
            throw new IllegalArgumentException("No BiomeFeaturePlacement with id " + existing.name() + " exists.");
        }
    }

    public static BiomeFeaturePlacement update(String name,
                                               ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> placedFeature) {
        if(ALL.containsKey(name)){
            BiomeFeaturePlacement updatedExisting = ALL.get(name);
            updatedExisting.features().add(placedFeature);
            return updatedExisting;
        } else {
            throw new IllegalArgumentException("No BiomeFeaturePlacement with id " + name + " exists.");
        }
    }
}

