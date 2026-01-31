package net.blueasclepias.bejeweled.record;

import net.blueasclepias.bejeweled.enums.OreBase;
import net.minecraft.world.item.Item;

import java.util.Set;
import java.util.function.Supplier;

public record OreType(
        String name,
        Set<OreBase> bases,
        Supplier<Item> drop
) {}
