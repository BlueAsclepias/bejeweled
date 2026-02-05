package net.blueasclepias.bejeweled.feature.placedfeature;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.HashMap;
import java.util.Map;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

/**
 * Registers Placed Features for the mod.
 */
public class ModPlacedFeature {

    // ===== Lists and Maps =====
    public static final Map<String, ResourceKey<PlacedFeature>> ALL = new HashMap<>();

    public static final ResourceKey<PlacedFeature> CORAL_POLYP = create("coral_polyp");
    public static final ResourceKey<PlacedFeature> STONE_BERYL_ORE = create("stone_beryl_ore");
    public static final ResourceKey<PlacedFeature> GRANITE_BERYL_ORE = create("granite_beryl_ore");
    public static final ResourceKey<PlacedFeature> STONE_RED_CORUNDUM_ORE = create("stone_red_corundum_ore");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_RED_CORUNDUM_ORE = create("deepslate_red_corundum_ore");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_BLUE_CORUNDUM_ORE = create("deepslate_blue_corundum_ore");
    public static final ResourceKey<PlacedFeature> BASALT_BLUE_CORUNDUM_ORE = create("basalt_blue_corundum_ore");
    public static final ResourceKey<PlacedFeature> STONE_GARNET_ORE = create("stone_garnet_ore");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_GARNET_ORE = create("deepslate_garnet_ore");
    public static final ResourceKey<PlacedFeature> STONE_TOPAZ_ORE = create("stone_topaz_ore");
    public static final ResourceKey<PlacedFeature> GRANITE_TOPAZ_ORE = create("granite_topaz_ore");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_TOPAZ_ORE = create("deepslate_topaz_ore");
    public static final ResourceKey<PlacedFeature> STONE_TURQUOISE_ORE = create("stone_turquoise_ore");
    public static final ResourceKey<PlacedFeature> STONE_NEPHRITE_ORE = create("stone_nephrite_ore");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_NEPHRITE_ORE = create("deepslate_nephrite_ore");
    public static final ResourceKey<PlacedFeature> STONE_JADEITE_ORE = create("stone_jadeite_ore");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_JADEITE_ORE = create("deepslate_jadeite_ore");
    public static final ResourceKey<PlacedFeature> TERRACOTTA_OPAL_ORE = create("terracotta_opal_ore");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_OLIVINE_ORE = create("deepslate_olivine_ore");

    public static ResourceKey<PlacedFeature> create(String name){
        ResourceKey<PlacedFeature> resourceKey = ResourceKey.create(
                Registries.PLACED_FEATURE,
                fromNamespaceAndPath(MOD_ID, name));
        ALL.put(name, resourceKey);
        return resourceKey;
    }
}
