package net.blueasclepias.bejeweled.record;

import net.blueasclepias.bejeweled.enums.OreBase;
import net.blueasclepias.bejeweled.feature.biomeplacement.BiomeFeaturePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public record OreBaseGen(
        @NotNull OreBase base,
        @NotNull List<PlacementModifier> placementModifiers,
        @Nullable BiomeFeaturePlacement biomeFeature
) {}