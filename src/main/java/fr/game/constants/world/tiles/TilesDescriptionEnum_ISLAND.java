package fr.game.constants.world.tiles;

public enum TilesDescriptionEnum_ISLAND implements TileSetInterface {
    TREASURE(0,"/objects/treasure.png",true),
    WATER(1,"/tiles/water.png",true),
    SAND(2,"/tiles/sand.png",false),
    GRASS(3,"/tiles/grass.png",false),
    TREE(4,"/tiles/tree.png",true),
    EARTH(5,"/tiles/earth.png",false),
    WALL(6,"/tiles/wall.png",true)
            ;
    int code;
    String path;
    boolean collision;

    TilesDescriptionEnum_ISLAND(int code, String path, boolean collision) {
        this.code = code;
        this.path = path;
        this.collision = collision;
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
