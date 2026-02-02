package net.blueasclepias.bejeweled.oredata;

import net.blueasclepias.bejeweled.enums.OreBase;
import net.blueasclepias.bejeweled.interfaces.BiomeFilter;
import net.blueasclepias.bejeweled.record.OreBaseGen;
import net.blueasclepias.bejeweled.record.OreGenSettings;
import net.blueasclepias.bejeweled.record.OreType;
import net.blueasclepias.bejeweled.registry.ModItems;

import java.util.List;
import java.util.Set;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public final class OreTypes {

    public static final OreType BERYL = new OreType(
            "beryl",
            Set.of(
                    new OreBaseGen(OreBase.STONE,
                            new OreGenSettings(
                                    1,
                                    4,
                                    -16,
                                    64,
                                    null)
                    ),
                    new OreBaseGen(OreBase.GRANITE,
                            new OreGenSettings(2,
                                    5,
                                    -16,
                                    64,
                                    null)
                    )
            ),
            () -> ModItems.ROUGH_AQUAMARINE.get()
    );

    public static final OreType RED_CORUNDUM = new OreType(
            "red_corundum",
            Set.of(
                    new OreBaseGen(OreBase.STONE,
                            new OreGenSettings(
                                    2,
                                    2,
                                    -32,
                                    32,
                                    null)
                    ),
                    new OreBaseGen(OreBase.BASALT,
                            new OreGenSettings(5,
                                    2,
                                    -48,
                                    32,
                                    null)
                    )
            ),
            () -> ModItems.ROUGH_RUBY.get()
    );

    public static final OreType BLUE_CORUNDUM = new OreType(
            "blue_corundum",
            Set.of(
                    new OreBaseGen(OreBase.DEEPSLATE,
                            new OreGenSettings(
                                    4,
                                    2,
                                    -32,
                                    32,
                                    null)
                    ),
                    new OreBaseGen(OreBase.BASALT,
                            new OreGenSettings(5,
                                    3,
                                    16,
                                    128,
                                    new BiomeFilter.Tag(
                                            fromNamespaceAndPath("minecraft", "basalt_deltas")
                                    )
                            )
                    )
            ),
            () -> ModItems.ROUGH_SAPPHIRE.get()
    );

    public static final OreType GARNET = new OreType(
            "garnet",
            Set.of(
                    new OreBaseGen(
                            OreBase.STONE,
                            new OreGenSettings(
                                    4,
                                    7,
                                    -16,
                                    96,
                                    null
                            )
                    ),
                    new OreBaseGen(
                            OreBase.DEEPSLATE,
                            new OreGenSettings(
                                    3,
                                    6,
                                    -48,
                                    32,
                                    null
                            )
                    )
            ),
            () -> ModItems.ROUGH_GARNET.get()
    );

    public static final OreType TOPAZ = new OreType(
            "topaz",
            Set.of(
                    new OreBaseGen(
                            OreBase.STONE,
                            new OreGenSettings(
                                    2,
                                    4,
                                    0,
                                    96,
                                    null
                            )
                    ),
                    new OreBaseGen(
                            OreBase.GRANITE,
                            new OreGenSettings(
                                    3,
                                    5,
                                    0,
                                    96,
                                    null
                            )
                    ),
                    new OreBaseGen(
                            OreBase.DEEPSLATE,
                            new OreGenSettings(
                                    4,
                                    1,
                                    -32,
                                    16,
                                    null
                            )
                    )
            ),
            () -> ModItems.ROUGH_TOPAZ.get()
    );

    public static final OreType TURQUOISE = new OreType(
            "turquoise",
            Set.of(
                    new OreBaseGen(
                            OreBase.STONE,
                            new OreGenSettings(
                                    4,
                                    5,
                                    48,
                                    128,
                                    new BiomeFilter.List(
                                            Set.of(
                                                    fromNamespaceAndPath("minecraft", "badlands"),
                                                    fromNamespaceAndPath("minecraft", "desert"),
                                                    fromNamespaceAndPath("minecraft", "savanna"),
                                                    fromNamespaceAndPath("minecraft", "eroded_badlands")
                                            )
                                    )
                            )
                    )
            ),
            () -> ModItems.ROUGH_TURQUOISE.get()
    );

    public static final OreType NEPHRITE = new OreType(
            "nephrite",
            Set.of(
                    new OreBaseGen(
                            OreBase.STONE,
                            new OreGenSettings(
                                    1,
                                    1,
                                    -48,
                                    96,
                                    new BiomeFilter.Tag(
                                            fromNamespaceAndPath("minecraft","is_mountain")
                                    )
                            )
                    ),
                    new OreBaseGen(
                            OreBase.DEEPSLATE,
                            new OreGenSettings(
                                    5,
                                    3,
                                    -48,
                                    -16,
                                    new BiomeFilter.Tag(fromNamespaceAndPath("minecraft", "is_beach"))
                            )
                    )
            ),
            () -> ModItems.ROUGH_JADE.get()
    );

    public static final OreType JADEITE = new OreType(
            "jadeite",
            Set.of(
                    new OreBaseGen(
                            OreBase.STONE,
                            new OreGenSettings(
                                    2,
                                    2,
                                    -64,
                                    -16,
                                    new BiomeFilter.Tag(
                                            fromNamespaceAndPath("minecraft","is_mountain")
                                    )
                            )
                    ),
                    new OreBaseGen(
                            OreBase.DEEPSLATE,
                            new OreGenSettings(
                                    4,
                                    1,
                                    -64,
                                    -16,
                                    new BiomeFilter.Tag(
                                            fromNamespaceAndPath("minecraft","is_mountain")
                                    )
                            )
                    )
            ),
            () -> ModItems.ROUGH_JADE.get()
    );

    // TODO: ADD MORE TYPES OF TERRACOTTA
    public static final OreType OPAL = new OreType(
            "opal",
            Set.of(
                    new OreBaseGen(
                            OreBase.TERRACOTTA,
                            new OreGenSettings(
                                    2,
                                    3,
                                    32,
                                    96,
                                    new BiomeFilter.Tag(
                                            fromNamespaceAndPath("minecraft", "is_badlands")
                                    )
                            )
                    )
            ),
            () -> ModItems.ROUGH_OPAL.get()
    );

    public static final OreType OLIVINE = new OreType(
            "olivine",
            Set.of(
                    new OreBaseGen(
                            OreBase.DEEPSLATE,
                            new OreGenSettings(
                                    2,
                                    3,
                                    -64,
                                    -32,
                                    null
                            )
                    )
            ),
            () -> ModItems.ROUGH_PERIDOT.get()
    );

    public static final List<OreType> ALL = List.of(
            BERYL,
            RED_CORUNDUM,
            BLUE_CORUNDUM,
            GARNET,
            TOPAZ,
            TURQUOISE,
            NEPHRITE,
            JADEITE,
            OPAL,
            OLIVINE
    );
}
