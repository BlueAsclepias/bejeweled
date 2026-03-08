package net.blueasclepias.bejeweled.common.data.gem.definition;

public enum GemCategory {
    GEMSTONE,
    BEAD;

    public static GemCategory fromString(String value) {
        return GemCategory.valueOf(value.toUpperCase());
    }
}
