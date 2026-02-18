package net.blueasclepias.bejeweled.material.definition.gem;

import net.minecraft.util.RandomSource;

public enum GemGrade {
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

    GemGrade(String displayName, int passiveUnlockLevel, float hpThreshold, int minigameMoveLimit, int weight) {
        this.displayName = displayName;
        this.passiveUnlockLevel = passiveUnlockLevel;
        this.hpThreshold = hpThreshold;
        this.minigameMoveLimit = minigameMoveLimit;
        this.weight = weight;
    }

    public static GemGrade random(RandomSource random){
        int total = 0;
        for (GemGrade value : values()) total += value.weight;
        int roll = random.nextInt(total);

        for (GemGrade value : values()) {
            roll -= value.weight;
            if (roll < 0) return value;
        }

        return D;
    }
}
