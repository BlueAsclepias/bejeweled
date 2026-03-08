package net.blueasclepias.bejeweled.common.api;

import net.blueasclepias.bejeweled.common.data.gem.definition.GemCategory;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemRarity;
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
