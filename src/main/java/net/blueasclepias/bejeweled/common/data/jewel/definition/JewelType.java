package net.blueasclepias.bejeweled.common.data.jewel.definition;

// TODO: Modifiers
public enum JewelType {
    RING("jeweltype.bejeweled.ring"),
    AMULET("jeweltype.bejeweled.amulet"),
    BRACELET("jeweltype.bejeweled.bracelet"),
    CIRCLET("jeweltype.bejeweled.circlet");

    public final String translationKey;

    JewelType(String key) {
        this.translationKey = key;
    }
}
