package net.blueasclepias.bejeweled.common.item;

import net.blueasclepias.bejeweled.common.data.jewel.definition.JewelMaterial;
import net.blueasclepias.bejeweled.common.data.jewel.definition.JewelType;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class BaseJewelItem extends Item implements ICurioItem {
    private final JewelType type;
    private final JewelMaterial material;

    public BaseJewelItem(Properties props,
                         JewelType type,
                         JewelMaterial material) {
        super(props.stacksTo(1));
        this.type = type;
        this.material = material;
    }

    public JewelType getType() {
        return type;
    }

    public JewelMaterial getMaterial() {
        return material;
    }
}
