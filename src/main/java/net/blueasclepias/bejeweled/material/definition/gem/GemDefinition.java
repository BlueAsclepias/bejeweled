package net.blueasclepias.bejeweled.material.definition.gem;

import net.blueasclepias.bejeweled.interfaces.IGemDefinition;
import net.blueasclepias.bejeweled.material.instance.gem.GemEffects;
import net.blueasclepias.bejeweled.material.instance.gem.GemPassives;
import net.blueasclepias.bejeweled.material.registry.ModGemRegistry;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public record GemDefinition(
        String id,
        int color,
        GemRarity rarity,
        GemCategory category,
        GemEffects effects, // TODO. null for now
        GemPassives passives // TODO. null for now
) implements IGemDefinition {
    public GemDefinition(String id,
                         int color,
                         GemRarity rarity,
                         GemCategory category,
                         GemEffects effects,
                         GemPassives passives){
        this.id = id;
        this.color = color;
        this.rarity = rarity;
        this.category = category;
        this.effects = effects;
        this.passives = passives;
        ModGemRegistry.registerDefinition(fromNamespaceAndPath(MOD_ID, id), this);
    }
}
