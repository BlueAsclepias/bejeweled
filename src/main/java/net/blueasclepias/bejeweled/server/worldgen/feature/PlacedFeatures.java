package net.blueasclepias.bejeweled.server.worldgen.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Registers Placed Features for the mod.
 */
public class PlacedFeatures {

    public static final ResourceKey<PlacedFeature> CORAL_POLYP = create("coral_polyp");

    public static ResourceKey<PlacedFeature> create(String name){
        return ResourceKey.create(
                Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
    }
}
