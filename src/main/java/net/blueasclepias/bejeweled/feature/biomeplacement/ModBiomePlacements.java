package net.blueasclepias.bejeweled.feature.biomeplacement;

import net.blueasclepias.bejeweled.feature.placedfeature.ModPlacedFeature;
import net.blueasclepias.bejeweled.interfaces.BiomeFilter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

/**
 * Defines biome placements for the mod's features.
 */
public class ModBiomePlacements {

    // Allow Addons to modify this so they don't need to replicate our systems
    public static final Map<String, BiomeFeaturePlacement> ALL = new HashMap<>();

    // ===== Generics =====
    public static final BiomeFeaturePlacement BASIC_GEM_ORE =
            create(
                    "basic_gem_ore",
                    Set.of(),
                    new BiomeFilter.Tag(
                            fromNamespaceAndPath("minecraft", "is_overworld")
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            );

    public static final BiomeFeaturePlacement CORAL_POLYP =
            create("coral_polyp",
            Set.of(ModPlacedFeature.CORAL_POLYP),
            new BiomeFilter.List(
                    Set.of(
                            fromNamespaceAndPath("minecraft", "warm_ocean")
                    )
            ),
            GenerationStep.Decoration.VEGETAL_DECORATION);

        public static BiomeFeaturePlacement create(String name,
                                               Set<ResourceKey<PlacedFeature>> placedFeatures,
                                               BiomeFilter biomeFilter,
                                               GenerationStep.Decoration step) {
        BiomeFeaturePlacement placement = new BiomeFeaturePlacement(
                name,
                placedFeatures,
                biomeFilter,
                step
        );
        ALL.put(name, placement);
        return placement;
    }

    public static BiomeFeaturePlacement update(BiomeFeaturePlacement existing,
                                               ResourceKey<PlacedFeature> placedFeature) {
        if(ALL.containsValue(existing)){
            BiomeFeaturePlacement updatedExisting = ALL.get(existing.name());
            updatedExisting.features().add(placedFeature);
            return updatedExisting;
        } else {
            throw new IllegalArgumentException("No BiomeFeaturePlacement with name " + existing.name() + " exists.");
        }
    }

    public static BiomeFeaturePlacement update(String name,
                                               ResourceKey<PlacedFeature> placedFeature) {
        if(ALL.containsKey(name)){
            BiomeFeaturePlacement updatedExisting = ALL.get(name);
            updatedExisting.features().add(placedFeature);
            return updatedExisting;
        } else {
            throw new IllegalArgumentException("No BiomeFeaturePlacement with name " + name + " exists.");
        }
    }
}

