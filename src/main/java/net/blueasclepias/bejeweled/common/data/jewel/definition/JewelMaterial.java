package net.blueasclepias.bejeweled.common.data.jewel.definition;

// TODO: modifiers
public enum JewelMaterial {
    COPPER("material.bejeweled.copper"),
    SILVER("material.bejeweled.silver"),
    BRONZE("material.bejeweled.bronze"),
    IRON("material.bejeweled.iron"),
    GOLD("material.bejeweled.gold"),
    STEEL("material.bejeweled.steel"),
    NETHERITE("material.bejeweled.netherite");

    public final String translationKey;

    JewelMaterial(String key) {
        this.translationKey = key;
    }
}
