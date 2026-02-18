package net.blueasclepias.bejeweled.material.instance.ore;

import net.blueasclepias.bejeweled.material.definition.ore.OreDefinition;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public final class OreDefinitions {

    public static final OreDefinition BERYL = new OreDefinition(
            "beryl",
            fromNamespaceAndPath(MOD_ID, "raw_aquamarine")
    );

    public static final OreDefinition RED_CORUNDUM = new OreDefinition(
            "red_corundum",
            fromNamespaceAndPath(MOD_ID, "raw_ruby")
    );

    public static final OreDefinition BLUE_CORUNDUM = new OreDefinition(
            "blue_corundum",
            fromNamespaceAndPath(MOD_ID, "raw_sapphire")
    );

    public static final OreDefinition GARNET = new OreDefinition(
            "garnet",
            fromNamespaceAndPath(MOD_ID, "raw_garnet")
    );

    public static final OreDefinition TOPAZ = new OreDefinition(
            "topaz",
            fromNamespaceAndPath(MOD_ID, "raw_topaz")
    );

    public static final OreDefinition TURQUOISE = new OreDefinition(
            "turquoise",
            fromNamespaceAndPath(MOD_ID, "raw_turquoise")
    );

    public static final OreDefinition NEPHRITE = new OreDefinition(
            "nephrite",
            fromNamespaceAndPath(MOD_ID, "raw_jade")
    );

    public static final OreDefinition JADEITE = new OreDefinition(
            "jadeite",
            fromNamespaceAndPath(MOD_ID, "raw_jade")
    );

    public static final OreDefinition OPAL = new OreDefinition(
            "opal",
            fromNamespaceAndPath(MOD_ID, "raw_opal")
    );

    public static final OreDefinition OLIVINE = new OreDefinition(
            "olivine",
            fromNamespaceAndPath(MOD_ID, "raw_peridot")
    );
}
