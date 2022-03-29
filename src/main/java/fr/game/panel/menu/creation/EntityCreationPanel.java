package fr.game.panel.menu.creation;

import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.panel.PanelStateEnum;
import fr.game.constants.rendered.entity.EntityEnum;
import fr.game.main.AppVariables;
import fr.game.main.ApplicationManager;
import fr.game.mechanics.core.MessageBox;
import fr.game.panel.AbstractPanel;
import fr.game.panel.AbstractUI;
import fr.game.panel.game.GameController;
import fr.game.panel.menu.core.*;
import fr.game.panel.menu.creation.subPanel.ClassCreationPanel;
import fr.game.panel.menu.creation.subPanel.StatCreationPanel;
import fr.game.rendererd.entity.PC.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class EntityCreationPanel  extends AbstractPanel {


    protected int marginTop= AppVariables.tileSize*3;
    protected int marginLeft= AppVariables.tileSize*2;

    EntityCreationUI uiPanel;

    //used in this panel

    Map<String,SubPanel> subPanelInTheScreen = new HashMap<>();
    //Parameters for IHM creation




    //CONSTRUCTOR
    public EntityCreationPanel(ApplicationManager applicationManager) {
        super(applicationManager);
        this.setBackground(Color.DARK_GRAY);
        this.uiPanel = new EntityCreationUI(this);
        this.absPanelUi = this.uiPanel;
        this.setPanelState(PanelStateEnum.IN_MENU);
    }

    //PANEL METHOD
    public void quitPanel() {

    }
    public void setupPanel() {
        StatCreationPanel statCreationPanel = new StatCreationPanel(this);
        statCreationPanel.setupSubPanel(marginLeft,marginTop,false,false);
        this.subPanelInTheScreen.put("STAT",statCreationPanel);

        ClassCreationPanel classCreationPanel = new ClassCreationPanel(this);
        classCreationPanel.setupSubPanel(statCreationPanel, DirectionsEnum.RIGHT);
        this.subPanelInTheScreen.put("CLASS",classCreationPanel);

    }
    public void update() {
        this.subPanelInTheScreen.get("STAT").updateSubPanel();
        this.subPanelInTheScreen.get("CLASS").updateSubPanel();
    }
    public AbstractUI getUiPanel() {
        return uiPanel;
    }
    public void paintPanel(){
        uiPanel.drawSubPanel(graphics2D);
        uiPanel.drawValidateButton(graphics2D);
        MessageBox.getInstance().drawMessage();
    }

    //PRIVATE METHOD for setupPanel()

    //STAT PANEL

    private void setupBottomLeftPanel(){
//        classPanelX=marginLeft;
//        classPanelY=statPanelY+statPanelHeight+marginBetweenPanel+subwindowPadding*2;
//        int xFromLeft =  classPanelX;
//        int yToTop = classPanelY;
//        sliderClassSelection = new SliderSelection<>("Classe","Description",
//                Arrays.asList(ClassCodex.values()),
//                xFromLeft, yToTop, classSliderPanelWidth);
//
//        classPanelWidth = classSliderPanelWidth;
//        classPanelHeight=classPanelY-yToTop;
    }

    //PRIVATE METHOD for update()

    //MENU SPECIFIC METHOD
    public void validateCreation(){
        Map<GameValueEnum, InventoryLine> givenPointAttribute = ((StatCreationPanel)this.subPanelInTheScreen.get("STAT")).getGivenPointAttribute();
        Player player = new Player(EntityEnum.PERSO_5,0,0);
        player.getAttributes().changeQuantity(GameValueEnum.BODY,givenPointAttribute.get(GameValueEnum.BODY).getGameValue().getValue());
        player.getAttributes().changeQuantity(GameValueEnum.MIND,givenPointAttribute.get(GameValueEnum.MIND).getGameValue().getValue());
        player.getAttributes().changeQuantity(GameValueEnum.SOUL,givenPointAttribute.get(GameValueEnum.SOUL).getGameValue().getValue());
        player.getAttributes().changeQuantity(GameValueEnum.SPEED,givenPointAttribute.get(GameValueEnum.SPEED).getGameValue().getValue());
        player.getAttributes().changeQuantity(GameValueEnum.HEALTH,givenPointAttribute.get(GameValueEnum.HEALTH).getGameValue().getValue(),givenPointAttribute.get(GameValueEnum.HEALTH).getGameValue().getValue());
        player.getAttributes().changeQuantity(GameValueEnum.STAMINA,givenPointAttribute.get(GameValueEnum.STAMINA).getGameValue().getValue());
        GameController.getInstance().setPlayer(player);
    }

    //GETTER & SETTER
    public void setUiPanel(EntityCreationUI uiPanel) {
        this.uiPanel = uiPanel;
    }
}