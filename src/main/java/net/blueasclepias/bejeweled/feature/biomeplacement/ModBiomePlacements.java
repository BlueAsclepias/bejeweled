package net.blueasclepias.bejeweled.feature.biomeplacement;

import net.blueasclepias.bejeweled.feature.placedfeature.ModPlacedFeature;
import net.blueasclepias.bejeweled.interfaces.BiomeFilter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.*;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

/**
 * Defines biome placements for the mod's features.
 */
public class ModBiomePlacements {

    // Allow Addons to modify this so they don't need to replicate our systems
    public static final List<BiomeFeaturePlacement> ALL = new ArrayList<>();

    // ===== Generics =====
    public static final BiomeFeaturePlacement BASIC_GEM_ORE =
            new BiomeFeaturePlacement(
                    "basic_gem_ore",
                    Set.of(),
                    new BiomeFilter.Tag(
                            fromNamespaceAndPath("minecraft", "is_overworld")
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            );

    public static final BiomeFeaturePlacement CORAL_POLYP =
            new BiomeFeaturePlacement(
                    "coral_polyp",
                    Set.of(ModPlacedFeature.ALL.get("coral_polyp")),
                    new BiomeFilter.List(
                            Set.of(
                                    fromNamespaceAndPath("minecraft", "warm_ocean")
                            )
                    ),
                    GenerationStep.Decoration.VEGETAL_DECORATION
            );

    // ===== Static Initializer =====
    static {
        // === generics ===
        ALL.add(CORAL_POLYP);
        ALL.add(BASIC_GEM_ORE);
    }

    public static BiomeFeaturePlacement addPlacedFeature(String name,
                                                         ResourceKey<PlacedFeature> placedFeature) {
        Optional<BiomeFeaturePlacement> existing = ALL.stream().filter(p -> p.name().equals(name)).findFirst();
        if(existing.isPresent()){
            BiomeFeaturePlacement updatedExisting = existing.get();
            updatedExisting.features().add(placedFeature);
            return updatedExisting;
        } else {
            throw new IllegalArgumentException("No BiomeFeaturePlacement with name " + name + " exists.");
        }
    }

    public static BiomeFeaturePlacement createOrUpdate(String name,
                                                       Set<ResourceKey<PlacedFeature>> placedFeatures,
                                                       BiomeFilter biomeFilter,
                                                       GenerationStep.Decoration step) {
        BiomeFeaturePlacement placement = new BiomeFeaturePlacement(
                name,
                placedFeatures,
                biomeFilter,
                step
        );
        Optional<BiomeFeaturePlacement> existing = ALL.stream().filter(placement::equals).findFirst();
        if(existing.isPresent()){
            BiomeFeaturePlacement updatedExisting = existing.get();
            updatedExisting.features().addAll(placedFeatures);
            return updatedExisting;
        } else {
            ALL.add(placement);
            return placement;
        }
    }
}

