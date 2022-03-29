package fr.game.panel.menu.core;

import fr.game.main.AppConstants;
import fr.game.constants.core.FontEnum;
import fr.game.main.utils.Graphics2DUtils;
import fr.game.mechanics.core.GameValue;
import fr.game.mechanics.core.Message;
import fr.game.panel.menu.MenuController;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;


public class MenuSlot {
    Set<MenuSlotActionEnum> slotActions = new HashSet<>();
    int x,y,width,height;
    int menuX, menuY;
    Message toDisplay;
    String parameters[];
    GameValue gameValue;

    //CONSTRUCTOR

    public MenuSlot(String parameters, Message message) {
        //FIXME have to do better
        //parameter in a map ?
        // 0=PANEL, 1=world
        if(parameters != null && !parameters.equalsIgnoreCase("")){
            this.parameters = parameters.split(";");
        }
        this.x = message.screenCoordinate.getKey();
        this.y = message.screenCoordinate.getValue();
        this.width = message.textBound.getKey();
        this.height = message.textBound.getValue();
        this.toDisplay = message;
    }

    //CLASS METHOD
    public void update(){
        this.x = toDisplay.screenCoordinate.getKey();
        this.y = toDisplay.screenCoordinate.getValue();
        this.width = toDisplay.textBound.getKey();
        this.height = toDisplay.textBound.getValue();
    }
    public boolean addAction(MenuSlotActionEnum action){
        return slotActions.add(action);
    }
    public boolean isMouseCursorWithin(int mouseX, int mouseY){
        if( mouseX >= x
                && mouseX <= x+width
                && mouseY >= y
                && mouseY <= y+height){
            return true;
        }
        return false;
    }

    //DEBUG METHOD
    public void drawDebug(Graphics2D graphics2D){
        if(AppConstants.DEBUG && (AppConstants.DEBUG_MOUSE || AppConstants.DEBUG_MENU)) {
            Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(mouseLocation, MenuController.getInstance().getPanel().getApplicationManager().getWindow());
            StringBuffer sbDebug = new StringBuffer();
            if (AppConstants.DEBUG_MOUSE){
                sbDebug.append(this.getX());
                sbDebug.append(",").append(this.getY());
                sbDebug.append("|").append(this.getWidth());
                sbDebug.append(",").append(this.getHeight());
            }
            if (AppConstants.DEBUG_MENU){
                sbDebug.append("|").append(this.getMenuX());
                sbDebug.append(",").append(this.getMenuY());
            }

            if (AppConstants.DEBUG_MOUSE){
                Color colorForZone;
                if(this.isMouseCursorWithin((int) mouseLocation.getX(),(int) mouseLocation.getY())){
                    Graphics2DUtils.setUIFont(graphics2D, FontEnum.FRANCISB,20,Color.GREEN);
                    colorForZone = new Color(17, 255, 0, 10);

                }else{
                    Graphics2DUtils.setUIFont(graphics2D, FontEnum.FRANCISB,20,Color.RED);
                    colorForZone = new Color(252, 23, 0, 10);
                }
                graphics2D.setColor(colorForZone);
                graphics2D.fillRect(this.getX(),this.getY()-this.toDisplay.textBound.getValue(),this.getWidth(),this.getHeight());

            }
            if (AppConstants.DEBUG_MENU){
                if(this.isMouseCursorWithin((int) mouseLocation.getX(),(int) mouseLocation.getY())){
                    graphics2D.setColor(Color.GREEN);
                }else{
                    graphics2D.setColor(Color.RED);
                }
                int x = this.getX();
                int y = this.getY()+this.getHeight()/2;
                graphics2D.drawString(sbDebug.toString(),x,y);
            }
        }
    }

    //GETTER && SETTER
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getMenuX() {
        return menuX;
    }
    public void setMenuX(int menuX) {
        this.menuX = menuX;
    }
    public int getMenuY() {
        return menuY;
    }
    public void setMenuY(int menuY) {
        this.menuY = menuY;
    }
    public Message getToDisplay() {
        return toDisplay;
    }
    public String[] getParameters() {
        return parameters;
    }
    public Set<MenuSlotActionEnum> getSlotActions() {
        return slotActions;
    }
    public void setSlotActions(Set<MenuSlotActionEnum> slotActions) {
        this.slotActions = slotActions;
    }
    public GameValue getGameValue() {
        return gameValue;
    }
    public void setGameValue(GameValue gameValue) {
        this.gameValue = gameValue;
    }

}
