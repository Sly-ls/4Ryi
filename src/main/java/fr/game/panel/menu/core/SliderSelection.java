package fr.game.panel.menu.core;

import fr.game.constants.InInventory;
import fr.game.constants.core.IconSizeEnum;
import fr.game.main.AppVariables;
import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.core.FontEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.main.utils.BufferedObjects;
import fr.game.main.utils.Graphics2DUtils;
import fr.game.main.utils.NumberUtils;
import fr.game.mechanics.core.Message;
import fr.game.mechanics.core.MessageBox;
import fr.game.mechanics.core.ScaledImage;
import fr.game.panel.menu.MenuController;
import javafx.util.Pair;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class SliderSelection<K> extends InventoryLine{

    final private int paddingForSelection = 0;
    List<K> listOfValues;

    public SliderSelection(String label, String description, List<K> listOfValues, int screenX, int screenY, int width) {
        super(label, description, screenX, screenY, width);
        this.listOfValues = listOfValues;
        this.gameValue.setMaxValue(listOfValues.size()-1);
        this.gameValue.setLoop(NumberUtils.NumberLoopType.LOOP_STOP);
    }

    public void draw(Graphics2D graphics2D){

        drawLabel();

        Message decrementMessage = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT,"<",
                FontEnum.BEDROCK,new Color(71, 234, 196,100), AppVariables.tileSize,
                true, paddingForSelection,
                0,0);

        K selectedValue = listOfValues.get(this.gameValue.getValue());
        Message valueMessage = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT, String.valueOf(selectedValue), 0,0, false) ;
        //place here toget the same font as value message
        initMaxLengthForSliderValue(graphics2D);

        Message incrementMessage = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT,">",
                FontEnum.BEDROCK,new Color(71, 234, 196,100), AppVariables.tileSize,
                true, paddingForSelection,
                0,0);

        //placing the message from the right
        int buttonScreenY = screenY +AppVariables.tileSize/5;
        int valueScreenX;
        int incrementScreenX;
        int decrementScreenX;
        if(displayLabel) {
            incrementScreenX = screenX + width - incrementMessage.textBound.getKey();
            decrementScreenX = incrementScreenX - maxValueLength - decrementMessage.textBound.getKey();
            valueScreenX = decrementScreenX + decrementMessage.textBound.getKey() + maxValueLength / 2 - valueMessage.textBound.getKey() / 2;
        }else{
            valueScreenX = screenX + width/2 - maxValueLength/2;
            incrementScreenX = valueScreenX + incrementMessage.textBound.getKey();
            decrementScreenX = valueScreenX - decrementMessage.textBound.getKey();
        }
        decrementMessage.screenCoordinate = new Pair<>(decrementScreenX, buttonScreenY);
        valueMessage.screenCoordinate = new Pair<>(valueScreenX, screenY);
        incrementMessage.screenCoordinate = new Pair<>(incrementScreenX, buttonScreenY);

        //MenuSlot management
        MenuSlot decrementButton = MenuController.getInstance().addMenuSlot(";-1", decrementMessage, DirectionsEnum.DOWN);
        decrementButton.addAction(MenuSlotActionEnum.CHANGE_VALUE);
        decrementButton.setGameValue(this.gameValue);

        MenuSlot incrementButton = MenuController.getInstance().addMenuSlot(";1", incrementMessage, DirectionsEnum.RIGHT);
        incrementButton.addAction(MenuSlotActionEnum.CHANGE_VALUE);
        incrementButton.setGameValue(this.gameValue);

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

            int iconY = screenY + AppVariables.tileSize;
            int iconX = screenX + (width/2);
            iconX -= getIconDimensionOffset(true);
            iconY -= getIconDimensionOffset(false);
            if(valueFromList instanceof InInventory &&
                    BufferedObjects.getInstance().getIcon((InInventory) valueFromList, iconSize) != null) {
                graphics2D.drawImage(BufferedObjects.getInstance().getIcon((InInventory) valueFromList, iconSize).getBufferedImage(),
                        iconX, iconY, null);
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
