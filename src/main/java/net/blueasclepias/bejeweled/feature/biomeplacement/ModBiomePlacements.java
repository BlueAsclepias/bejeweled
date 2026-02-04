package net.blueasclepias.bejeweled.feature.biomeplacement;

import net.blueasclepias.bejeweled.feature.placedfeature.ModPlacedFeature;
import net.blueasclepias.bejeweled.interfaces.BiomeFilter;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
                    ModPlacedFeature.create("basic_gem_ore"),
                    new BiomeFilter.Tag(
                            fromNamespaceAndPath("minecraft", "is_overworld")
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            );

    public static final BiomeFeaturePlacement CORAL_POLYP =
            new BiomeFeaturePlacement(
                    ModPlacedFeature.create("coral_polyp"),
                    new BiomeFilter.List(
                            Set.of(
                                    fromNamespaceAndPath("minecraft", "warm_ocean")
                            )
                    ),
                    GenerationStep.Decoration.VEGETAL_DECORATION
            );

    // ===== Specifics =====
    public static final BiomeFeaturePlacement BASALT_BLUE_CORUNDUM =
            new BiomeFeaturePlacement(
                    ModPlacedFeature.create("basalt_blue_corundum"),
                    new BiomeFilter.List(
                            Set.of(
                                    fromNamespaceAndPath("minecraft", "basalt_deltas")
                            )
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            );

    public static final BiomeFeaturePlacement TURQUOISE =
            new BiomeFeaturePlacement(
                    ModPlacedFeature.create("turquoise"),
                    new BiomeFilter.List(
                            Set.of(
                                    fromNamespaceAndPath("minecraft", "desert"),
                                    fromNamespaceAndPath("minecraft", "badlands"),
                                    fromNamespaceAndPath("minecraft", "wooded_badlands"),
                                    fromNamespaceAndPath("minecraft", "eroded_badlands"),
                                    fromNamespaceAndPath("minecraft", "savanna"),
                                    fromNamespaceAndPath("minecraft", "savanna_plateau"),
                                    fromNamespaceAndPath("minecraft", "windswept_savanna")

                            )
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            );

    public static final BiomeFeaturePlacement NEPHRITE =
            new BiomeFeaturePlacement(
                    ModPlacedFeature.create("nephrite"),
                    new BiomeFilter.Tag(
                            fromNamespaceAndPath("minecraft", "is_beach")
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            );

    public static final BiomeFeaturePlacement JADEITE =
            new BiomeFeaturePlacement(
                    ModPlacedFeature.create("jadeite"),
                    new BiomeFilter.Tag(
                            fromNamespaceAndPath("minecraft", "is_mountain")
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            );
    public static final BiomeFeaturePlacement OPAL =
            new BiomeFeaturePlacement(
                    ModPlacedFeature.create("opal"),
                    new BiomeFilter.Tag(
                            fromNamespaceAndPath("minecraft", "is_badlands")
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            );

    // ===== Static Initializer =====
    static {
        // === generics ===
        ALL.add(CORAL_POLYP);
        ALL.add(BASIC_GEM_ORE);

        // === specifics ===
        ALL.add(BASALT_BLUE_CORUNDUM);
        ALL.add(TURQUOISE);
        ALL.add(NEPHRITE);
        ALL.add(JADEITE);
        ALL.add(OPAL);
    }
}

