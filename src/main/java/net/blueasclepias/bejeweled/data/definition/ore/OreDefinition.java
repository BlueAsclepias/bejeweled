package net.blueasclepias.bejeweled.data.definition.ore;

import net.blueasclepias.bejeweled.interfaces.IOreDefinition;
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
