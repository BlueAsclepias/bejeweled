package net.blueasclepias.bejeweled.material.definition.jewel;

// TODO: Modifiers
public enum JewelType {
    RING("jeweltype.bejeweled.ring"),
    AMULET("jeweltype.bejeweled.amulet"),
    BRACELET("jeweltype.bejeweled.bracelet"),
    CIRCLET("jeweltype.bejeweled.circlet");

    private final String translationKey;

    JewelType(String key) {
        this.translationKey = key;
    }

    public String getTranslationKey() {
        return translationKey;
    }
}
