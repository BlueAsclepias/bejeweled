package net.blueasclepias.bejeweled.registry;

import net.blueasclepias.bejeweled.feature.CoralPolypFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CORAL_POLYP =
            FEATURES.register("coral_polyp",
                    CoralPolypFeature::new);

    public static void register(IEventBus modEventBus){
        FEATURES.register(modEventBus);
    }
}
