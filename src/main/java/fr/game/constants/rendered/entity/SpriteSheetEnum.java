package fr.game.constants.rendered.entity;


public enum SpriteSheetEnum {
    NEUTRAL(1,0,0,
            16,16,0,0,0,
            "a lot of NPC, 4 directionsn 2 Sprites walking animation; 70 lines, some lines should be deleted, inventory to do",
            "D://workspace/First-2D-game/src/main/ressources/spritesheet/",
            "/spritesheet/","perso/","SpriteSheet26.png")
//    ,ENEMY(1,0,0,
//                        36,64,
//        16,16,
//        0,
//                        "",
//                        "D://workspace/First-2D-game/src/main/ressources/spritesheet/",
//                        "/spritesheet/","enemy/","units.png")
            ;

    int code;
    int skipFirt;
    int skipLast;
    int width;
    int height;

    int marginX;
    int marginY;
    int padding;
    String description;
    String absoluteRootDirectory;
    String ressourcesRootDirectory;
    String namedDirectory;
    String originalTileset;

    SpriteSheetEnum(int code, int skipFirt, int skipLast, int tileSize, int ratioHeight, int marginX, int marginY, int padding, String description, String absoluteRootDirectory, String ressourcesRootDirectory, String namedDirectory, String originalTileset) {
        this.code = code;
        this.skipFirt = skipFirt;
        this.skipLast = skipLast;
        this.height = ratioHeight;
        this.width  = tileSize;
        this.marginX = marginX;
        this.marginY = marginY;
        this.padding = padding;
        this.description = description;
        this.absoluteRootDirectory = absoluteRootDirectory;
        this.ressourcesRootDirectory = ressourcesRootDirectory;
        this.namedDirectory = namedDirectory;
        this.originalTileset = originalTileset;
    }

    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public String getAbsoluteRootDirectory() {
        return absoluteRootDirectory;
    }
    public String getRessourcesRootDirectory() {
        return ressourcesRootDirectory;
    }
    public String getNamedDirectory() {
        return namedDirectory;
    }
    public String getOriginalTileset() {
        return originalTileset;
    }
    public int getSkipFirt() {
        return skipFirt;
    }
    public int getSkipLast() {
        return skipLast;
    }
    public int getWidth() {
        return width;
    }
    public int getPadding() {
        return padding;
    }
    public int getHeight() {
        return height;
    }

    public int getMarginX() {
        return marginX;
    }

    public int getMarginY() {
        return marginY;
    }
}
