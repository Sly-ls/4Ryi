package fr.game.panel.menu.core;

import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.core.FontEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.constants.game.GameValueEnum;
import fr.game.main.AppVariables;
import fr.game.mechanics.core.Message;
import fr.game.mechanics.core.MessageBox;
import fr.game.panel.menu.MenuController;
import javafx.util.Pair;

import java.awt.*;

public class IncrementableValue extends InventoryLine{

    final private int paddingForSelection = 0;

    public IncrementableValue(GameValueEnum gameValueDescription, int screenX, int screenY, int width) {
        super(gameValueDescription,screenX,screenY,width);
    }
    public IncrementableValue(String label, String description, int screenX, int screenY, int width) {
        super(label, description, screenX, screenY, width);
    }

    public void draw(Graphics2D graphics2D){

        drawIcon(graphics2D);
        drawLabel();
        Message decrementMessage = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT,"-",
                FontEnum.BEDROCK,new Color(71, 234, 196,100), AppVariables.tileSize,
                true, paddingForSelection,
                screenX, screenY);

        Message valueMessage = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT,
                 String.valueOf(this.gameValue.getValue()),
                screenX, screenY, false) ;
        if(!displayLabel){
            this.height = valueMessage.textBound.getValue();
        }
        initMaxLength(graphics2D);

        Message incrementMessage = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT,"+",
                FontEnum.BEDROCK,new Color(71, 234, 196,100), AppVariables.tileSize,
                true, paddingForSelection,
                0, screenY);

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
        MenuSlot decrementButton = MenuController.getInstance().addMenuSlot(
                MenuController.MenuPointEnum.POOL_CREATION+";-1", decrementMessage, DirectionsEnum.DOWN);
        decrementButton.addAction(MenuSlotActionEnum.CHANGE_VALUE);
        decrementButton.setGameValue(this.gameValue);

        MenuSlot incrementButton = MenuController.getInstance().addMenuSlot(
                MenuController.MenuPointEnum.POOL_CREATION+";1", incrementMessage, DirectionsEnum.RIGHT);
        incrementButton.addAction(MenuSlotActionEnum.CHANGE_VALUE);
        incrementButton.setGameValue(this.gameValue);
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
}
