package net.blueasclepias.bejeweled.enums;

public enum Rarity {
    COMMON(8,1),
    UNCOMMON(4,2),
    RARE(2,3);

    public final int weight;
    public final int basePassiveUnlock;

    Rarity(int weight, int basePassiveUnlock) {
        this.weight = weight;
        this.basePassiveUnlock = basePassiveUnlock;
    }
}
