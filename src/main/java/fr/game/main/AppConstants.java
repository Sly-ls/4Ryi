package fr.game.main;

import java.text.DecimalFormat;

public class AppConstants {

    final public static String GAME_TITLE = "Chasse au Trésor";
    //GAME PARAMETER
    //How many pont in the entity creation panel
    static final public int NB_CREATION_POINTS = 3;


    //DEBUG SETTINGS
    public static final boolean DEBUG = true;
    public static final boolean DEBUG_UI = false;
    public static final boolean DEBUG_INVINCIBLE_MODE = false;
    public static final boolean DEBUG_WITH_DAYLIGHT = false;
    public static final boolean DEBUG_TILE_COLLISION = true;
    public static final boolean DEBUG_OBJECT_COLLISION = true;
    public static final boolean DEBUG_ACTION_COLLISION = true;
    public static final boolean DEBUG_KEYBOARD  = false;
    public static final boolean DEBUG_MOUSE = true;
    public static final boolean DEBUG_MENU = true;
    public static final boolean DEBUG_ACTION = true;
    public static final boolean DEBUG_SPEAK = false;
    public static final boolean DEBUG_MUSIC = false;
    public static final boolean DEBUG_SOUND = true;
    public static final boolean REBUILD_TILESET = false;

    //CONSTANTS
    public static final int SECOND_IN_MINUTES = 60;
    public static final int NANO_SECONDS_IN_1_SECOND = 1000000000;
    //FORMATTER
    public static DecimalFormat INTEGER_FORMAT = new DecimalFormat("#0");
    public static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.00");;

}
