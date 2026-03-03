package net.blueasclepias.bejeweled.data.instance.ore;

import net.blueasclepias.bejeweled.data.definition.ore.OreFeature;
import net.blueasclepias.bejeweled.feature.biomeplacement.ModBiomePlacements;
import net.blueasclepias.bejeweled.feature.configuredfeature.ModConfiguredFeatures;
import net.blueasclepias.bejeweled.feature.placedfeature.ModPlacedFeature;
import net.blueasclepias.bejeweled.interfaces.BiomeFilter;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;

import java.util.List;
import java.util.Set;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

// TODO: simplify placement and biome
// TODO: ModConfiguredFeatures should already exist, don't create them here
public final class OreFeatures {
    public static final OreFeature STONE_BERYL = new OreFeature(
            "stone_beryl_ore",
            OreDefinitions.AQUAMARINE,
            OreVariants.STONE,
            ModConfiguredFeatures.STONE_BERYL_CONFIGURED,
            false,
            ModPlacedFeature.STONE_BERYL_ORE,
            List.of(
                    CountPlacement.of(6),
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
    );

    public static final OreFeature GRANITE_BERYL = new OreFeature(
            "granite_beryl_ore",
            OreDefinitions.AQUAMARINE,
            OreVariants.GRANITE,
            ModConfiguredFeatures.GRANITE_BERYL_CONFIGURED,
            false,
            ModPlacedFeature.GRANITE_BERYL_ORE,
            List.of(
                    CountPlacement.of(10),
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
    );

    public static final OreFeature STONE_RED_CORUNDUM = new OreFeature(
            "stone_red_corundum_ore",
            OreDefinitions.RED_CORUNDUM,
            OreVariants.STONE,
            ModConfiguredFeatures.create("stone_red_corundum_ore"),
            true,
            ModPlacedFeature.STONE_RED_CORUNDUM_ORE,
            List.of(
                    CountPlacement.of(6),
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
    );

    public static final OreFeature DEEPSLATE_RED_CORUNDUM = new OreFeature(
            "deepslate_red_corundum_ore",
            OreDefinitions.RED_CORUNDUM,
            OreVariants.DEEPSLATE,
            ModConfiguredFeatures.create("deepslate_red_corundum_ore"),
            true,
            ModPlacedFeature.DEEPSLATE_RED_CORUNDUM_ORE,
            List.of(
                    CountPlacement.of(12),
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
    );

    public static final OreFeature DEEPSLATE_BLUE_CORUNDUM = new OreFeature(
            "deepslate_blue_corundum_ore",
            OreDefinitions.BLUE_CORUNDUM,
            OreVariants.DEEPSLATE,
            ModConfiguredFeatures.create("deepslate_blue_corundum_ore"),
            true,
            ModPlacedFeature.DEEPSLATE_BLUE_CORUNDUM_ORE,
            List.of(
                    CountPlacement.of(4),
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
    );

    public static final OreFeature BASALT_BLUE_CORUNDUM = new OreFeature(
            "basalt_blue_corundum_ore",
            OreDefinitions.BLUE_CORUNDUM,
            OreVariants.BASALT,
            ModConfiguredFeatures.create("basalt_blue_corundum_ore"),
            true,
            ModPlacedFeature.BASALT_BLUE_CORUNDUM_ORE,
            List.of(
                    CountPlacement.of(12),
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
    );

    public static final OreFeature STONE_GARNET = new OreFeature(
            "stone_garnet_ore",
            OreDefinitions.GARNET,
            OreVariants.STONE,
            ModConfiguredFeatures.create("stone_garnet_ore"),
            true,
            ModPlacedFeature.STONE_GARNET_ORE,
            List.of(
                    CountPlacement.of(10),
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
    );

    public static final OreFeature DEEPSLATE_GARNET =new OreFeature(
            "deepslate_garnet_ore",
            OreDefinitions.GARNET,
            OreVariants.DEEPSLATE,
            ModConfiguredFeatures.create("deepslate_garnet_ore"),
            true,
            ModPlacedFeature.DEEPSLATE_GARNET_ORE,
            List.of(
                    CountPlacement.of(6),
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
    );

    public static final OreFeature STONE_TOPAZ = new OreFeature(
            "stone_topaz_ore",
            OreDefinitions.TOPAZ,
            OreVariants.STONE,
            ModConfiguredFeatures.create("stone_topaz_ore"),
            true,
            ModPlacedFeature.STONE_TOPAZ_ORE,
            List.of(
                    CountPlacement.of(6),
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
    );

    public static final OreFeature GRANITE_TOPAZ = new OreFeature(
            "granite_topaz_ore",
            OreDefinitions.TOPAZ,
            OreVariants.GRANITE,
            ModConfiguredFeatures.create("granite_topaz_ore"),
            true,
            ModPlacedFeature.GRANITE_TOPAZ_ORE,
            List.of(
                    CountPlacement.of(12),
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
    );

    public static final OreFeature DEEPSLATE_TOPAZ = new OreFeature(
            "deepslate_topaz_ore",
            OreDefinitions.TOPAZ,
            OreVariants.DEEPSLATE,
            ModConfiguredFeatures.create("deepslate_topaz_ore"),
            true,
            ModPlacedFeature.DEEPSLATE_TOPAZ_ORE,
            List.of(
                    CountPlacement.of(6),
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
    );

    public static final OreFeature STONE_TURQUOISE = new OreFeature(
            "stone_turquoise_ore",
            OreDefinitions.TURQUOISE,
            OreVariants.STONE,
            ModConfiguredFeatures.ALL_TURQUOISE_CONFIGURED,
            false,
            ModPlacedFeature.STONE_TURQUOISE_ORE,
            List.of(
                    CountPlacement.of(10),
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
    );

    public static final OreFeature STONE_NEPHRITE = new OreFeature(
            "stone_nephrite_ore",
            OreDefinitions.NEPHRITE,
            OreVariants.STONE,
            ModConfiguredFeatures.create("stone_nephrite_ore"),
            true,
            ModPlacedFeature.STONE_NEPHRITE_ORE,
            List.of(
                    CountPlacement.of(6),
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
    );

    public static final OreFeature DEEPSLATE_NEPHRITE = new OreFeature(
            "deepslate_nephrite_ore",
            OreDefinitions.NEPHRITE,
            OreVariants.DEEPSLATE,
            ModConfiguredFeatures.create("deepslate_nephrite_ore"),
            true,
            ModPlacedFeature.DEEPSLATE_NEPHRITE_ORE,
            List.of(
                    CountPlacement.of(12),
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
    );

    public static final OreFeature STONE_JADEITE = new OreFeature(
            "stone_jadeite_ore",
            OreDefinitions.JADEITE,
            OreVariants.STONE,
            ModConfiguredFeatures.create("stone_jadeite_ore"),
            true,
            ModPlacedFeature.STONE_JADEITE_ORE,
            List.of(
                    CountPlacement.of(6),
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
    );

    public static final OreFeature DEEPSLATE_JADEITE = new OreFeature(
            "deepslate_jadeite_ore",
            OreDefinitions.JADEITE,
            OreVariants.DEEPSLATE,
            ModConfiguredFeatures.create("deepslate_jadeite_ore"),
            true,
            ModPlacedFeature.DEEPSLATE_JADEITE_ORE,
            List.of(
                    CountPlacement.of(12),
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
    );

    public static final OreFeature TERRACOTTA_OPAL = new OreFeature(
            "terracotta_opal_ore",
            OreDefinitions.OPAL,
            OreVariants.TERRACOTTA,
            ModConfiguredFeatures.create("terracotta_opal_ore"),
            true,
            ModPlacedFeature.TERRACOTTA_OPAL_ORE,
            List.of(
                    CountPlacement.of(8),
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
    );

    public static final OreFeature DEEPSLATE_OLIVINE = new OreFeature(
            "deepslate_olivine_ore",
            OreDefinitions.OLIVINE,
            OreVariants.DEEPSLATE,
            ModConfiguredFeatures.ALL_OLIVINE_CONFIGURED,
            false,
            ModPlacedFeature.DEEPSLATE_OLIVINE_ORE,
            List.of(
                    CountPlacement.of(8),
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
    );
}
