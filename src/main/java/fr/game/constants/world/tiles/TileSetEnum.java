package fr.game.constants.world.tiles;


public enum TileSetEnum implements TileSetInterface {
    WINTER_WC2(2,0,0,
            32,0,1,
            "Winter tileset from Warcraft 2",
            "D://workspace/First-2D-game/src/main/ressources/tilesets/", "/tilesets/","wintertiles/","wintertiles.png"),

    WASTELAND_WC2(1,0,0,
            32,0,1,
            "Wasteland tileset from Warcraft 2",
            "D://workspace/First-2D-game/src/main/ressources/tilesets/", "/tilesets/","wastelandtiles/","wastelandtiles.png"),

    SUMMER_WC2(0,0,0,
            32,0,1,
            "Summer tileset from Warcraft 2",
                       "D://workspace/First-2D-game/src/main/ressources/tilesets/", "/tilesets/","summertiles/","summertiles.png")
;

    int code;
    int skipFirt;
    int skipLast;
    int tileSize;

    int margin;
    int padding;
    String description;
    String absoluteRootDirectory;
    String ressourcesRootDirectory;
    String namedDirectory;
    String originalTileset;

    TileSetEnum(int code, int skipFirt, int skipLast, int tileSize, int margin, int padding, String description, String absoluteRootDirectory, String ressourcesRootDirectory, String namedDirectory, String originalTileset) {
        this.code = code;
        this.skipFirt = skipFirt;
        this.skipLast = skipLast;
        this.tileSize = tileSize;
        this.margin = margin;
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

    public int getTileSize() {
        return tileSize;
    }

    public int getMargin() {
        return margin;
    }

    public int getPadding() {
        return padding;
    }
}
