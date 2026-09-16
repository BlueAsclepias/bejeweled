package net.blueasclepias.bejeweled.common.api;

import net.minecraft.resources.ResourceLocation;

/**
 * Common contract for ore definitions.
 */
public interface IOreDefinition {
    String id();
    ResourceLocation drop();
}
