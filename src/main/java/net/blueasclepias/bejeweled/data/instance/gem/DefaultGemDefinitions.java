package net.blueasclepias.bejeweled.data.instance.gem;

import net.blueasclepias.bejeweled.data.definition.gem.GemCategory;
import net.blueasclepias.bejeweled.data.definition.gem.GemDefinition;
import net.blueasclepias.bejeweled.data.definition.gem.GemRarity;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class DefaultGemDefinitions {

    private static final Map<ResourceLocation, GemDefinition> DEFINITIONS = new HashMap<>();

    private static final GemDefinition AQUAMARINE =
            register(new GemDefinition(
                    fromNamespaceAndPath(MOD_ID, "aquamarine"),
                    0x21B2D3,
                    true,
                    GemRarity.COMMON,
                    GemCategory.GEMSTONE,
                    null,
                    null
            ));


    private static final GemDefinition RUBY =
            register(new GemDefinition(
                    fromNamespaceAndPath(MOD_ID, "ruby"),
                    0xEA1243,
                    true,
                    GemRarity.RARE,
                    GemCategory.GEMSTONE,
                    null,
                    null
            ));

    private static final GemDefinition SAPPHIRE =
            register(new GemDefinition(
                    fromNamespaceAndPath(MOD_ID, "sapphire"),
                    0x1057E5,
                    true,
                    GemRarity.RARE,
                    GemCategory.GEMSTONE,
                    null,
                    null
            ));

    private static final GemDefinition GARNET =
            register(new GemDefinition(
                    fromNamespaceAndPath(MOD_ID, "garnet"),
                    0xA00603,
                    true,
                    GemRarity.COMMON,
                    GemCategory.GEMSTONE,
                    null,
                    null
            ));

    private static final GemDefinition AMETHYST =
            register(new GemDefinition(
                    fromNamespaceAndPath("minecraft", "amethyst"),
                    0xFF8DFD,
                    true,
                    GemRarity.COMMON,
                    GemCategory.GEMSTONE,
                    null,
                    null
            ));

    private static final GemDefinition TOPAZ =
            register(new GemDefinition(
                    fromNamespaceAndPath(MOD_ID, "topaz"),
                    0xF38241,
                    true,
                    GemRarity.COMMON,
                    GemCategory.GEMSTONE,
                    null,
                    null
            ));

    private static final GemDefinition EMERALD =
            register(new GemDefinition(
                    fromNamespaceAndPath("minecraft", "emerald"),
                    0x17DD62,
                    true,
                    GemRarity.UNCOMMON,
                    GemCategory.GEMSTONE,
                    null,
                    null
            ));

    private static final GemDefinition TURQUOISE =
            register(new GemDefinition(
                    fromNamespaceAndPath(MOD_ID, "turquoise"),
                    0x31D4A4,
                    true,
                    GemRarity.RARE,
                    GemCategory.GEMSTONE,
                    null,
                    null
            ));

    private static final GemDefinition JADE =
            register(new GemDefinition(
                    fromNamespaceAndPath(MOD_ID, "jade"),
                    0x95C585,
                    true,
                    GemRarity.UNCOMMON,
                    GemCategory.GEMSTONE,
                    null,
                    null
            ));

    private static final GemDefinition OPAL =
            register(new GemDefinition(
                    fromNamespaceAndPath(MOD_ID, "opal"),
                    0xDEFFF4,
                    true,
                    GemRarity.RARE,
                    GemCategory.GEMSTONE,
                    null,
                    null
            ));

    private static final GemDefinition PEARL =
            register(new GemDefinition(
                    fromNamespaceAndPath(MOD_ID, "pearl"),
                    0xE8E3BA,
                    true,
                    GemRarity.COMMON,
                    GemCategory.BEAD,
                    null,
                    null
            ));

    private static final GemDefinition BLUE_CORAL =
            register(new GemDefinition(
                    fromNamespaceAndPath(MOD_ID, "blue_coral"),
                    0x858DFF,
                    true,
                    GemRarity.COMMON,
                    GemCategory.BEAD,
                    null,
                    null
            ));

    private static final GemDefinition RED_CORAL =
            register(new GemDefinition(
                    fromNamespaceAndPath(MOD_ID, "red_coral"),
                    0xE35C5E,
                    true,
                    GemRarity.COMMON,
                    GemCategory.BEAD,
                    null,
                    null
            ));

    private static final GemDefinition YELLOW_CORAL =
            register(new GemDefinition(
                    fromNamespaceAndPath(MOD_ID, "yellow_coral"),
                    0x9A711F,
                    true,
                    GemRarity.COMMON,
                    GemCategory.BEAD,
                    null,
                    null
            ));

    private static final GemDefinition PINK_CORAL =
            register(new GemDefinition(
                    fromNamespaceAndPath(MOD_ID, "pink_coral"),
                    0xBC2FCC,
                    true,
                    GemRarity.COMMON,
                    GemCategory.BEAD,
                    null,
                    null
            ));

    private static final GemDefinition PURPLE_CORAL =
            register(new GemDefinition(
                    fromNamespaceAndPath(MOD_ID, "purple_coral"),
                    0x8E1AB9,
                    true,
                    GemRarity.COMMON,
                    GemCategory.BEAD,
                    null,
                    null
            ));

    private static final GemDefinition DIAMOND =
            register(new GemDefinition(
                    fromNamespaceAndPath("minecraft", "diamond"),
                    0x2CE0D8,
                    true,
                    GemRarity.RARE,
                    GemCategory.GEMSTONE,
                    null,
                    null
            ));

    private static final GemDefinition PERIDOT =
            register(new GemDefinition(
                    fromNamespaceAndPath(MOD_ID, "peridot"),
                    0xADFF0E,
                    true,
                    GemRarity.RARE,
                    GemCategory.GEMSTONE,
                    null,
                    null
            ));

    private static GemDefinition register(GemDefinition definition){
        DEFINITIONS.put(definition.id(), definition);
        return definition;
    }

    public static boolean containsKey(ResourceLocation key) {
        return DEFINITIONS.containsKey(key);
    }

    public static Map<ResourceLocation, GemDefinition> getAllByCategory(GemCategory category) {
        return DEFINITIONS.entrySet().stream()
                .filter(e -> e.getValue().category() == category)
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    public static Map<ResourceLocation, GemDefinition> getAll() {
        return DEFINITIONS;
    }
}
