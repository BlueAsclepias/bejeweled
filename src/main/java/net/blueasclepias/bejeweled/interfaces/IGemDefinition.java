package net.blueasclepias.bejeweled.interfaces;

import net.blueasclepias.bejeweled.data.definition.gem.GemCategory;
import net.blueasclepias.bejeweled.data.definition.gem.GemRarity;
import net.minecraft.resources.ResourceLocation;

public interface IGemDefinition {
    ResourceLocation id();
    int color();
    boolean generateLoot();
    GemRarity rarity();
    GemCategory category();
    //GemEffects effects();
    //GemPassives passives();
}
