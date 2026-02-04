package net.blueasclepias.bejeweled.registry;

import net.blueasclepias.bejeweled.feature.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Register Mod World Gen Features
 */
public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CORAL_POLYP =
            FEATURES.register("coral_polyp",
                    CoralPolypFeature::new);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> BASIC_GEM_ORE =
            FEATURES.register("basic_gem_ore",
                    BasicGemOreFeature::new);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> STONE_BERYL =
            FEATURES.register("stone_beryl",
                    StoneBerylOreFeature::new);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> GRANITE_BERYL =
            FEATURES.register("granite_beryl",
                    GraniteBerylOreFeature::new);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> TURQUOISE =
            FEATURES.register("turquoise",
                    TurquoiseOreFeature::new);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> OLIVINE_CONFIGURED =
            FEATURES.register("olivine",
                    OlivineOreFeature::new);

    public static void register(IEventBus modEventBus){
        FEATURES.register(modEventBus);
    }
}
