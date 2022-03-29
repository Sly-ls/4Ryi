package fr.game.panel.menu.creation.subPanel;

import fr.game.constants.core.IconSizeEnum;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.game.effect.GameValueType;
import fr.game.constants.rendered.entity.RaceCodex;
import fr.game.main.AppConstants;
import fr.game.main.AppVariables;
import fr.game.mechanics.core.GameValue;
import fr.game.panel.AbstractPanel;
import fr.game.panel.menu.MenuController;
import fr.game.panel.menu.core.IncrementableValue;
import fr.game.panel.menu.core.InventoryLine;
import fr.game.panel.menu.core.SliderSelection;
import fr.game.panel.menu.core.SubPanel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StatCreationPanel extends SubPanel {

    SliderSelection<RaceCodex> sliderRaceSelection;
    RaceCodex selectedRace;
    InventoryLine remainingPoint;
    Map<GameValueEnum, InventoryLine> givenPointAttribute = new HashMap<>();

    final int raceSliderPanelWidth = (AppVariables.SCREEN_WIDTH-marginLeft*2)/3;
    int sliderRaceHeight;
    final int primaryStatWidth = (AppVariables.SCREEN_WIDTH-marginLeft*2)/4;
    int primaryStatPanelHeight;
    final int secondaryStatWidth = (AppVariables.SCREEN_WIDTH-marginLeft*2)/4;
    int secondaryStatPanelHeight;

    public StatCreationPanel(AbstractPanel currentPanel) {
        super(currentPanel);
    }


    //STAT PANEL
    public void updateSubPanel(){
        applySelectedRace();
        recalculateSecondaryAttributes();
        updateRemainingPoint();

    }
    public void setupSubPanel(int panelX, int panelY, boolean panelOnLeft, boolean panelOnTop){
        MenuController.getInstance().getPointPools().changeQuantityThen(MenuController.MenuPointEnum.POOL_CREATION,AppConstants.NB_CREATION_POINTS);
        setupRacePanel( panelX,  panelY,  panelOnLeft,  panelOnTop);
        setupPrimaryStatPanel();
        setupSecondaryStatPanel();
        panelWidth = primaryStatWidth + secondaryStatWidth + marginBetweenPanel;
        panelHeight = sliderRaceHeight + Math.max(primaryStatPanelHeight,secondaryStatPanelHeight);
    }
    private void setupRacePanel(int panelX, int panelY, boolean panelOnLeft, boolean panelOnTop){

        this.panelX=panelX;
        if(panelOnLeft){
            this.panelX+= marginBetweenPanel+subwindowPadding*2;
        }
        this.panelY=panelY;
        if(panelOnTop){
            this.panelY+= marginBetweenPanel+subwindowPadding*2;
        }

        int xFromLeft =  this.panelX;
        int yToTop = this.panelY;

        sliderRaceSelection = new SliderSelection<>("Race","Description",
                Arrays.asList(RaceCodex.values()),
                xFromLeft, yToTop, raceSliderPanelWidth);
        sliderRaceSelection.setSmallIcon(IconSizeEnum.XL);
        yToTop += nextLineHeight*1;
        //place for portrait//miniature
        yToTop +=nextLineHeight*5;

        sliderRaceHeight = yToTop-this.panelY;
        //FIXME -= nextLineHeight*1 to be replaced by the slider selection height
        this.panelY-= nextLineHeight*1;
        this.staticLinesInTheSubPanel.add(sliderRaceSelection);
    }
    private void setupPrimaryStatPanel(){
        int xFromLeft =  panelX;
        int yToTop = panelY+ sliderRaceHeight;
        this.remainingPoint = new InventoryLine("Point à répartir : ", "desc", xFromLeft, yToTop, primaryStatWidth);
        this.remainingPoint.setDisplayIcon(false);
        this.remainingPoint.getGameValue().setValue(AppConstants.NB_CREATION_POINTS);
        this.staticLinesInTheSubPanel.add(this.remainingPoint);
        yToTop += nextLineHeight*2;
        yToTop = setupVerticalStatPanel(GameValueType.PRIMARY_STAT, true, xFromLeft, yToTop, primaryStatWidth);
        //FIXME -nextLineHeight*1 to be replaced by the slider selection height
        primaryStatPanelHeight = yToTop-panelY- sliderRaceHeight -nextLineHeight*1;
    }
    private void setupSecondaryStatPanel(){
        int xFromLeft = panelX+ primaryStatWidth + marginBetweenPanel;
        int yToTop = panelY+ sliderRaceHeight;
        //to align with the stats under the remaining point line
        yToTop += nextLineHeight*2;

        yToTop = setupVerticalStatPanel(GameValueType.SECONDARY_STAT, false, xFromLeft, yToTop, secondaryStatWidth);
        secondaryStatPanelHeight = yToTop-panelY- sliderRaceHeight -nextLineHeight*1;
    }
    private int setupVerticalStatPanel(GameValueType gameValueTypeSelected, boolean editable, int xFromLeft, int yToTop, int width){
        for(GameValueEnum gameValueType : GameValueEnum.values()){
            if(gameValueType.getTypeOfStat()==gameValueTypeSelected) {
                InventoryLine newLine;
                if(editable) {
                    newLine = new IncrementableValue(gameValueType, xFromLeft, yToTop, width);
                }else{
                    newLine = new InventoryLine(gameValueType, xFromLeft, yToTop, width);
                }
                givenPointAttribute.put(gameValueType, newLine);
                yToTop += nextLineHeight*1;
                this.staticLinesInTheSubPanel.add(newLine);
            }
        }
        return yToTop;
    }


    //PRIVATE METHOD for update()
    private void applySelectedRace(){
        if(selectedRace != sliderRaceSelection.getSelectedValue()){
            if(selectedRace != null){
                for(Map.Entry<GameValueEnum, GameValue> statBonus : selectedRace.getAttributeBonus().getInventory()){
                    givenPointAttribute.get(statBonus.getKey()).getGameValue().changeValue(-selectedRace.getAttributeBonus().getInventory(statBonus.getKey()));
                    givenPointAttribute.get(statBonus.getKey()).getGameValue().changeMinValue(-selectedRace.getAttributeBonus().getInventory(statBonus.getKey()));
                }
            }
            selectedRace = sliderRaceSelection.getSelectedValue();
            for(Map.Entry<GameValueEnum, GameValue> statBonus : selectedRace.getAttributeBonus().getInventory()){
                givenPointAttribute.get(statBonus.getKey()).getGameValue().changeValue(selectedRace.getAttributeBonus().getInventory(statBonus.getKey()));
                givenPointAttribute.get(statBonus.getKey()).getGameValue().changeMinValue(selectedRace.getAttributeBonus().getInventory(statBonus.getKey()));
            }
        }
    }
    private void recalculateSecondaryAttributes() {
        //FIXME should be dynamic and using something (a function ?) declared in gameValueEnum
        for(GameValueEnum attributeType : givenPointAttribute.keySet()){
            if(attributeType.getTypeOfStat()==GameValueType.SECONDARY_STAT) {
                int attributeValue = 0;
                switch (attributeType){
                    case SPEED:
                        attributeValue = Math.max(givenPointAttribute.get(GameValueEnum.BODY).getGameValue().getValue(), givenPointAttribute.get(GameValueEnum.MIND).getGameValue().getValue());
                        break;
                    case HEALTH:
                        attributeValue = givenPointAttribute.get(GameValueEnum.BODY).getGameValue().getValue()
                                + givenPointAttribute.get(GameValueEnum.SOUL).getGameValue().getValue();
                        break;
                    case STAMINA:
                        attributeValue = (givenPointAttribute.get(GameValueEnum.MIND).getGameValue().getValue()
                                + givenPointAttribute.get(GameValueEnum.SOUL).getGameValue().getValue())/2;
                        break;
                    default:
                        break;
                }
                givenPointAttribute.get(attributeType).getGameValue().setValue(attributeValue);
            }
        }
    }
    private void updateRemainingPoint() {
        this.remainingPoint.getGameValue().setValue(
                MenuController.getInstance().getPointPools().getInventory((MenuController.MenuPointEnum.POOL_CREATION))
        );
    }

    //GETTER & SETTER

    public Map<GameValueEnum, InventoryLine> getGivenPointAttribute() {
        return givenPointAttribute;
    }
}
