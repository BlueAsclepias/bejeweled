package net.blueasclepias.bejeweled.item;

import net.blueasclepias.bejeweled.material.definition.gem.GemDefinition;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractGemItem<D extends GemDefinition> extends Item {

    protected final D definition;

    protected AbstractGemItem(D definition, Properties props) {
        super(props);
        this.definition = definition;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 64;
    }

    public D definition() {
        return definition;
    }
}
