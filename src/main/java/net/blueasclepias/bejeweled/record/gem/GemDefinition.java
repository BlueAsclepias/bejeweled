package net.blueasclepias.bejeweled.record.gem;

import net.blueasclepias.bejeweled.enums.Rarity;

public record GemDefinition(
        String name,
        Rarity rarity,
        GemEffects effects, // TODO. null for now
        GemPassives passives // TODO. null for now
){}
