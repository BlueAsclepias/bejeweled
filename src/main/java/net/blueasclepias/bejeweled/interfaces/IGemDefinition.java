package net.blueasclepias.bejeweled.interfaces;

import net.blueasclepias.bejeweled.material.definition.gem.GemCategory;
import net.blueasclepias.bejeweled.material.definition.gem.GemRarity;
import net.blueasclepias.bejeweled.material.instance.gem.GemEffects;
import net.blueasclepias.bejeweled.material.instance.gem.GemPassives;

public interface IGemDefinition {
    String id();
    GemRarity rarity();
    GemCategory category();
    GemEffects effects();
    GemPassives passives();
}
