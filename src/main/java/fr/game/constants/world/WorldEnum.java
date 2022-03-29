package fr.game.constants.world;

import fr.game.constants.world.tiles.TileSetEnum;
import fr.game.constants.world.tiles.TileSetInterface;
import fr.game.constants.world.tiles.TilesDescriptionEnum_ISLAND;
import fr.game.constants.world.tiles.TilesDescriptionEnum_TEST_MAP;
import fr.game.main.utils.NumberUtils;

public enum WorldEnum {

    CUTE_MAP_BLUEBOY_TUTO("CUTE_MAP_BLUEBOY_TUTO", TilesDescriptionEnum_TEST_MAP.WATER,
            "/maps/50x50_map.txt", true,
            26,26,
            50,50,16, 3
            , NumberUtils.NumberLoopType.LOOP_PLUS ,16 ,12),
    TREASURE_HUNT("TREASURE_HUNT", TilesDescriptionEnum_TEST_MAP.WATER,
            "/maps/BigMap2.txt", false,
            23,22,
            50,100,16, 3
            , NumberUtils.NumberLoopType.LOOP_PLUS ,16 ,12),
    TEST_MAP("TEST_MAP", TilesDescriptionEnum_TEST_MAP.WATER,
            "/maps/BigMapTestingArea.txt", true,
            20,20,
            50,100,16, 3
            , NumberUtils.NumberLoopType.LOOP_PLUS ,16 ,12),
    THE_ISLAND("THE_ISLAND", TilesDescriptionEnum_ISLAND.WATER,
            "/maps/Labyrinth30012022.csv", false,
            21,97,
            50,100,16, 3
            , NumberUtils.NumberLoopType.LOOP_PLUS, 16 ,12),
    SPIDERLAND("SPIDERLAND", TileSetEnum.SUMMER_WC2,
            "/maps/CarteVDG.txt", false,
            50,50,
            100,100,32, 2
            , NumberUtils.NumberLoopType.LOOP_PLUS, 16 ,12),
    TEST_1("TEST_1", TilesDescriptionEnum_TEST_MAP.WATER,
            "/maps/BigMapTestingArea.txt", true,
            26,26,
            50,100,16, 3
            , NumberUtils.NumberLoopType.LOOP_PLUS ,16 ,12),
    TEST_2("TEST_2", TilesDescriptionEnum_TEST_MAP.WATER,
            "/maps/BigMapTestingArea.txt", true,
            26,26,
            50,100,16, 3
            , NumberUtils.NumberLoopType.LOOP_PLUS ,16 ,12),
    TEST_3("TEST_3", TilesDescriptionEnum_TEST_MAP.WATER,
            "/maps/BigMapTestingArea.txt", true,
            26,26,
            50,100,16, 3
            , NumberUtils.NumberLoopType.LOOP_PLUS ,16 ,12),
    TEST_4("TEST_4", TilesDescriptionEnum_TEST_MAP.WATER,
            "/maps/BigMapTestingArea.txt", true,
            26,26,
            50,100,16, 3
            , NumberUtils.NumberLoopType.LOOP_PLUS ,16 ,12),
    TEST_5("TEST_5", TilesDescriptionEnum_TEST_MAP.WATER,
            "/maps/BigMapTestingArea.txt", true,
            26,26,
            50,100,16, 3
            , NumberUtils.NumberLoopType.LOOP_PLUS ,16 ,12),
    TEST_6("TEST_6", TilesDescriptionEnum_TEST_MAP.WATER,
            "/maps/BigMapTestingArea.txt", true,
            26,26,
            50,100,16, 3
            , NumberUtils.NumberLoopType.LOOP_PLUS ,16 ,12),
    TEST_7("TEST_7", TilesDescriptionEnum_TEST_MAP.WATER,
            "/maps/BigMapTestingArea.txt", true,
            26,26,
            50,100,16, 3
            , NumberUtils.NumberLoopType.LOOP_PLUS ,16 ,12),
    TEST_8("TEST_8", TilesDescriptionEnum_TEST_MAP.WATER,
            "/maps/BigMapTestingArea.txt", true,
            26,26,
            50,100,16, 3
            , NumberUtils.NumberLoopType.LOOP_PLUS ,16 ,12),
    TEST_9("TEST_9", TilesDescriptionEnum_TEST_MAP.WATER,
            "/maps/BigMapTestingArea.txt", true,
            26,26,
            50,100,16, 3
            , NumberUtils.NumberLoopType.LOOP_PLUS ,16 ,12);



    final String title;
    final TileSetInterface tileSet;
    final String path;
    final boolean isForDebug;
    final int playerStartingX;
    final int playerStartingY;
    final int worldCol;
    final int worldRow;
    final int originialTileSize;
    final int scale;
    final NumberUtils.NumberLoopType goesAroundScreen;
    final int maxScreenColumn;
    final int maxScreenRow;

    //CONSTRUCTOR
    WorldEnum(String title, TileSetInterface tileSet, String path, boolean isForDebug,
              int playerStartingX, int playerStartingY,
              int worldCol, int worldRow, int originialTileSize, int scale,
              NumberUtils.NumberLoopType goesAroundScreen, int maxScreenColumn, int maxScreenRow) {
        this.title = title;
        this.tileSet = tileSet;
        this.isForDebug = isForDebug;
        this.path = path;
        this.playerStartingX = playerStartingX;
        this.playerStartingY = playerStartingY;
        this.worldRow = worldRow;
        this.worldCol = worldCol;
        this.scale = scale;
        this.originialTileSize = originialTileSize;
        this.goesAroundScreen = goesAroundScreen;
        this.maxScreenColumn = maxScreenColumn;
        this.maxScreenRow = maxScreenRow;
    }
    //GETTER && SETTER
    public String getTitle() {
        return title;
    }
    public TileSetInterface getTileSet() {
        return tileSet;
    }
    public String getPath() {
        return path;
    }
    public int getWorldCol() {
        return worldCol;
    }
    public int getWorldRow() {
        return worldRow;
    }
    public int getOriginialTileSize() {
        return originialTileSize;
    }
    public NumberUtils.NumberLoopType isGoesAroundScreen() {
        return goesAroundScreen;
    }
    public int getMaxScreenColumn() {
        return maxScreenColumn;
    }
    public int getMaxScreenRow() {
        return maxScreenRow;
    }
    public int getScale() {
        return scale;
    }
    public int getPlayerStartingX() {
        return playerStartingX;
    }
    public int getPlayerStartingY() {
        return playerStartingY;
    }
    public boolean isForDebug() {
        return isForDebug;
    }
}
