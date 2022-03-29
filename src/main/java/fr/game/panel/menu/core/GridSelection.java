package fr.game.panel.menu.core;

import fr.game.constants.InInventory;
import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.core.FontEnum;
import fr.game.constants.core.IconSizeEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.main.AppVariables;
import fr.game.main.utils.BufferedObjects;
import fr.game.main.utils.Graphics2DUtils;
import fr.game.main.utils.NumberUtils;
import fr.game.mechanics.core.Message;
import fr.game.mechanics.core.MessageBox;
import fr.game.mechanics.core.ScaledImage;
import fr.game.panel.menu.MenuController;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GridSelection <K extends InInventory> extends InventoryLine{

    final private int paddingForSelection = 0;
    List<K> listOfValues;

    public GridSelection(String label, String description, K[] listOfValues, int screenX, int screenY, int width) {
        super(label, description, screenX, screenY, width);
        this.listOfValues = Arrays.asList(listOfValues);
        this.gameValue.setMaxValue(this.listOfValues.size()-1);
        this.gameValue.setLoop(NumberUtils.NumberLoopType.LOOP_STOP);
    }

    public void draw(Graphics2D graphics2D){
        int yToTop = screenY;
        drawLabel();
        yToTop += this.height;
        for(K valueType : listOfValues){
            Message skillMessage = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT,
                    valueType.getName(),
                    FontEnum.BEDROCK, new Color(71, 234, 196, 100), 30,
                    false, 20,
                    screenX, yToTop);
//            MenuSlot creationValidationButton = MenuController.getInstance().addMenuSlot(null, skillMessage, DirectionsEnum.DOWN);
            yToTop += iconSize.getSizeInPixel()*2;
        }

        K selectedValue = listOfValues.get(this.gameValue.getValue());
        //place here toget the same font as value message
        initMaxLengthForSliderValue(graphics2D);

        //placing the message from the right

        drawIconSlider(graphics2D);

    }
    protected void drawLabel(){
        if( displayLabel) {
            int labelX = screenX;
            Message labelMessage = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT, this.label, labelX, screenY, false) ;
            this.height = labelMessage.textBound.getValue();
        }
    }
    protected void drawIconSlider(Graphics2D graphics2D){
        if(displayIcon && this.listOfValues != null && listOfValues.size() > 0) {

            K valueFromList = this.listOfValues.get(this.gameValue.getValue());

            if(BufferedObjects.getInstance().getIcon(valueFromList, IconSizeEnum.MEDIUM) != null) {
                int iconY = screenY + AppVariables.tileSize;
                int iconX = screenX + (width/2);
                iconY -= getIconDimensionOffset(false);
                graphics2D.drawImage(BufferedObjects.getInstance().getIcon(valueFromList,
                        iconSize).getBufferedImage(), iconX, iconY, null);
            }
        }
    }

    //SPECIAL GETTER
    private void initMaxLengthForSliderValue(Graphics2D graphics2D){
        if(maxValueLength < 0){
            for(K value : listOfValues ){
                maxValueLength = (int) Math.max(maxValueLength, graphics2D.getFontMetrics().getStringBounds(String.valueOf(value), graphics2D).getWidth());
            }
            maxValueLength += maxValueLength /4;
        }
    }
    public K getSelectedValue(){
        return listOfValues.get(this.gameValue.getValue());
    }

}
