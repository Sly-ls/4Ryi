package fr.game.mechanics.controller;

import fr.game.main.AppVariables;
import fr.game.constants.core.GameUnitEnum;
import fr.game.mechanics.CameraControl;
import fr.game.rendererd.AbstractRendered;
import fr.game.rendererd.entity.PC.Player;

public class Camera implements CameraControl {
    private int worldX, worldY = 0;
    private int screenX, screenY = 0;
    static Camera instance;


    public static Camera getInstance() {
        if(instance==null){
            instance = new Camera();
        }
        return instance;
    }

    private Camera() {
    }

    @Override
    public boolean isDisplayable(AbstractRendered toCheckForVisibility){

        return isDisplayable(toCheckForVisibility.getWorldX(), toCheckForVisibility.getWorldY());
    }

    @Override
    public boolean isDisplayable(int worldX, int worldY){

        boolean inScreen = false;
        if(getScreenX(worldX) + AppVariables.tileSize> 0
                && getScreenX(worldX) < AppVariables.SCREEN_WIDTH
                && getScreenY(worldY)  + AppVariables.tileSize > 0
                && getScreenY(worldY) < AppVariables.SCREEN_HEIGHT ){
            inScreen = true;
        }

        return inScreen;
    }
    @Override
    public void centerCameraOnPlayer(Player player){
        //TODO integrate the offset part here
        this.screenX = 0;
        this.screenY = 0;
        this.worldX = player.getWorldX() - AppVariables.SCREEN_WIDTH /2 - (AppVariables.tileSize/2);
        if(this.worldX > AppVariables.maxWorldX - AppVariables.SCREEN_WIDTH){
            this.worldX = AppVariables.maxWorldX - AppVariables.SCREEN_WIDTH;
        }
        if(this.worldX<0){
            this.worldX = 0;
        }
        this.worldY = player.getWorldY() - AppVariables.SCREEN_HEIGHT /2  - (AppVariables.tileSize/2);
        if(this.worldY> AppVariables.maxWorldY - AppVariables.SCREEN_HEIGHT ){
            this.worldY = AppVariables.maxWorldY - AppVariables.SCREEN_HEIGHT;
        }
        if(this.worldY < 0){
            this.worldY = 0;
        }
    }


    @Override
    public int getScreenY(int worldY, GameUnitEnum unit) {
        switch (unit){
            case TILE:
                return getScreenY(worldY)/ AppVariables.tileSize;
            case TRUE:
            default:
                return getScreenY(worldY);

        }
    }
    @Override
    public int getScreenX(int worldX, GameUnitEnum unit) {
        switch (unit){
            case TILE:
                return getScreenX(worldX)/ AppVariables.tileSize;
            case TRUE:
            default:
                return getScreenX(worldX);

        }
    }
    @Override
    public int getScreenY(int worldY) {
        return worldY - this.worldY + this.screenY;
    }
    @Override
    public int getScreenXTileUnit(int worldX) {
        return getScreenX(worldX)/ AppVariables.tileSize;
    }

    @Override
    public int getScreenYTileUnit(int worldY) {
        return getScreenX(worldX)/ AppVariables.tileSize;
    }

    public int getScreenX(int worldX) {
        return worldX - this.worldX + this.screenX;
        //to draw from bottom right
//        return (this.screenX + GameConstants.SCREEN_WIDTH - worldX);
    }

    @Override
    public int getCameraMaxWorldX(GameUnitEnum unit) {
        switch (unit){
            case TILE:
                return worldX+AppVariables.SCREEN_WIDTH/ AppVariables.tileSize;
            case TRUE:
            default:
                return worldX;

        }
    }

    @Override
    public int getCameraMaxWorlY(GameUnitEnum unit) {
        switch (unit){
            case TILE:
                return worldY+AppVariables.SCREEN_HEIGHT/ AppVariables.tileSize;
            case TRUE:
            default:
                return worldY;

        }
    }


    @Override
    public int getCameraWorldX(GameUnitEnum unit) {
        switch (unit){
            case TILE:
                return worldX/ AppVariables.tileSize;
            case TRUE:
            default:
                return worldX;

        }
    }

    @Override
    public int getCameraWorldY(GameUnitEnum unit) {
        switch (unit){
            case TILE:
                return worldY/ AppVariables.tileSize;
            case TRUE:
            default:
                return worldY;

        }
    }

}
