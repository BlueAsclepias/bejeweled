package net.blueasclepias.bejeweled.data.definition.gem;

import net.minecraft.ChatFormatting;
import net.minecraft.util.RandomSource;

public enum GemGrade {
    S("grade.bejeweled.superb", ChatFormatting.GOLD, 5, .5f, 5, 1),
    A("grade.bejeweled.adequate", ChatFormatting.LIGHT_PURPLE, 4, .35f, 5, 2),
    B("grade.bejeweled.basic", ChatFormatting.BLUE, 3, .20f, 10, 4),
    C("grade.bejeweled.crummy", ChatFormatting.GREEN, 2, .10f, 15, 8),
    D("grade.bejeweled.defective", ChatFormatting.GRAY, 1, 0f, 20, 16);

    public final String translationKey;
    public final int passiveUnlockLevel;
    public final float hpThreshold;
    public final int minigameMoveLimit;
    public final int weight;
    public final ChatFormatting color;

    GemGrade(String translationKey, ChatFormatting color, int passiveUnlockLevel, float hpThreshold, int minigameMoveLimit, int weight) {
        this.translationKey = translationKey;
        this.passiveUnlockLevel = passiveUnlockLevel;
        this.hpThreshold = hpThreshold;
        this.minigameMoveLimit = minigameMoveLimit;
        this.weight = weight;
        this.color = color;
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
