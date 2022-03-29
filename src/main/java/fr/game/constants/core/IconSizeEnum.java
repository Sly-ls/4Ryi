package fr.game.constants.core;

public enum IconSizeEnum {
    XS(4),
    SMALL(8),
    MEDIUM(16),
    BIG(32),
    XL(64);

    int sizeInPixel;

    IconSizeEnum(int sizeInPixel) {
        this.sizeInPixel = sizeInPixel;
    }

    public int getSizeInPixel() {
        return sizeInPixel;
    }
}
