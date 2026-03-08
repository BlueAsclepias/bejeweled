package net.blueasclepias.bejeweled.common.data.ore.definition;

import net.blueasclepias.bejeweled.common.api.IOreDefinition;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record OreDefinition(
        @NotNull String id,
        @NotNull ResourceLocation drop
) implements IOreDefinition {
    public OreDefinition(
            String id,
            ResourceLocation drop){
        this.id = id;
        this.drop = drop;
    }
}
