package net.blueasclepias.bejeweled.common.data.storage.definition;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

/**
 * A single generated storage block: the gem/material it compresses ({@link #ingredient()}) and the map color used
 * for its default {@link net.minecraft.world.level.block.state.BlockBehaviour.Properties}.
 * Instances are pure data. Registering an entry into
 * {@link net.blueasclepias.bejeweled.common.data.storage.registry.StorageBlockRegistry} is an explicit step
 * performed by whoever assembles the mod's storage block list, not a constructor side effect.
 */
public record StorageBlockEntry(
        @NotNull String id,
        @NotNull ResourceLocation ingredient,
        @NotNull MapColor color
) {
}
