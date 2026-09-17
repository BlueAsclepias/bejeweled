package net.blueasclepias.bejeweled.common.data.ore.defaults;

import net.blueasclepias.bejeweled.common.data.ore.definition.OreEntry;
import net.blueasclepias.bejeweled.common.data.ore.definition.OrePlacement;
import net.blueasclepias.bejeweled.common.data.ore.registry.OreRegistry;
import net.blueasclepias.bejeweled.server.worldgen.feature.ModPlacedFeatures;
import net.blueasclepias.bejeweled.server.worldgen.placement.BiomePlacements;
import net.blueasclepias.bejeweled.server.worldgen.registry.CustomFeatures;
import net.blueasclepias.bejeweled.server.worldgen.util.IBiomeFilter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;

import java.util.List;
import java.util.Set;

/**
 * The mod's complete list of generated ore entries.
 * Each {@link OreEntry} is plain data; registering it into {@link OreRegistry} happens explicitly via
 * {@link #register(OreEntry)} below instead of as a hidden constructor side effect. {@link #ALL} lets consumers
 * (block/item registration, worldgen bootstrap, datagen) iterate every entry without needing one named field per
 * ore block.
 */
public final class OreEntries {

    public static final OreEntry STONE_BERYL = register(new OreEntry(
            "stone_beryl_ore",
            OreDefinitions.AQUAMARINE,
            OreVariants.STONE,
            2,
            List.of(
                    CountPlacement.of(6),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(0),
                            VerticalAnchor.absolute(64)
                    )
            ),
            BiomePlacements.update(BiomePlacements.BASIC_GEM_ORE, ModPlacedFeatures.create("stone_beryl_ore")),
            OrePlacement.custom(CustomFeatures.STONE_BERYL)
    ));

    public static final OreEntry GRANITE_BERYL = register(new OreEntry(
            "granite_beryl_ore",
            OreDefinitions.AQUAMARINE,
            OreVariants.GRANITE,
            4,
            List.of(
                    CountPlacement.of(10),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(-16),
                            VerticalAnchor.absolute(64)
                    )
            ),
            BiomePlacements.update(BiomePlacements.BASIC_GEM_ORE, ModPlacedFeatures.create("granite_beryl_ore")),
            OrePlacement.custom(CustomFeatures.GRANITE_BERYL)
    ));

    public static final OreEntry STONE_RED_CORUNDUM = register(new OreEntry(
            "stone_red_corundum_ore",
            OreDefinitions.RED_CORUNDUM,
            OreVariants.STONE,
            1,
            List.of(
                    CountPlacement.of(6),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(0),
                            VerticalAnchor.absolute(32)
                    )
            ),
            BiomePlacements.update(BiomePlacements.BASIC_GEM_ORE, ModPlacedFeatures.create("stone_red_corundum_ore")),
            OrePlacement.generic()
    ));

    public static final OreEntry DEEPSLATE_RED_CORUNDUM = register(new OreEntry(
            "deepslate_red_corundum_ore",
            OreDefinitions.RED_CORUNDUM,
            OreVariants.DEEPSLATE,
            1,
            List.of(
                    CountPlacement.of(12),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(-48),
                            VerticalAnchor.absolute(7)
                    )
            ),
            BiomePlacements.update(BiomePlacements.BASIC_GEM_ORE, ModPlacedFeatures.create("deepslate_red_corundum_ore")),
            OrePlacement.generic()
    ));

    public static final OreEntry DEEPSLATE_BLUE_CORUNDUM = register(new OreEntry(
            "deepslate_blue_corundum_ore",
            OreDefinitions.BLUE_CORUNDUM,
            OreVariants.DEEPSLATE,
            1,
            List.of(
                    CountPlacement.of(4),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(-32),
                            VerticalAnchor.absolute(7)
                    )
            ),
            BiomePlacements.update(BiomePlacements.BASIC_GEM_ORE, ModPlacedFeatures.create("deepslate_blue_corundum_ore")),
            OrePlacement.generic()
    ));

    public static final OreEntry BASALT_BLUE_CORUNDUM = register(new OreEntry(
            "basalt_blue_corundum_ore",
            OreDefinitions.BLUE_CORUNDUM,
            OreVariants.BASALT,
            1,
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
                    Set.of(ModPlacedFeatures.create("basalt_blue_corundum_ore")),
                    new IBiomeFilter.List(
                            Set.of(
                                    ResourceLocation.fromNamespaceAndPath("minecraft", "basalt_deltas")
                            )
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            ),
            OrePlacement.generic()
    ));

    public static final OreEntry STONE_GARNET = register(new OreEntry(
            "stone_garnet_ore",
            OreDefinitions.GARNET,
            OreVariants.STONE,
            3,
            List.of(
                    CountPlacement.of(10),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(0),
                            VerticalAnchor.absolute(96)
                    )
            ),
            BiomePlacements.update(BiomePlacements.BASIC_GEM_ORE, ModPlacedFeatures.create("stone_garnet_ore")),
            OrePlacement.generic()
    ));

    public static final OreEntry DEEPSLATE_GARNET = register(new OreEntry(
            "deepslate_garnet_ore",
            OreDefinitions.GARNET,
            OreVariants.DEEPSLATE,
            1,
            List.of(
                    CountPlacement.of(6),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(-48),
                            VerticalAnchor.absolute(32)
                    )
            ),
            BiomePlacements.update(BiomePlacements.BASIC_GEM_ORE, ModPlacedFeatures.create("deepslate_garnet_ore")),
            OrePlacement.generic()
    ));

    public static final OreEntry STONE_TOPAZ = register(new OreEntry(
            "stone_topaz_ore",
            OreDefinitions.TOPAZ,
            OreVariants.STONE,
            3,
            List.of(
                    CountPlacement.of(6),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(0),
                            VerticalAnchor.absolute(96)
                    )
            ),
            BiomePlacements.update(BiomePlacements.BASIC_GEM_ORE, ModPlacedFeatures.create("stone_topaz_ore")),
            OrePlacement.generic()
    ));

    public static final OreEntry GRANITE_TOPAZ = register(new OreEntry(
            "granite_topaz_ore",
            OreDefinitions.TOPAZ,
            OreVariants.GRANITE,
            6,
            List.of(
                    CountPlacement.of(12),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(0),
                            VerticalAnchor.absolute(96)
                    )
            ),
            BiomePlacements.update(BiomePlacements.BASIC_GEM_ORE, ModPlacedFeatures.create("granite_topaz_ore")),
            OrePlacement.generic()
    ));

    public static final OreEntry DEEPSLATE_TOPAZ = register(new OreEntry(
            "deepslate_topaz_ore",
            OreDefinitions.TOPAZ,
            OreVariants.DEEPSLATE,
            1,
            List.of(
                    CountPlacement.of(6),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(0),
                            VerticalAnchor.absolute(8)
                    )
            ),
            BiomePlacements.update(BiomePlacements.BASIC_GEM_ORE, ModPlacedFeatures.create("deepslate_topaz_ore")),
            OrePlacement.generic()
    ));

    public static final OreEntry STONE_TURQUOISE = register(new OreEntry(
            "stone_turquoise_ore",
            OreDefinitions.TURQUOISE,
            OreVariants.STONE,
            2,
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
                    Set.of(ModPlacedFeatures.create("stone_turquoise_ore")),
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
            OrePlacement.custom(CustomFeatures.TURQUOISE)
    ));

    public static final OreEntry STONE_NEPHRITE = register(new OreEntry(
            "stone_nephrite_ore",
            OreDefinitions.NEPHRITE,
            OreVariants.STONE,
            1,
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
                    Set.of(ModPlacedFeatures.create("stone_nephrite_ore")),
                    new IBiomeFilter.Tag(
                            ResourceLocation.fromNamespaceAndPath("minecraft", "is_beach")
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            ),
            OrePlacement.generic()
    ));

    public static final OreEntry DEEPSLATE_NEPHRITE = register(new OreEntry(
            "deepslate_nephrite_ore",
            OreDefinitions.NEPHRITE,
            OreVariants.DEEPSLATE,
            2,
            List.of(
                    CountPlacement.of(12),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(-32),
                            VerticalAnchor.absolute(8)
                    )
            ),
            BiomePlacements.update("all_nephrite_ore", ModPlacedFeatures.create("deepslate_nephrite_ore")),
            OrePlacement.generic()
    ));

    public static final OreEntry STONE_JADEITE = register(new OreEntry(
            "stone_jadeite_ore",
            OreDefinitions.JADEITE,
            OreVariants.STONE,
            1,
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
                    Set.of(ModPlacedFeatures.create("stone_jadeite_ore")),
                    new IBiomeFilter.Tag(
                            ResourceLocation.fromNamespaceAndPath("minecraft", "is_mountain")
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            ),
            OrePlacement.generic()
    ));

    public static final OreEntry DEEPSLATE_JADEITE = register(new OreEntry(
            "deepslate_jadeite_ore",
            OreDefinitions.JADEITE,
            OreVariants.DEEPSLATE,
            2,
            List.of(
                    CountPlacement.of(12),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(-32),
                            VerticalAnchor.absolute(0)
                    )
            ),
            BiomePlacements.update("all_jadeite_ore", ModPlacedFeatures.create("deepslate_jadeite_ore")),
            OrePlacement.generic()
    ));

    public static final OreEntry TERRACOTTA_OPAL = register(new OreEntry(
            "terracotta_opal_ore",
            OreDefinitions.OPAL,
            OreVariants.TERRACOTTA,
            2,
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
                    Set.of(ModPlacedFeatures.create("terracotta_opal_ore")),
                    new IBiomeFilter.Tag(
                            ResourceLocation.fromNamespaceAndPath("minecraft", "is_badlands")
                    ),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            ),
            OrePlacement.generic()
    ));

    public static final OreEntry DEEPSLATE_OLIVINE = register(new OreEntry(
            "deepslate_olivine_ore",
            OreDefinitions.OLIVINE,
            OreVariants.DEEPSLATE,
            1,
            List.of(
                    CountPlacement.of(8),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(
                            VerticalAnchor.absolute(-64),
                            VerticalAnchor.absolute(-32)
                    )
            ),
            BiomePlacements.update(BiomePlacements.BASIC_GEM_ORE, ModPlacedFeatures.create("deepslate_olivine_ore")),
            OrePlacement.custom(CustomFeatures.OLIVINE)
    ));

    /**
     * Every ore entry the mod defines, in declaration order. Block/item registration and worldgen bootstrap iterate
     * this list instead of needing a dedicated field per ore block.
     */
    public static final List<OreEntry> ALL = List.of(
            STONE_BERYL, GRANITE_BERYL,
            STONE_RED_CORUNDUM, DEEPSLATE_RED_CORUNDUM,
            DEEPSLATE_BLUE_CORUNDUM, BASALT_BLUE_CORUNDUM,
            STONE_GARNET, DEEPSLATE_GARNET,
            STONE_TOPAZ, GRANITE_TOPAZ, DEEPSLATE_TOPAZ,
            STONE_TURQUOISE,
            STONE_NEPHRITE, DEEPSLATE_NEPHRITE,
            STONE_JADEITE, DEEPSLATE_JADEITE,
            TERRACOTTA_OPAL,
            DEEPSLATE_OLIVINE
    );

    private static OreEntry register(OreEntry entry) {
        OreRegistry.register(entry);
        return entry;
    }
}
