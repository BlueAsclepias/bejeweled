package net.blueasclepias.bejeweled.data.definition.gem;

public enum GemCategory {
    GEMSTONE,
    BEAD;

    public static GemCategory fromString(String value) {
        return GemCategory.valueOf(value.toUpperCase());
    }
}
