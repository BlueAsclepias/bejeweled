package net.blueasclepias.bejeweled.oredata;

import net.blueasclepias.bejeweled.enums.OreBase;
import net.blueasclepias.bejeweled.feature.biomeplacement.ModBiomePlacements;
import net.blueasclepias.bejeweled.record.OreFeature;
import net.blueasclepias.bejeweled.record.OreType;
import net.blueasclepias.bejeweled.registry.ModItems;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class OreTypes {

    public static final OreType BERYL = new OreType(
            "beryl",
            Set.of(
                    new OreFeature(
                            OreBase.STONE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(12),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(64)
                                    )
                            ),
                            ModBiomePlacements.BASIC_GEM_ORE
                            // only near granite in caves
                    ),
                    new OreFeature(
                            OreBase.GRANITE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(6),
                                    InSquarePlacement.spread(),
                                            HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-16),
                                            VerticalAnchor.absolute(64)
                                            )
                            ),
                            ModBiomePlacements.BASIC_GEM_ORE
                            // only in caves
                    )
            ),
            () -> ModItems.ROUGH_AQUAMARINE.get()
    );

    public static final OreType RED_CORUNDUM = new OreType(
            "red_corundum",
            Set.of(
                    new OreFeature(
                            OreBase.STONE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(8),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(32)
                                    )
                            ),
                            ModBiomePlacements.BASIC_GEM_ORE
                    ),
                    new OreFeature(
                            OreBase.DEEPSLATE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(4),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-48),
                                            VerticalAnchor.absolute(7)
                                    )
                            ),
                            ModBiomePlacements.BASIC_GEM_ORE
                    )
            ),
            () -> ModItems.ROUGH_RUBY.get()
    );

    public static final OreType BLUE_CORUNDUM = new OreType(
            "blue_corundum",
            Set.of(
                    new OreFeature(
                            OreBase.DEEPSLATE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(6),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-32),
                                            VerticalAnchor.absolute(7)
                                    )
                            ),
                            ModBiomePlacements.BASIC_GEM_ORE
                    ),
                    new OreFeature(
                            OreBase.BASALT,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(6),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(16),
                                            VerticalAnchor.absolute(128)
                                    )
                            ),
                            ModBiomePlacements.BASALT_BLUE_CORUNDUM
                    )
            ),
            () -> ModItems.ROUGH_SAPPHIRE.get()
    );

    public static final OreType GARNET = new OreType(
            "garnet",
            Set.of(
                    new OreFeature(
                            OreBase.STONE,
                            List.of(
                                    CountPlacement.of(8),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(96)
                                    )
                            ),
                            ModBiomePlacements.BASIC_GEM_ORE
                    ),
                    new OreFeature(
                            OreBase.DEEPSLATE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(4),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-48),
                                            VerticalAnchor.absolute(32)
                                    )
                            ),
                            ModBiomePlacements.BASIC_GEM_ORE
                    )
            ),
            () -> ModItems.ROUGH_GARNET.get()
    );

    public static final OreType TOPAZ = new OreType(
            "topaz",
            Set.of(
                    new OreFeature(
                            OreBase.STONE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(4),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(96)
                                    )
                            ),
                            ModBiomePlacements.BASIC_GEM_ORE
                    ),
                    new OreFeature(
                            OreBase.GRANITE,
                            List.of(
                                    CountPlacement.of(5),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(96)
                                    )
                            ),
                            ModBiomePlacements.BASIC_GEM_ORE
                    ),
                    new OreFeature(
                            OreBase.DEEPSLATE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(4),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(8)
                                    )
                            ),
                            ModBiomePlacements.BASIC_GEM_ORE
                    )
            ),
            () -> ModItems.ROUGH_TOPAZ.get()
    );

    public static final OreType TURQUOISE = new OreType(
            "turquoise",
            Set.of(
                    new OreFeature(
                            OreBase.STONE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(6),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(96)
                                    )
                            ),
                            ModBiomePlacements.TURQUOISE
                            // near copper near water
                    )
            ),
            () -> ModItems.ROUGH_TURQUOISE.get()
    );

    public static final OreType NEPHRITE = new OreType(
            "nephrite",
            Set.of(
                    new OreFeature(
                            OreBase.STONE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(4),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(16)
                                    )
                            ),
                            ModBiomePlacements.NEPHRITE
                    ),
                    new OreFeature(
                            OreBase.DEEPSLATE,
                            List.of(
                                    CountPlacement.of(5),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-32),
                                            VerticalAnchor.absolute(8)
                                    )
                            ),
                            ModBiomePlacements.NEPHRITE
                    )
            ),
            () -> ModItems.ROUGH_JADE.get()
    );

    public static final OreType JADEITE = new OreType(
            "jadeite",
            Set.of(
                    new OreFeature(
                            OreBase.STONE,
                            List.of(
                                    RarityFilter.onAverageOnceEvery(2),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(0),
                                            VerticalAnchor.absolute(16)
                                    )
                            ),
                            ModBiomePlacements.JADEITE
                    ),
                    new OreFeature(
                            OreBase.DEEPSLATE,
                            List.of(
                                    CountPlacement.of(2),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-32),
                                            VerticalAnchor.absolute(0)
                                    )
                            ),
                            ModBiomePlacements.JADEITE
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
                            List.of(
                                    RarityFilter.onAverageOnceEvery(8),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(32),
                                            VerticalAnchor.absolute(96)
                                    )
                            ),
                            ModBiomePlacements.OPAL
                    )
            ),
            () -> ModItems.ROUGH_OPAL.get()
    );

    public static final OreType OLIVINE = new OreType(
            "olivine",
            Set.of(
                    new OreFeature(
                            OreBase.DEEPSLATE,
                            List.of(
                                    CountPlacement.of(2),
                                    InSquarePlacement.spread(),
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-64),
                                            VerticalAnchor.absolute(-32)
                                    )
                            ),
                            ModBiomePlacements.BASIC_GEM_ORE
                            // only next to lava
                    )
            ),
            () -> ModItems.ROUGH_PERIDOT.get()
    );

    // Allow Addons to modify this so they don't need to replicate our systems
    public static List<OreType> ALL = new ArrayList<>();

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
        ALL.add(BERYL);
    }
}
