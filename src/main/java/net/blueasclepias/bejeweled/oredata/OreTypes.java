package net.blueasclepias.bejeweled.oredata;

import net.blueasclepias.bejeweled.enums.OreBase;
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
                            OreBase.STONE,
                            ModConfiguredFeatures.STONE_BERYL_CONFIGURED, // only near granite in caves
                            ModPlacedFeature.create("stone_beryl_ore"),
                            List.of(
                                    RarityFilter.onAverageOnceEvery(12),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(64)
                                    )
                            ),
                            ModBiomePlacements.addPlacedFeature(
                                    "basic_gem_ore",
                                    ModPlacedFeature.ALL.get("stone_beryl_ore")
                            )
                    ),
                    new OreFeature(
                            OreBase.GRANITE,
                            ModConfiguredFeatures.GRANITE_BERYL_CONFIGURED, // only in caves
                            ModPlacedFeature.create("granite_beryl_ore"),
                            List.of(
                                    RarityFilter.onAverageOnceEvery(6),
                                    InSquarePlacement.spread(),
                                            HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-16),
                                            VerticalAnchor.absolute(64)
                                            )
                            ),
                            ModBiomePlacements.addPlacedFeature(
                                    "basic_gem_ore",
                                    ModPlacedFeature.ALL.get("granite_beryl_ore")
                            )
                    )
            ),
            () -> ModItems.ROUGH_AQUAMARINE.get()
    );

    public static final OreType RED_CORUNDUM = new OreType(
            "red_corundum",
            Set.of(
                    new OreFeature(
                            OreBase.STONE,
                            ModConfiguredFeatures.BASIC_GEM_CONFIGURED,
                            ModPlacedFeature.create("stone_red_corundum_ore"),
                            List.of(
                                    RarityFilter.onAverageOnceEvery(8),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(32)
                                    )
                            ),
                            ModBiomePlacements.addPlacedFeature(
                                    "basic_gem_ore",
                                    ModPlacedFeature.ALL.get("stone_red_corundum_ore")
                            )
                    ),
                    new OreFeature(
                            OreBase.DEEPSLATE,
                            ModConfiguredFeatures.BASIC_GEM_CONFIGURED,
                            ModPlacedFeature.create("deepslate_red_corundum_ore"),
                            List.of(
                                    RarityFilter.onAverageOnceEvery(4),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-48),
                                            VerticalAnchor.absolute(7)
                                    )
                            ),
                            ModBiomePlacements.addPlacedFeature(
                                    "basic_gem_ore",
                                    ModPlacedFeature.ALL.get("deepslate_red_corundum_ore")
                            )
                    )
            ),
            () -> ModItems.ROUGH_RUBY.get()
    );

    public static final OreType BLUE_CORUNDUM = new OreType(
            "blue_corundum",
            Set.of(
                    new OreFeature(
                            OreBase.DEEPSLATE,
                            ModConfiguredFeatures.BASIC_GEM_CONFIGURED,
                            ModPlacedFeature.create("deepslate_blue_corundum_ore"),
                            List.of(
                                    RarityFilter.onAverageOnceEvery(6),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-32),
                                            VerticalAnchor.absolute(7)
                                    )
                            ),
                            ModBiomePlacements.addPlacedFeature(
                                    "basic_gem_ore",
                                    ModPlacedFeature.ALL.get("deepslate_blue_corundum_ore")
                            )
                    ),
                    new OreFeature(
                            OreBase.BASALT,
                            ModConfiguredFeatures.BASIC_GEM_CONFIGURED, 
                            ModPlacedFeature.create("basalt_blue_corundum_ore"),
                            List.of(
                                    RarityFilter.onAverageOnceEvery(6),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(16),
                                            VerticalAnchor.absolute(128)
                                    )
                            ),
                            ModBiomePlacements.createOrUpdate(
                                    "basalt_blue_corundum_ore",
                                    Set.of(ModPlacedFeature.ALL.get("basalt_blue_corundum_ore")),
                                    new BiomeFilter.List(
                                            Set.of(
                                                    fromNamespaceAndPath("minecraft", "basalt_deltas")
                                            )
                                    ),
                                    GenerationStep.Decoration.UNDERGROUND_ORES
                            )
                    )
            ),
            () -> ModItems.ROUGH_SAPPHIRE.get()
    );

    public static final OreType GARNET = new OreType(
            "garnet",
            Set.of(
                    new OreFeature(
                            OreBase.STONE,
                            ModConfiguredFeatures.BASIC_GEM_CONFIGURED,
                            ModPlacedFeature.create("stone_garnet_ore"),
                            List.of(
                                    CountPlacement.of(8),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(96)
                                    )
                            ),
                            ModBiomePlacements.addPlacedFeature(
                                    "basic_gem_ore",
                                    ModPlacedFeature.ALL.get("stone_garnet_ore")
                            )
                    ),
                    new OreFeature(
                            OreBase.DEEPSLATE,
                            ModConfiguredFeatures.BASIC_GEM_CONFIGURED,
                            ModPlacedFeature.create("deepslate_garnet_ore"),
                            List.of(
                                    RarityFilter.onAverageOnceEvery(4),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-48),
                                            VerticalAnchor.absolute(32)
                                    )
                            ),
                            ModBiomePlacements.addPlacedFeature(
                                    "basic_gem_ore",
                                    ModPlacedFeature.ALL.get("deepslate_garnet_ore")
                            )
                    )
            ),
            () -> ModItems.ROUGH_GARNET.get()
    );

    public static final OreType TOPAZ = new OreType(
            "topaz",
            Set.of(
                    new OreFeature(
                            OreBase.STONE,
                            ModConfiguredFeatures.BASIC_GEM_CONFIGURED,
                            ModPlacedFeature.create("stone_topaz_ore"),
                            List.of(
                                    RarityFilter.onAverageOnceEvery(4),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(96)
                                    )
                            ),
                            ModBiomePlacements.addPlacedFeature(
                                    "basic_gem_ore",
                                    ModPlacedFeature.ALL.get("stone_topaz_ore")
                            )
                    ),
                    new OreFeature(
                            OreBase.GRANITE,
                            ModConfiguredFeatures.BASIC_GEM_CONFIGURED,
                            ModPlacedFeature.create("granite_topaz_ore"),
                            List.of(
                                    CountPlacement.of(5),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(96)
                                    )
                            ),
                            ModBiomePlacements.addPlacedFeature(
                                    "basic_gem_ore",
                                    ModPlacedFeature.ALL.get("granite_topaz_ore")
                            )
                    ),
                    new OreFeature(
                            OreBase.DEEPSLATE,
                            ModConfiguredFeatures.BASIC_GEM_CONFIGURED,
                            ModPlacedFeature.create("deepslate_topaz_ore"),
                            List.of(
                                    RarityFilter.onAverageOnceEvery(4),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(8)
                                    )
                            ),
                            ModBiomePlacements.addPlacedFeature(
                                    "basic_gem_ore",
                                    ModPlacedFeature.ALL.get("deepslate_topaz_ore")
                            )
                    )
            ),
            () -> ModItems.ROUGH_TOPAZ.get()
    );

    public static final OreType TURQUOISE = new OreType(
            "turquoise",
            Set.of(
                    new OreFeature(
                            OreBase.STONE,
                            ModConfiguredFeatures.TURQUOISE_CONFIGURED, // near copper near water
                            ModPlacedFeature.create("stone_turquoise_ore"),
                            List.of(
                                    RarityFilter.onAverageOnceEvery(6),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(96)
                                    )
                            ),
                            ModBiomePlacements.createOrUpdate(
                                    "stone_turquoise_ore",
                                    Set.of(ModPlacedFeature.ALL.get("stone_turquoise_ore")),
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
                            )
                    )
            ),
            () -> ModItems.ROUGH_TURQUOISE.get()
    );

    public static final OreType NEPHRITE = new OreType(
            "nephrite",
            Set.of(
                    new OreFeature(
                            OreBase.STONE,
                            ModConfiguredFeatures.BASIC_GEM_CONFIGURED, 
                            ModPlacedFeature.create("stone_nephrite_ore"),
                            List.of(
                                    RarityFilter.onAverageOnceEvery(4),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(16)
                                    )
                            ),
                            ModBiomePlacements.createOrUpdate(
                                    "all_nephrite_ore",
                                    Set.of(ModPlacedFeature.ALL.get("stone_nephrite_ore")),
                                    new BiomeFilter.Tag(
                                            fromNamespaceAndPath("minecraft", "is_beach")
                                    ),
                                    GenerationStep.Decoration.UNDERGROUND_ORES
                            )
                    ),
                    new OreFeature(
                            OreBase.DEEPSLATE,
                            ModConfiguredFeatures.BASIC_GEM_CONFIGURED, 
                            ModPlacedFeature.create("deepslate_nephrite_ore"),
                            List.of(
                                    CountPlacement.of(5),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-32),
                                            VerticalAnchor.absolute(8)
                                    )
                            ),
                            ModBiomePlacements.addPlacedFeature(
                                    "all_nephrite_ore",
                                    ModPlacedFeature.ALL.get("deepslate_nephrite_ore")
                            )
                    )
            ),
            () -> ModItems.ROUGH_JADE.get()
    );

    public static final OreType JADEITE = new OreType(
            "jadeite",
            Set.of(
                    new OreFeature(
                            OreBase.STONE,
                            ModConfiguredFeatures.BASIC_GEM_CONFIGURED, 
                            ModPlacedFeature.create("stone_jadeite_ore"),
                            List.of(
                                    RarityFilter.onAverageOnceEvery(2),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(16)
                                    )
                            ),
                            ModBiomePlacements.createOrUpdate(
                                    "all_jadeite_ore",
                                    Set.of(ModPlacedFeature.ALL.get("stone_jadeite_ore")),
                                    new BiomeFilter.Tag(
                                            fromNamespaceAndPath("minecraft", "is_mountain")
                                    ),
                                    GenerationStep.Decoration.UNDERGROUND_ORES
                            )
                    ),
                    new OreFeature(
                            OreBase.DEEPSLATE,
                            ModConfiguredFeatures.BASIC_GEM_CONFIGURED,
                            ModPlacedFeature.create("deepslate_jadeite_ore"),
                            List.of(
                                    CountPlacement.of(2),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-32),
                                            VerticalAnchor.absolute(0)
                                    )
                            ),
                            ModBiomePlacements.addPlacedFeature(
                                    "all_jadeite_ore",
                                    ModPlacedFeature.ALL.get("deepslate_jadeite_ore")
                            )
                    )
            ),
            () -> ModItems.ROUGH_JADE.get()
    );

    // TODO: ADD MORE TYPES OF TERRACOTTA
    public static final OreType OPAL = new OreType(
            "opal",
            Set.of(
                    new OreFeature(
                            OreBase.TERRACOTTA,
                            ModConfiguredFeatures.BASIC_GEM_CONFIGURED,
                            ModPlacedFeature.create("terracotta_opal_ore"),
                            List.of(
                                    RarityFilter.onAverageOnceEvery(8),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(32),
                                            VerticalAnchor.absolute(96)
                                    )
                            ),
                            ModBiomePlacements.createOrUpdate(
                                    "terracotta_opal_ore",
                                    Set.of(ModPlacedFeature.ALL.get("terracotta_opal_ore")),
                                    new BiomeFilter.Tag(
                                            fromNamespaceAndPath("minecraft", "is_badlands")
                                    ),
                                    GenerationStep.Decoration.UNDERGROUND_ORES
                            )
                    )
            ),
            () -> ModItems.ROUGH_OPAL.get()
    );

    public static final OreType OLIVINE = new OreType(
            "olivine",
            Set.of(
                    new OreFeature(
                            OreBase.DEEPSLATE,
                            ModConfiguredFeatures.OLIVINE_CONFIGURED,  // only next to lava
                            ModPlacedFeature.create("deepslate_olivine_ore"),
                            List.of(
                                    CountPlacement.of(2),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-64),
                                            VerticalAnchor.absolute(-32)
                                    )
                            ),
                            ModBiomePlacements.addPlacedFeature(
                                    "basic_gem_ore",
                                    ModPlacedFeature.ALL.get("deepslate_olivine_ore")
                            )
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
