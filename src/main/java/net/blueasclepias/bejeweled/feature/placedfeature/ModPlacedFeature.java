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

    static {
        create("coral_polyp");
    }

    public static ResourceKey<PlacedFeature> create(String name){
        ResourceKey<PlacedFeature> resourceKey = ResourceKey.create(
                Registries.PLACED_FEATURE,
                fromNamespaceAndPath(MOD_ID, name));
        ALL.put(name, resourceKey);
        return resourceKey;
    }

}
