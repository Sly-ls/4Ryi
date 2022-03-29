package fr.game.constants.world.tiles;

public enum TilesDescriptionEnum_TEST_MAP implements TileSetInterface {
    GRASS(0,"/tiles/grass.png",false,true),
    WALL(1,"/tiles/wall.png",true,false),
    WATER(2,"/tiles/water.png",true,false),
    SAND(3,"/tiles/sand.png",false,false),
    TREE(4,"/tiles/tree.png",true,true),
    EARTH(5,"/tiles/earth.png",false,true),
    TREASURE(6,"/objects/treasure.png",true,false)
    ;

    private final boolean canPlantGrow;
    int code;
    String path;
    boolean collision;

    TilesDescriptionEnum_TEST_MAP(int code, String path, boolean collision, boolean canPlantGrow) {
        this.code = code;
        this.path = path;
        this.collision = collision;
        this.canPlantGrow = canPlantGrow;
    }

    public int getCode() {
        return code;
    }

    public String getPath() {
        return path;
    }

    public boolean isCollision() {
        return collision;
    }
}
