package br.com.raoni.RunasDeMidgard.Enum;

public enum Rarity {

    COMMON ("common"),
    RARE ("rare"),
    EPIC ("epic"),
    LEGENDARY ("legendary"),
    MYSTIC ("mystic");

    private final String displayName;

    Rarity(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
