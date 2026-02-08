package net.blueasclepias.bejeweled.record;

import net.blueasclepias.bejeweled.enums.Rarity;

public record CoreType(
        String name,
        Rarity rarity,
        CoreEffects effects, // TODO. null for now
        CorePassives passives // TODO. null for now
){}
