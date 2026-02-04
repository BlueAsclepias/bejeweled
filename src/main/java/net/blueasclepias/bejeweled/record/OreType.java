package net.blueasclepias.bejeweled.record;

import net.minecraft.world.item.Item;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.Set;
import java.util.function.Supplier;

public record OreType(
        @NotBlank String name,
        Set<OreFeature> features,
        Supplier<Item> drop
) {}
