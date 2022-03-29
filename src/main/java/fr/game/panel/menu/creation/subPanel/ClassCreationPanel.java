package fr.game.panel.menu.creation.subPanel;

import fr.game.constants.InInventory;
import fr.game.constants.core.FontEnum;
import fr.game.constants.core.IconSizeEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.constants.game.SkillnEnum;
import fr.game.constants.rendered.GameObjectEnum;
import fr.game.constants.rendered.entity.ClassCodex;
import fr.game.main.AppVariables;
import fr.game.mechanics.core.Message;
import fr.game.mechanics.core.MessageBox;
import fr.game.panel.AbstractPanel;
import fr.game.panel.menu.core.GridSelection;
import fr.game.panel.menu.core.SliderSelection;
import fr.game.panel.menu.core.SubPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ClassCreationPanel extends SubPanel {


    public int MAX_ITEM_BY_COLUMN = 5;

    final int classSliderWidth = (AppVariables.SCREEN_WIDTH-marginLeft*2)/3;
    int sliderClassHeight;
    int skilListHeight;

    int backPackPanelWidth = (AppVariables.SCREEN_WIDTH-marginLeft*2)/3;
    int backPackPanelX;
    int backPackPanelY;
    int backPackPanelHeight;
    SliderSelection<ClassCodex> sliderClassSelection;
    ClassCodex selectedClass;
    /*
    i'll be using that, you only choose one skill for now
    You'll be using more when you have fate point
    As you'll have more point to access better race and advanced class
     */
    SkillnEnum skillSelected;
    SliderSelection<GameObjectEnum> sliderEquipementSelection;

    public ClassCreationPanel(AbstractPanel currentPanel) {
        super(currentPanel);
    }
    @Override
    public void setupSubPanel(int panelX, int panelY, boolean panelOnLeft, boolean panelOnTop ) {
        setupClassSlider(panelX,panelY,panelOnLeft,panelOnTop);
        setupSkillList();
        setupBackPackPanel();
        panelWidth = classSliderWidth;
        panelHeight=skilListHeight+backPackPanelHeight+marginBetweenPanel;
    }

    //STAT PANEL
    //CLASS PANEL
    private void setupClassSlider(int panelX, int panelY, boolean panelOnLeft, boolean panelOnTop ){

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

        sliderClassSelection = new SliderSelection<>("Classe","Description",
                Arrays.asList(ClassCodex.values()),
                xFromLeft, yToTop, classSliderWidth);
        sliderClassSelection.setSmallIcon(IconSizeEnum.BIG);
        yToTop += nextLineHeight*3;
        this.staticLinesInTheSubPanel.add(sliderClassSelection);


        sliderClassHeight = yToTop - this.panelY;
        //FIXME -= nextLineHeight*1 to be replaced by the slider selection height
        this.panelY-= nextLineHeight*1;
    }
    private void setupSkillList(){

        int yToTop = panelY+ sliderClassHeight + marginBetweenPanel;
        int maxSkillSize = 0;
        for(ClassCodex classSkillToAdd : ClassCodex.values()) {
            maxSkillSize = Math.max(maxSkillSize,Arrays.asList(classSkillToAdd.getClassSkill()).size());
        }
        yToTop += nextLineHeight * maxSkillSize;
        //FIXME - nextLineHeight*1 to be replaced by the slider selection height
        skilListHeight = yToTop - panelY - sliderClassHeight  - marginBetweenPanel - nextLineHeight*1;
        panelHeight=skilListHeight + sliderClassHeight;
    }
    private void setupBackPackPanel(){
        int maxSize = 0;
        backPackPanelX=panelX;
        backPackPanelY=panelY +panelHeight + marginBetweenPanel*3 +subwindowPadding*2;
        int yToTop = backPackPanelY;
        //titleHeight
        yToTop += nextLineHeight * 1;

        for(ClassCodex classCodexValue : ClassCodex.values()) {
            maxSize = Math.max(maxSize, Arrays.asList(classCodexValue.getEquimentSet()).size());
        }
        yToTop += nextLineHeight * maxSize;
        //FIXME - nextLineHeight*1 to be replaced by the slider selection height
        backPackPanelHeight =  yToTop-backPackPanelY -nextLineHeight*1;

        //FIXME -nextLineHeight*1 to be replaced by the slider selection height
    }

    //PRIVATE METHOD for update()
    public void updateSubPanel(){
        this.dynamicLinesInTheSubPanel = new ArrayList<>();
        applySelectedClass();
        updateSkillList();
        updateBackPackPanel();
    }
    private void updateSkillList(){
        if(selectedClass != null){
            int xFromLeft =  panelX;
            int yToTop = panelY+ sliderClassHeight + marginBetweenPanel;
            GridSelection classSkillGridSelection = new GridSelection<SkillnEnum>("Compétences :", "description", selectedClass.getClassSkill(), panelX, yToTop, classSliderWidth);
            this.dynamicLinesInTheSubPanel.add(classSkillGridSelection);
        }
    }
    private void updateBackPackPanel(){

        if(selectedClass != null){

            int xFromLeft =  backPackPanelX;
            int yToTop = backPackPanelY;

            MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT,
                    "Liste d'objets",
                    xFromLeft, yToTop,false);
            yToTop += nextLineHeight * 1;

            for(InInventory equipementToEquip : selectedClass.getEquimentSet()) {
                Message skillMessage = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT,
                        equipementToEquip.getName(),
                        FontEnum.BEDROCK, new Color(71, 234, 196, 100), 30,
                        false, subwindowPadding,
                        xFromLeft, yToTop);
//                MenuSlot creationValidationButton = MenuController.getInstance().addMenuSlot(null, skillMessage, DirectionsEnum.DOWN);
                yToTop += nextLineHeight * 1;
            }
        }
    }

    private void applySelectedClass(){
        if(selectedClass != sliderClassSelection.getSelectedValue()){
            if(selectedClass != null){
                //Remove selected skill bonus from entity
            }
            selectedClass = sliderClassSelection.getSelectedValue();
            for(SkillnEnum classSkillToAdd : selectedClass.getClassSkill()){
                //add first skill bonus to entity
                skillSelected = classSkillToAdd;
                break;
            }
        }
    }
}
