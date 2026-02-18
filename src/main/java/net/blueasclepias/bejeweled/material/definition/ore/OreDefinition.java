package net.blueasclepias.bejeweled.material.definition.ore;

import net.blueasclepias.bejeweled.interfaces.IOreDefinition;
import net.blueasclepias.bejeweled.material.registry.ModOreRegistry;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public record OreDefinition(
        @NotNull String id,
        @NotNull ResourceLocation drop
) implements IOreDefinition {
    public OreDefinition(
            String id,
            ResourceLocation drop){
        this.id = id;
        this.drop = drop;
        ModOreRegistry.registerDefinition(fromNamespaceAndPath(MOD_ID, id), this);
    }
}
