package net.blueasclepias.bejeweled.common.data.ore.definition;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * Identity of a gemstone ore: a mineral id and the item it drops.
 */
public record OreDefinition(
        @NotNull String id,
        @NotNull ResourceLocation drop
) {
}
