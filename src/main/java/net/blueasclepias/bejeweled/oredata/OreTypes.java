package net.blueasclepias.bejeweled.oretypes;

import net.blueasclepias.bejeweled.enums.OreBase;
import net.blueasclepias.bejeweled.record.OreType;
import net.blueasclepias.bejeweled.registry.ModItems;

import java.util.Set;

public final class OreTypes {
    public static final OreType BERYL = new OreType(
            "beryl",
            Set.of(OreBase.STONE, OreBase.GRANITE),
            () -> ModItems.ROUGH_AQUAMARINE.get()
    );

    public static final OreType RED_CORUNDUM = new OreType(
            "red_corundum",
            Set.of(OreBase.STONE, OreBase.BASALT),
            () -> ModItems.ROUGH_RUBY.get()
    );

    public static final OreType BLUE_CORUNDUM = new OreType(
            "blue_corundum",
            Set.of(OreBase.DEEPSLATE, OreBase.BASALT),
            () -> ModItems.ROUGH_SAPPHIRE.get()
    );

    public static final OreType GARNET = new OreType(
            "garnet",
            Set.of(OreBase.STONE, OreBase.DEEPSLATE),
            () -> ModItems.ROUGH_GARNET.get()
    );

    public static final OreType TOPAZ = new OreType(
            "topaz",
            Set.of(OreBase.GRANITE, OreBase.STONE, OreBase.DEEPSLATE),
            () -> ModItems.ROUGH_TOPAZ.get()
    );

    public static final OreType TURQUOISE = new OreType(
            "turquoise",
            Set.of(OreBase.STONE),
            () -> ModItems.ROUGH_TURQUOISE.get()
    );

    public static final OreType NEPHRITE = new OreType(
            "nephrite",
            Set.of(OreBase.STONE, OreBase.DEEPSLATE),
            () -> ModItems.ROUGH_JADE.get()
    );

    public static final OreType JADEITE = new OreType(
            "jadeite",
            Set.of(OreBase.STONE, OreBase.DEEPSLATE),
            () -> ModItems.ROUGH_JADE.get()
    );

    public static final OreType OPAL = new OreType(
            "opal",
            Set.of(OreBase.TERRACOTTA),
            () -> ModItems.ROUGH_OPAL.get()
    );

    public static final OreType OLIVINE = new OreType(
            "olivine",
            Set.of(OreBase.DEEPSLATE),
            () -> ModItems.ROUGH_PERIDOT.get()
    );
}
