package net.blueasclepias.bejeweled.record;

import net.minecraft.world.item.Item;

import java.util.Set;
import java.util.function.Supplier;

public record OreType(
        String name,
        Set<OreBaseGen> bases,
        Supplier<Item> drop
) {}
