package net.blueasclepias.bejeweled.record.gem;

import net.blueasclepias.bejeweled.enums.Grade;
import net.minecraft.world.item.Item;

public record Gem(
        Item gem,
        GemDefinition definition,
        Grade grade
){}
