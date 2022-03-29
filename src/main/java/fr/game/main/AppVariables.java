package fr.game.main;

import fr.game.constants.world.WorldEnum;
import fr.game.main.utils.BufferedObjects;
import fr.game.main.utils.Graphics2DUtils;
import fr.game.main.utils.NumberUtils;

public class AppVariables {
    //do i use this.direction or any (birdEyedView) in the player class
    public static final boolean BIRD_EYED_PERSPECTIVE = false;
    //WORLD SETTING
    public static int maxWorldColumn = 50;
    public static int maxWorldRow = 50;
    //SCREEN SETTINGS
    public static int FPS = 60;
    public static NumberUtils.NumberLoopType goesAroundScreen = NumberUtils.NumberLoopType.LOOP_PLUS;
    public static int originalTileSize = 16;
    public static int scale = 3;
    public static int maxScreenColumn = 27;
    public static int maxScreenRow = 18;
    public static int tileSize = originalTileSize * scale;
    public static int SCREEN_WIDTH = tileSize * maxScreenColumn;
    public static int SCREEN_HEIGHT = tileSize * maxScreenRow;
    public static int maxWorldX = maxWorldColumn * tileSize;
    public static int maxWorldY = maxWorldRow * tileSize;
    //UI SETTINGS
    public static final int messageStayFor = FPS * 2;
    public static final int corpseStayFor = FPS * 2;
    public static final int flameWaveEvery = FPS/2;


    public static void test(WorldEnum worldSelected) {
        //WORLD SETTING
        maxWorldColumn = worldSelected.getWorldCol();
        maxWorldRow = worldSelected.getWorldRow();
        //SCREEN SETTINGS
        goesAroundScreen = worldSelected.isGoesAroundScreen();
        originalTileSize = worldSelected.getOriginialTileSize();
        scale = worldSelected.getScale();

        //FIXME not working, i can' move when i chane this; i think that de the solidArea
        // interact with nearby object, but i didn't verify it at the time
        maxScreenColumn = worldSelected.getMaxScreenColumn();
        maxScreenRow = worldSelected.getMaxScreenRow();

        refresh();
    }

    public static void resetTo(WorldEnum worldSelected) {
        //WORLD SETTING
        maxWorldColumn = worldSelected.getWorldCol();
        maxWorldRow = worldSelected.getWorldRow();
        //FIXME not working, i can' move when i chane this; i think that de the solidArea
        // interact with nearby object, but i didn't verify it at the time
        maxScreenColumn = worldSelected.getMaxScreenColumn();
        maxScreenRow = worldSelected.getMaxScreenRow();
        //SCREEN SETTINGS
        goesAroundScreen = worldSelected.isGoesAroundScreen();
        originalTileSize = worldSelected.getOriginialTileSize();
        refreshTest();
        BufferedObjects.getInstance().resetIconTotileSize();
    }

    public static void refreshTest() {
        tileSize = SCREEN_WIDTH / AppVariables.maxScreenColumn;
        scale = tileSize / AppVariables.originalTileSize;
    }

    public static void refresh() {
        tileSize = AppVariables.originalTileSize * AppVariables.scale;
        SCREEN_WIDTH = tileSize * AppVariables.maxScreenColumn;
        SCREEN_HEIGHT = tileSize * AppVariables.maxScreenRow;
        AppVariables.maxWorldX = AppVariables.maxWorldColumn * tileSize;
        maxWorldY = AppVariables.maxWorldRow * tileSize;
    }
}
