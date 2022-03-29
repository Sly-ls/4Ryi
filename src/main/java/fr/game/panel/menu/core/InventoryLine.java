package fr.game.panel.menu.core;

import fr.game.constants.InInventory;
import fr.game.constants.core.IconSizeEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.constants.game.GameValueEnum;
import fr.game.main.AppVariables;
import fr.game.main.utils.BufferedObjects;
import fr.game.main.utils.Graphics2DUtils;
import fr.game.mechanics.core.GameValue;
import fr.game.mechanics.core.Message;
import fr.game.mechanics.core.MessageBox;
import fr.game.mechanics.core.ScaledImage;
import javafx.util.Pair;

import java.awt.*;
import java.util.Map;

public class InventoryLine {

    boolean displayLabel = true;
    boolean displayIcon = true;
    boolean isIconDisplayed = false;
    IconSizeEnum iconSize = IconSizeEnum.MEDIUM;
    protected int maxValueLength = -1;
    GameValueEnum gameValueDescription;
    String label;
    String description;
    GameValue gameValue;
    int screenX;
    int screenY;
    int width;
    int height;

    //Construction
    public InventoryLine(GameValueEnum gameValueDescription, int screenX, int screenY, int width) {
        this.gameValueDescription=gameValueDescription;
        this.screenX=screenX;
        this.screenY =screenY;
        this.width=width;
        this.label = gameValueDescription.getName();
        //FIXME mettre une vrai description dans l'enum de ce que fait la carac
        this.description = gameValueDescription.getName();
        this.gameValue = new GameValue(gameValueDescription);
    }

    public InventoryLine(String label, String description, int screenX, int screenY, int width) {
        this.label = label;
        this.description = description;
        this.gameValue = new GameValue(null);
        this.screenX = screenX;
        this.screenY = screenY;
        this.width = width;
    }

    //GRAPHIC METHOD
    public void draw(Graphics2D graphics2D){
        drawIcon(graphics2D);
        drawLabel();
        Message valueMessage = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT, String.valueOf(this.gameValue.getValue()), screenX + width, screenY, false);
        if(!displayLabel){
            this.height = valueMessage.textBound.getValue();
        }
        //place here together the same font as value message
        initMaxLength(graphics2D);
        //placing the message from the right
        int valueScreenX = screenX + width - maxValueLength;
        valueMessage.screenCoordinate = new Pair<>(valueScreenX, screenY);
    }
    protected void drawLabel(){
        if( displayLabel) {
            int spaceAfterIcon = getSpaceAfterIcon();
            int labelX = screenX+spaceAfterIcon;
            Message labelMessage = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT, this.label, labelX, screenY, false) ;
            this.height = labelMessage.textBound.getValue();
        }
    }
    protected void drawIcon(Graphics2D graphics2D){
        isIconDisplayed=false;
        if(displayIcon && this.gameValueDescription != null) {
            int iconY = screenY;
            int iconX =screenX;
            if(!displayLabel){
                iconX += width/4;
            }

            iconY -= getIconDimensionOffset(false);
            if(BufferedObjects.getInstance().getIcon(this.gameValueDescription, iconSize) != null){
                graphics2D.drawImage(BufferedObjects.getInstance().getIcon(this.gameValueDescription, iconSize).getBufferedImage(), iconX, iconY, null);
                isIconDisplayed=true;
            }
        }
    }
    protected int getSpaceAfterIcon(){
        int spaceAfterIcon = 0;
        if(isIconDisplayed) {
            switch (iconSize){
                case XS:
                    spaceAfterIcon =  AppVariables.tileSize/4+AppVariables.tileSize/ 5;
                    break;
                case SMALL:
                    spaceAfterIcon =  AppVariables.tileSize/2+AppVariables.tileSize/ 5;
                    break;
                case MEDIUM:
                    spaceAfterIcon = AppVariables.tileSize+AppVariables.tileSize/5 ;
                    break;
                case BIG:
                    spaceAfterIcon = AppVariables.tileSize*2+AppVariables.tileSize/5 ;
                    break;
                case XL:
                    spaceAfterIcon =  AppVariables.tileSize*4+AppVariables.tileSize/ 5;
                    break;
                default:
                    break;
            }
        }
        return spaceAfterIcon;
    }
    protected int getIconDimensionOffset(boolean getX){
        int width = 0;
        int height = 0;
        switch (iconSize){
            case XS:
            case SMALL:
                width = AppVariables.tileSize / 4;
                height = AppVariables.tileSize / 2;
                break;
            case BIG:
            case MEDIUM:
                width = AppVariables.tileSize / 2;
                height = AppVariables.tileSize/2 + AppVariables.tileSize/4;
                break;
            case XL:
                width = AppVariables.tileSize + AppVariables.tileSize/2;
                height = AppVariables.tileSize;
                break;
            default:
                break;
        }
        if(getX) return width;
        else return height;
    }
    //SPECIAL GETTER
    private void initMaxLength(Graphics2D graphics2D){
        if(maxValueLength < 0){
            int maxValueToTest = 99;
            if(this.gameValue.getMaxValue() > 99){
                maxValueToTest = 999;
            }else if(this.gameValue.getMaxValue() > 999){
                maxValueToTest = 9999;
            }
            maxValueLength = (int) Math.max(maxValueLength, graphics2D.getFontMetrics().getStringBounds(String.valueOf(maxValueToTest), graphics2D).getWidth());
            maxValueLength += maxValueLength /4;
        }
    }

    //GETTER & SETTER
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public GameValue getGameValue() {
        return gameValue;
    }
    public void setGameValue(GameValue gameValue) {
        this.gameValue = gameValue;
    }
    public int getScreenX() {
        return screenX;
    }
    public int getScreenY() {
        return screenY;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }
    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public boolean isDisplayLabel() {
        return displayLabel;
    }
    public void setDisplayLabel(boolean displayLabel) {
        this.displayLabel = displayLabel;
    }
    public boolean isDisplayIcon() {
        return displayIcon;
    }
    public void setDisplayIcon(boolean displayIcon) {
        this.displayIcon = displayIcon;
    }
    public IconSizeEnum isSmallIcon() {
        return iconSize;
    }
    public void setSmallIcon(IconSizeEnum smallIcon) {
        this.iconSize = smallIcon;
    }
    public int getMaxValueLength() {
        return maxValueLength;
    }
    public void setMaxValueLength(int maxValueLength) {
        this.maxValueLength = maxValueLength;
    }
    public GameValueEnum getGameValueDescription() {
        return gameValueDescription;
    }
    public void setGameValueDescription(GameValueEnum gameValueDescription) {
        this.gameValueDescription = gameValueDescription;
    }
}
