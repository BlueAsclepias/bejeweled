package net.blueasclepias.bejeweled.server.worldgen.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Registers Placed Features for the mod.
 */
public class PlacedFeatures {

    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> CORAL_POLYP = create("coral_polyp");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> STONE_BERYL_ORE = create("stone_beryl_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> GRANITE_BERYL_ORE = create("granite_beryl_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> STONE_RED_CORUNDUM_ORE = create("stone_red_corundum_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> DEEPSLATE_RED_CORUNDUM_ORE = create("deepslate_red_corundum_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> DEEPSLATE_BLUE_CORUNDUM_ORE = create("deepslate_blue_corundum_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> BASALT_BLUE_CORUNDUM_ORE = create("basalt_blue_corundum_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> STONE_GARNET_ORE = create("stone_garnet_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> DEEPSLATE_GARNET_ORE = create("deepslate_garnet_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> STONE_TOPAZ_ORE = create("stone_topaz_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> GRANITE_TOPAZ_ORE = create("granite_topaz_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> DEEPSLATE_TOPAZ_ORE = create("deepslate_topaz_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> STONE_TURQUOISE_ORE = create("stone_turquoise_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> STONE_NEPHRITE_ORE = create("stone_nephrite_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> DEEPSLATE_NEPHRITE_ORE = create("deepslate_nephrite_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> STONE_JADEITE_ORE = create("stone_jadeite_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> DEEPSLATE_JADEITE_ORE = create("deepslate_jadeite_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> TERRACOTTA_OPAL_ORE = create("terracotta_opal_ore");
    public static final ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> DEEPSLATE_OLIVINE_ORE = create("deepslate_olivine_ore");

    public static ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> create(String name){
        return ResourceKey.create(
                Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
    }
}
