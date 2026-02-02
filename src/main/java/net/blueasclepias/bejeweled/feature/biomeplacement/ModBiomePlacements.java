package net.blueasclepias.bejeweled.feature.biomeplacement;

import com.mojang.datafixers.util.Either;
import net.blueasclepias.bejeweled.datagen.ModWorldGen;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.List;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class ModBiomePlacements {

    public static final BiomeFeaturePlacement CORAL_POLYP =
            new BiomeFeaturePlacement(
                    ModWorldGen.CORAL_POLYP_PLACED,
                    Either.left(ResourceKey.create(
                            Registries.BIOME,
                            fromNamespaceAndPath("minecraft", "warm_ocean")
                    )),
                    GenerationStep.Decoration.VEGETAL_DECORATION
            );

    public static List<BiomeFeaturePlacement> all() {
        return List.of(CORAL_POLYP);
    }
}

