package net.blueasclepias.bejeweled.record;

import net.blueasclepias.bejeweled.enums.Grade;
import net.minecraft.world.item.Item;

public record JewelCore(
        Item coreItem,
        CoreType type,
        Grade grade
){}
