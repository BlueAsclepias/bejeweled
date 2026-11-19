package net.blueasclepias.bejeweled.common.data.coral.definition;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

/**
 * A single generated coral polyp block: the id it is registered under, the item it drops, and the vanilla coral
 * block it must be attached to in order to survive.
 * Instances are pure data. Registering an entry into
 * {@link net.blueasclepias.bejeweled.common.data.coral.registry.CoralPolypRegistry} happens when the actual
 * {@link net.blueasclepias.bejeweled.common.block.CoralPolypBlock} is created, not as a constructor side effect.
 */
public record CoralPolypEntry(
        @NotNull String id,
        @NotNull ResourceLocation drop,
        @NotNull Block coralBlock
) {
}
