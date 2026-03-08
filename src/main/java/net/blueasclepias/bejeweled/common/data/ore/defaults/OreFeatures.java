package net.blueasclepias.bejeweled.common.data.ore.defaults;

import net.blueasclepias.bejeweled.common.data.ore.definition.OreGenerationFeature;
import net.blueasclepias.bejeweled.server.worldgen.feature.ConfiguredFeatures;
import net.blueasclepias.bejeweled.server.worldgen.feature.PlacedFeatures;
import net.blueasclepias.bejeweled.server.worldgen.placement.BiomePlacements;
import net.blueasclepias.bejeweled.server.worldgen.util.IBiomeFilter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;

import java.util.List;
import java.util.Set;

// TODO: simplify placement and biome
// TODO: ModConfiguredFeatures should already exist, don't create them here
public final class OreFeatures {
    public static final OreGenerationFeature STONE_BERYL = new OreGenerationFeature(
            "stone_beryl_ore",
            OreDefinitions.AQUAMARINE,
            OreVariants.STONE,
            ConfiguredFeatures.STONE_BERYL_CONFIGURED,
            false,
            PlacedFeatures.STONE_BERYL_ORE,
            List.of(
                    CountPlacement.of(6),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(0),
                            VerticalAnchor.absolute(64)
                    )
            ),
            BiomePlacements.update(
                    BiomePlacements.BASIC_GEM_ORE,
                    PlacedFeatures.STONE_BERYL_ORE
            ),
            2
    );

    public static final OreGenerationFeature GRANITE_BERYL = new OreGenerationFeature(
            "granite_beryl_ore",
            OreDefinitions.AQUAMARINE,
            OreVariants.GRANITE,
            ConfiguredFeatures.GRANITE_BERYL_CONFIGURED,
            false,
            PlacedFeatures.GRANITE_BERYL_ORE,
            List.of(
                    CountPlacement.of(10),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(-16),
                            VerticalAnchor.absolute(64)
                    )
            ),
            BiomePlacements.update(
                    BiomePlacements.BASIC_GEM_ORE,
                    PlacedFeatures.GRANITE_BERYL_ORE
            ),
            4
    );

    public static final OreGenerationFeature STONE_RED_CORUNDUM = new OreGenerationFeature(
            "stone_red_corundum_ore",
            OreDefinitions.RED_CORUNDUM,
            OreVariants.STONE,
            ConfiguredFeatures.create("stone_red_corundum_ore"),
            true,
            PlacedFeatures.STONE_RED_CORUNDUM_ORE,
            List.of(
                    CountPlacement.of(6),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(0),
                            VerticalAnchor.absolute(32)
                    )
            ),
            BiomePlacements.update(
                    BiomePlacements.BASIC_GEM_ORE,
                    PlacedFeatures.STONE_RED_CORUNDUM_ORE
            ),
            1
    );

    public static final OreGenerationFeature DEEPSLATE_RED_CORUNDUM = new OreGenerationFeature(
            "deepslate_red_corundum_ore",
            OreDefinitions.RED_CORUNDUM,
            OreVariants.DEEPSLATE,
            ConfiguredFeatures.create("deepslate_red_corundum_ore"),
            true,
            PlacedFeatures.DEEPSLATE_RED_CORUNDUM_ORE,
            List.of(
                    CountPlacement.of(12),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(-48),
                            VerticalAnchor.absolute(7)
                    )
            ),
            BiomePlacements.update(
                    BiomePlacements.BASIC_GEM_ORE,
                    PlacedFeatures.DEEPSLATE_RED_CORUNDUM_ORE
            ),
            1
    );

    public static final OreGenerationFeature DEEPSLATE_BLUE_CORUNDUM = new OreGenerationFeature(
            "deepslate_blue_corundum_ore",
            OreDefinitions.BLUE_CORUNDUM,
            OreVariants.DEEPSLATE,
            ConfiguredFeatures.create("deepslate_blue_corundum_ore"),
            true,
            PlacedFeatures.DEEPSLATE_BLUE_CORUNDUM_ORE,
            List.of(
                    CountPlacement.of(4),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(-32),
                            VerticalAnchor.absolute(7)
                    )
            ),
            BiomePlacements.update(
                    BiomePlacements.BASIC_GEM_ORE,
                    PlacedFeatures.DEEPSLATE_BLUE_CORUNDUM_ORE
            ),
            1
    );

    public static final OreGenerationFeature BASALT_BLUE_CORUNDUM = new OreGenerationFeature(
            "basalt_blue_corundum_ore",
            OreDefinitions.BLUE_CORUNDUM,
            OreVariants.BASALT,
            ConfiguredFeatures.create("basalt_blue_corundum_ore"),
            true,
            PlacedFeatures.BASALT_BLUE_CORUNDUM_ORE,
            List.of(
                    CountPlacement.of(12),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(16),
                            VerticalAnchor.absolute(128)
                    )
            ),
            BiomePlacements.create(
                    "basalt_blue_corundum_ore",
                    Set.of(PlacedFeatures.BASALT_BLUE_CORUNDUM_ORE),
                    new IBiomeFilter.List(
                            Set.of(
                                    ResourceLocation.fromNamespaceAndPath("minecraft", "basalt_deltas")
                            )
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            ),
            1
    );

    public static final OreGenerationFeature STONE_GARNET = new OreGenerationFeature(
            "stone_garnet_ore",
            OreDefinitions.GARNET,
            OreVariants.STONE,
            ConfiguredFeatures.create("stone_garnet_ore"),
            true,
            PlacedFeatures.STONE_GARNET_ORE,
            List.of(
                    CountPlacement.of(10),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(0),
                            VerticalAnchor.absolute(96)
                    )
            ),
            BiomePlacements.update(
                    BiomePlacements.BASIC_GEM_ORE,
                    PlacedFeatures.STONE_GARNET_ORE
            ),
            3
    );

    public static final OreGenerationFeature DEEPSLATE_GARNET =new OreGenerationFeature(
            "deepslate_garnet_ore",
            OreDefinitions.GARNET,
            OreVariants.DEEPSLATE,
            ConfiguredFeatures.create("deepslate_garnet_ore"),
            true,
            PlacedFeatures.DEEPSLATE_GARNET_ORE,
            List.of(
                    CountPlacement.of(6),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(-48),
                            VerticalAnchor.absolute(32)
                    )
            ),
            BiomePlacements.update(
                    BiomePlacements.BASIC_GEM_ORE,
                    PlacedFeatures.DEEPSLATE_GARNET_ORE
            ),
            1
    );

    public static final OreGenerationFeature STONE_TOPAZ = new OreGenerationFeature(
            "stone_topaz_ore",
            OreDefinitions.TOPAZ,
            OreVariants.STONE,
            ConfiguredFeatures.create("stone_topaz_ore"),
            true,
            PlacedFeatures.STONE_TOPAZ_ORE,
            List.of(
                    CountPlacement.of(6),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(0),
                            VerticalAnchor.absolute(96)
                    )
            ),
            BiomePlacements.update(
                    BiomePlacements.BASIC_GEM_ORE,
                    PlacedFeatures.STONE_TOPAZ_ORE
            ),
            3
    );

    public static final OreGenerationFeature GRANITE_TOPAZ = new OreGenerationFeature(
            "granite_topaz_ore",
            OreDefinitions.TOPAZ,
            OreVariants.GRANITE,
            ConfiguredFeatures.create("granite_topaz_ore"),
            true,
            PlacedFeatures.GRANITE_TOPAZ_ORE,
            List.of(
                    CountPlacement.of(12),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(0),
                            VerticalAnchor.absolute(96)
                    )
            ),
            BiomePlacements.update(
                    BiomePlacements.BASIC_GEM_ORE,
                    PlacedFeatures.GRANITE_TOPAZ_ORE
            ),
            6
    );

    public static final OreGenerationFeature DEEPSLATE_TOPAZ = new OreGenerationFeature(
            "deepslate_topaz_ore",
            OreDefinitions.TOPAZ,
            OreVariants.DEEPSLATE,
            ConfiguredFeatures.create("deepslate_topaz_ore"),
            true,
            PlacedFeatures.DEEPSLATE_TOPAZ_ORE,
            List.of(
                    CountPlacement.of(6),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(0),
                            VerticalAnchor.absolute(8)
                    )
            ),
            BiomePlacements.update(
                    BiomePlacements.BASIC_GEM_ORE,
                    PlacedFeatures.DEEPSLATE_TOPAZ_ORE
            ),
            1
    );

    public static final OreGenerationFeature STONE_TURQUOISE = new OreGenerationFeature(
            "stone_turquoise_ore",
            OreDefinitions.TURQUOISE,
            OreVariants.STONE,
            ConfiguredFeatures.ALL_TURQUOISE_CONFIGURED,
            false,
            PlacedFeatures.STONE_TURQUOISE_ORE,
            List.of(
                    CountPlacement.of(10),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(0),
                            VerticalAnchor.absolute(96)
                    )
            ),
            BiomePlacements.create(
                    "stone_turquoise_ore",
                    Set.of(PlacedFeatures.STONE_TURQUOISE_ORE),
                    new IBiomeFilter.List(
                            Set.of(
                                    ResourceLocation.fromNamespaceAndPath("minecraft", "desert"),
                                    ResourceLocation.fromNamespaceAndPath("minecraft", "badlands"),
                                    ResourceLocation.fromNamespaceAndPath("minecraft", "wooded_badlands"),
                                    ResourceLocation.fromNamespaceAndPath("minecraft", "eroded_badlands"),
                                    ResourceLocation.fromNamespaceAndPath("minecraft", "savanna"),
                                    ResourceLocation.fromNamespaceAndPath("minecraft", "savanna_plateau"),
                                    ResourceLocation.fromNamespaceAndPath("minecraft", "windswept_savanna")

                            )
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            ),
            2
    );

    public static final OreGenerationFeature STONE_NEPHRITE = new OreGenerationFeature(
            "stone_nephrite_ore",
            OreDefinitions.NEPHRITE,
            OreVariants.STONE,
            ConfiguredFeatures.create("stone_nephrite_ore"),
            true,
            PlacedFeatures.STONE_NEPHRITE_ORE,
            List.of(
                    CountPlacement.of(6),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(0),
                            VerticalAnchor.absolute(16)
                    )
            ),
            BiomePlacements.create(
                    "all_nephrite_ore",
                    Set.of(PlacedFeatures.STONE_NEPHRITE_ORE),
                    new IBiomeFilter.Tag(
                            ResourceLocation.fromNamespaceAndPath("minecraft", "is_beach")
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            ),
            1
    );

    public static final OreGenerationFeature DEEPSLATE_NEPHRITE = new OreGenerationFeature(
            "deepslate_nephrite_ore",
            OreDefinitions.NEPHRITE,
            OreVariants.DEEPSLATE,
            ConfiguredFeatures.create("deepslate_nephrite_ore"),
            true,
            PlacedFeatures.DEEPSLATE_NEPHRITE_ORE,
            List.of(
                    CountPlacement.of(12),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(-32),
                            VerticalAnchor.absolute(8)
                    )
            ),
            BiomePlacements.update(
                    "all_nephrite_ore",
                    PlacedFeatures.DEEPSLATE_NEPHRITE_ORE
            ),
            2
    );

    public static final OreGenerationFeature STONE_JADEITE = new OreGenerationFeature(
            "stone_jadeite_ore",
            OreDefinitions.JADEITE,
            OreVariants.STONE,
            ConfiguredFeatures.create("stone_jadeite_ore"),
            true,
            PlacedFeatures.STONE_JADEITE_ORE,
            List.of(
                    CountPlacement.of(6),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(0),
                            VerticalAnchor.absolute(16)
                    )
            ),
            BiomePlacements.create(
                    "all_jadeite_ore",
                    Set.of(PlacedFeatures.STONE_JADEITE_ORE),
                    new IBiomeFilter.Tag(
                            ResourceLocation.fromNamespaceAndPath("minecraft", "is_mountain")
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            ),
            1
    );

    public static final OreGenerationFeature DEEPSLATE_JADEITE = new OreGenerationFeature(
            "deepslate_jadeite_ore",
            OreDefinitions.JADEITE,
            OreVariants.DEEPSLATE,
            ConfiguredFeatures.create("deepslate_jadeite_ore"),
            true,
            PlacedFeatures.DEEPSLATE_JADEITE_ORE,
            List.of(
                    CountPlacement.of(12),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(-32),
                            VerticalAnchor.absolute(0)
                    )
            ),
            BiomePlacements.update(
                    "all_jadeite_ore",
                    PlacedFeatures.DEEPSLATE_JADEITE_ORE
            ),
            2
    );

    public static final OreGenerationFeature TERRACOTTA_OPAL = new OreGenerationFeature(
            "terracotta_opal_ore",
            OreDefinitions.OPAL,
            OreVariants.TERRACOTTA,
            ConfiguredFeatures.create("terracotta_opal_ore"),
            true,
            PlacedFeatures.TERRACOTTA_OPAL_ORE,
            List.of(
                    CountPlacement.of(8),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(32),
                            VerticalAnchor.absolute(96)
                    )
            ),
            BiomePlacements.create(
                    "terracotta_opal_ore",
                    Set.of(PlacedFeatures.TERRACOTTA_OPAL_ORE),
                    new IBiomeFilter.Tag(
                            ResourceLocation.fromNamespaceAndPath("minecraft", "is_badlands")
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            ),
            2
    );

    public static final OreGenerationFeature DEEPSLATE_OLIVINE = new OreGenerationFeature(
            "deepslate_olivine_ore",
            OreDefinitions.OLIVINE,
            OreVariants.DEEPSLATE,
            ConfiguredFeatures.ALL_OLIVINE_CONFIGURED,
            false,
            PlacedFeatures.DEEPSLATE_OLIVINE_ORE,
            List.of(
                    CountPlacement.of(8),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(-64),
                            VerticalAnchor.absolute(-32)
                    )
            ),
            BiomePlacements.update(
                    BiomePlacements.BASIC_GEM_ORE,
                    PlacedFeatures.DEEPSLATE_OLIVINE_ORE
            ),
            1
    );
}
