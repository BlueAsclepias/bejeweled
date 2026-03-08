package net.blueasclepias.bejeweled.common.data.ore.defaults;

import net.blueasclepias.bejeweled.common.data.ore.definition.OreDefinition;
import net.minecraft.resources.ResourceLocation;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public final class OreDefinitions {

    public static final OreDefinition AQUAMARINE = new OreDefinition(
            "beryl",
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "aquamarine")
    );

    public static final OreDefinition RED_CORUNDUM = new OreDefinition(
            "red_corundum",
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "ruby")
    );

    public static final OreDefinition BLUE_CORUNDUM = new OreDefinition(
            "blue_corundum",
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "sapphire")
    );

    public static final OreDefinition GARNET = new OreDefinition(
            "garnet",
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "garnet")
    );

    public static final OreDefinition TOPAZ = new OreDefinition(
            "topaz",
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "topaz")
    );

    public static final OreDefinition TURQUOISE = new OreDefinition(
            "turquoise",
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "turquoise")
    );

    public static final OreDefinition NEPHRITE = new OreDefinition(
            "nephrite",
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "jade")
    );

    public static final OreDefinition JADEITE = new OreDefinition(
            "jadeite",
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "jade")
    );

    public static final OreDefinition OPAL = new OreDefinition(
            "opal",
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "opal")
    );

    public static final OreDefinition OLIVINE = new OreDefinition(
            "olivine",
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "peridot")
    );
}
