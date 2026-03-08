package net.blueasclepias.bejeweled.common.data.gem.definition;

public enum GemRarity {
    COMMON(8,0),
    UNCOMMON(4,1),
    RARE(2,2),
    EPIC(1,3);

    public final int weight;
    public final int basePassiveUnlock;

    GemRarity(int weight, int basePassiveUnlock) {
        this.weight = weight;
        this.basePassiveUnlock = basePassiveUnlock;
    }

    public static GemRarity fromString(String value) {
        return GemRarity.valueOf(value.toUpperCase());
    }
}
