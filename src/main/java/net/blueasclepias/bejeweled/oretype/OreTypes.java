package net.blueasclepias.bejeweled.oretype;

import net.blueasclepias.bejeweled.feature.biomeplacement.ModBiomePlacements;
import net.blueasclepias.bejeweled.feature.configuredfeature.ModConfiguredFeatures;
import net.blueasclepias.bejeweled.feature.placedfeature.ModPlacedFeature;
import net.blueasclepias.bejeweled.interfaces.BiomeFilter;
import net.blueasclepias.bejeweled.record.OreFeature;
import net.blueasclepias.bejeweled.record.OreType;
import net.blueasclepias.bejeweled.registry.ModItems;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public final class OreTypes {

    public static final OreType BERYL = new OreType(
            "beryl",
            Set.of(
                    new OreFeature(
                            OreBases.STONE,
                            ModConfiguredFeatures.STONE_BERYL_CONFIGURED,
                            false,
                            ModPlacedFeature.STONE_BERYL_ORE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(2),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(64)
                                    )
                            ),
                            ModBiomePlacements.update(
                                    ModBiomePlacements.BASIC_GEM_ORE,
                                    ModPlacedFeature.STONE_BERYL_ORE
                            ),
                            2
                    ),
                    new OreFeature(
                            OreBases.GRANITE,
                            ModConfiguredFeatures.GRANITE_BERYL_CONFIGURED,
                            false,
                            ModPlacedFeature.GRANITE_BERYL_ORE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(1),
                                    InSquarePlacement.spread(),
                                            HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-16),
                                            VerticalAnchor.absolute(64)
                                            )
                            ),
                            ModBiomePlacements.update(
                                    ModBiomePlacements.BASIC_GEM_ORE,
                                    ModPlacedFeature.GRANITE_BERYL_ORE
                            ),
                            4
                    )
            ),
            () -> ModItems.ROUGH_AQUAMARINE.get()
    );

    public static final OreType RED_CORUNDUM = new OreType(
            "red_corundum",
            Set.of(
                    new OreFeature(
                            OreBases.STONE,
                            ModConfiguredFeatures.create("stone_red_corundum_ore"),
                            true,
                            ModPlacedFeature.STONE_RED_CORUNDUM_ORE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(2),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(32)
                                    )
                            ),
                            ModBiomePlacements.update(
                                    ModBiomePlacements.BASIC_GEM_ORE,
                                    ModPlacedFeature.STONE_RED_CORUNDUM_ORE
                            ),
                            1
                    ),
                    new OreFeature(
                            OreBases.DEEPSLATE,
                            ModConfiguredFeatures.create("deepslate_red_corundum_ore"),
                            true,
                            ModPlacedFeature.DEEPSLATE_RED_CORUNDUM_ORE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(2),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-48),
                                            VerticalAnchor.absolute(7)
                                    )
                            ),
                            ModBiomePlacements.update(
                                    ModBiomePlacements.BASIC_GEM_ORE,
                                    ModPlacedFeature.DEEPSLATE_RED_CORUNDUM_ORE
                            ),
                            1
                    )
            ),
            () -> ModItems.ROUGH_RUBY.get()
    );

    public static final OreType BLUE_CORUNDUM = new OreType(
            "blue_corundum",
            Set.of(
                    new OreFeature(
                            OreBases.DEEPSLATE,
                            ModConfiguredFeatures.create("deepslate_blue_corundum_ore"),
                            true,
                            ModPlacedFeature.DEEPSLATE_BLUE_CORUNDUM_ORE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(4),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-32),
                                            VerticalAnchor.absolute(7)
                                    )
                            ),
                            ModBiomePlacements.update(
                                    ModBiomePlacements.BASIC_GEM_ORE,
                                    ModPlacedFeature.DEEPSLATE_BLUE_CORUNDUM_ORE
                            ),
                            1
                    ),
                    new OreFeature(
                            OreBases.BASALT,
                            ModConfiguredFeatures.create("basalt_blue_corundum_ore"),
                            true,
                            ModPlacedFeature.BASALT_BLUE_CORUNDUM_ORE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(1),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(16),
                                            VerticalAnchor.absolute(128)
                                    )
                            ),
                            ModBiomePlacements.create(
                                    "basalt_blue_corundum_ore",
                                    Set.of(ModPlacedFeature.BASALT_BLUE_CORUNDUM_ORE),
                                    new BiomeFilter.List(
                                            Set.of(
                                                    fromNamespaceAndPath("minecraft", "basalt_deltas")
                                            )
                                    ),
                                    GenerationStep.Decoration.UNDERGROUND_ORES
                            ),
                            1
                    )
            ),
            () -> ModItems.ROUGH_SAPPHIRE.get()
    );

    public static final OreType GARNET = new OreType(
            "garnet",
            Set.of(
                    new OreFeature(
                            OreBases.STONE,
                            ModConfiguredFeatures.create("stone_garnet_ore"),
                            true,
                            ModPlacedFeature.STONE_GARNET_ORE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(1),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(96)
                                    )
                            ),
                            ModBiomePlacements.update(
                                    ModBiomePlacements.BASIC_GEM_ORE,
                                    ModPlacedFeature.STONE_GARNET_ORE
                            ),
                            3
                    ),
                    new OreFeature(
                            OreBases.DEEPSLATE,
                            ModConfiguredFeatures.create("deepslate_garnet_ore"),
                            true,
                            ModPlacedFeature.DEEPSLATE_GARNET_ORE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(3),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-48),
                                            VerticalAnchor.absolute(32)
                                    )
                            ),
                            ModBiomePlacements.update(
                                    ModBiomePlacements.BASIC_GEM_ORE,
                                    ModPlacedFeature.DEEPSLATE_GARNET_ORE
                            ),
                            1
                    )
            ),
            () -> ModItems.ROUGH_GARNET.get()
    );

    public static final OreType TOPAZ = new OreType(
            "topaz",
            Set.of(
                    new OreFeature(
                            OreBases.STONE,
                            ModConfiguredFeatures.create("stone_topaz_ore"),
                            true,
                            ModPlacedFeature.STONE_TOPAZ_ORE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(2),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(96)
                                    )
                            ),
                            ModBiomePlacements.update(
                                    ModBiomePlacements.BASIC_GEM_ORE,
                                    ModPlacedFeature.STONE_TOPAZ_ORE
                            ),
                            3
                    ),
                    new OreFeature(
                            OreBases.GRANITE,
                            ModConfiguredFeatures.create("granite_topaz_ore"),
                            true,
                            ModPlacedFeature.GRANITE_TOPAZ_ORE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(1),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(96)
                                    )
                            ),
                            ModBiomePlacements.update(
                                    ModBiomePlacements.BASIC_GEM_ORE,
                                    ModPlacedFeature.GRANITE_TOPAZ_ORE
                            ),
                            6
                    ),
                    new OreFeature(
                            OreBases.DEEPSLATE,
                            ModConfiguredFeatures.create("deepslate_topaz_ore"),
                            true,
                            ModPlacedFeature.DEEPSLATE_TOPAZ_ORE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(3),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(8)
                                    )
                            ),
                            ModBiomePlacements.update(
                                    ModBiomePlacements.BASIC_GEM_ORE,
                                    ModPlacedFeature.DEEPSLATE_TOPAZ_ORE
                            ),
                            1
                    )
            ),
            () -> ModItems.ROUGH_TOPAZ.get()
    );

    public static final OreType TURQUOISE = new OreType(
            "turquoise",
            Set.of(
                    new OreFeature(
                            OreBases.STONE,
                            ModConfiguredFeatures.ALL_TURQUOISE_CONFIGURED,
                            false,
                            ModPlacedFeature.STONE_TURQUOISE_ORE,
                            List.of(
                                    CountPlacement.of(1),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(96)
                                    )
                            ),
                            ModBiomePlacements.create(
                                    "stone_turquoise_ore",
                                    Set.of(ModPlacedFeature.STONE_TURQUOISE_ORE),
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
                            ),
                            2
                    )
            ),
            () -> ModItems.ROUGH_TURQUOISE.get()
    );

    public static final OreType NEPHRITE = new OreType(
            "nephrite",
            Set.of(
                    new OreFeature(
                            OreBases.STONE,
                            ModConfiguredFeatures.create("stone_nephrite_ore"),
                            true,
                            ModPlacedFeature.STONE_NEPHRITE_ORE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(2),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(16)
                                    )
                            ),
                            ModBiomePlacements.create(
                                    "all_nephrite_ore",
                                    Set.of(ModPlacedFeature.STONE_NEPHRITE_ORE),
                                    new BiomeFilter.Tag(
                                            fromNamespaceAndPath("minecraft", "is_beach")
                                    ),
                                    GenerationStep.Decoration.UNDERGROUND_ORES
                            ),
                            1
                    ),
                    new OreFeature(
                            OreBases.DEEPSLATE,
                            ModConfiguredFeatures.create("deepslate_nephrite_ore"),
                            true,
                            ModPlacedFeature.DEEPSLATE_NEPHRITE_ORE,
                            List.of(
                                    CountPlacement.of(2),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-32),
                                            VerticalAnchor.absolute(8)
                                    )
                            ),
                            ModBiomePlacements.update(
                                    "all_nephrite_ore",
                                    ModPlacedFeature.DEEPSLATE_NEPHRITE_ORE
                            ),
                            2
                    )
            ),
            () -> ModItems.ROUGH_JADE.get()
    );

    public static final OreType JADEITE = new OreType(
            "jadeite",
            Set.of(
                    new OreFeature(
                            OreBases.STONE,
                            ModConfiguredFeatures.create("stone_jadeite_ore"),
                            true,
                            ModPlacedFeature.STONE_JADEITE_ORE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(2),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(16)
                                    )
                            ),
                            ModBiomePlacements.create(
                                    "all_jadeite_ore",
                                    Set.of(ModPlacedFeature.STONE_JADEITE_ORE),
                                    new BiomeFilter.Tag(
                                            fromNamespaceAndPath("minecraft", "is_mountain")
                                    ),
                                    GenerationStep.Decoration.UNDERGROUND_ORES
                            ),
                            1
                    ),
                    new OreFeature(
                            OreBases.DEEPSLATE,
                            ModConfiguredFeatures.create("deepslate_jadeite_ore"),
                            true,
                            ModPlacedFeature.DEEPSLATE_JADEITE_ORE,
                            List.of(
                                    CountPlacement.of(3),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-32),
                                            VerticalAnchor.absolute(0)
                                    )
                            ),
                            ModBiomePlacements.update(
                                    "all_jadeite_ore",
                                    ModPlacedFeature.DEEPSLATE_JADEITE_ORE
                            ),
                            2
                    )
            ),
            () -> ModItems.ROUGH_JADE.get()
    );

    // TODO: ADD MORE TYPES OF TERRACOTTA
    public static final OreType OPAL = new OreType(
            "opal",
            Set.of(
                    new OreFeature(
                            OreBases.TERRACOTTA,
                            ModConfiguredFeatures.create("terracotta_opal_ore"),
                            true,
                            ModPlacedFeature.TERRACOTTA_OPAL_ORE,
                            List.of(
                                    CountPlacement.of(1),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(32),
                                            VerticalAnchor.absolute(96)
                                    )
                            ),
                            ModBiomePlacements.create(
                                    "terracotta_opal_ore",
                                    Set.of(ModPlacedFeature.TERRACOTTA_OPAL_ORE),
                                    new BiomeFilter.Tag(
                                            fromNamespaceAndPath("minecraft", "is_badlands")
                                    ),
                                    GenerationStep.Decoration.UNDERGROUND_ORES
                            ),
                            2
                    )
            ),
            () -> ModItems.ROUGH_OPAL.get()
    );

    public static final OreType OLIVINE = new OreType(
            "olivine",
            Set.of(
                    new OreFeature(
                            OreBases.DEEPSLATE,
                            ModConfiguredFeatures.ALL_OLIVINE_CONFIGURED,
                            false,
                            ModPlacedFeature.DEEPSLATE_OLIVINE_ORE,
                            List.of(
                                    CountPlacement.of(1),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-64),
                                            VerticalAnchor.absolute(-32)
                                    )
                            ),
                            ModBiomePlacements.update(
                                    ModBiomePlacements.BASIC_GEM_ORE,
                                    ModPlacedFeature.DEEPSLATE_OLIVINE_ORE
                            ),
                            1
                    )
            ),
            () -> ModItems.ROUGH_PERIDOT.get()
    );

    // Allow Addons to modify this so they don't need to replicate our systems
    public static final List<OreType> ALL = new ArrayList<>();

    // ===== Static Initializer =====
    static {
        ALL.add(BERYL);
        ALL.add(RED_CORUNDUM);
        ALL.add(BLUE_CORUNDUM);
        ALL.add(GARNET);
        ALL.add(TOPAZ);
        ALL.add(TURQUOISE);
        ALL.add(NEPHRITE);
        ALL.add(JADEITE);
        ALL.add(OPAL);
        ALL.add(OLIVINE);
    }
}
