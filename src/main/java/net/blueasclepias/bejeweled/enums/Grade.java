package net.blueasclepias.bejeweled.enums;

public enum Grade {
    S("Superb", 5, .5f, 5, 1),
    A("Adequate", 4, .35f, 5, 2),
    B("Basic", 3, .20f, 10, 4),
    C("Crummy", 2, .10f, 15, 8),
    D("Defective", 1, 0f, 20, 16);

    public final String displayName;
    public final int passiveUnlockLevel;
    public final float hpThreshold;
    public final int minigameMoveLimit;
    public final int weight;

    Grade(String displayName, int passiveUnlockLevel, float hpThreshold, int minigameMoveLimit, int weight) {
        this.displayName = displayName;
        this.passiveUnlockLevel = passiveUnlockLevel;
        this.hpThreshold = hpThreshold;
        this.minigameMoveLimit = minigameMoveLimit;
        this.weight = weight;
    }
}
