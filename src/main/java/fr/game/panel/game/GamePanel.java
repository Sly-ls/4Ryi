package fr.game.panel.game;

import fr.game.constants.panel.PanelStateEnum;
import fr.game.constants.rendered.GameObjectEnum;
import fr.game.main.ApplicationManager;
import fr.game.mechanics.controller.LightController;
import fr.game.mechanics.controller.SoundController;
import fr.game.mechanics.core.MessageBox;
import fr.game.panel.AbstractPanel;
import fr.game.panel.AbstractUI;
import fr.game.rendererd.AbstractRendered;
import fr.game.rendererd.TileManager;

import java.util.Comparator;
import java.util.List;

public class GamePanel extends AbstractPanel {

    //COMPONENT MECHANICS
    protected GameUI uiPanel;
    //TODO put order process in camera
    public Comparator coordinateComparator = new GameController.CoordinateComparator();

    public GamePanel(ApplicationManager applicationManager) {
        super(applicationManager);
        this.uiPanel = new GameUI(this);
        this.absPanelUi = this.uiPanel;
    }


    @Override
    public void quitPanel(){
        SoundController.getInstance().stopMusic();
    }
    @Override
    public void setupPanel(){
        GameController.createInstance(this);
        GameController.getInstance().setupGame();
        this.setPanelState(PanelStateEnum.PLAY);
    }

    public void update(){
        CheckVictoryCondition();
        if (this.panelState == PanelStateEnum.PLAY) {
            List<AbstractRendered> listToUpdate = GameController.getInstance().getListToUpdate();
            for(AbstractRendered toUpdate : listToUpdate){
                toUpdate.update();
            }
            LightController.getInstance().update();
        }

        if (this.panelState == PanelStateEnum.PAUSE || this.panelState == PanelStateEnum.DIALOG) {
        }
    }

    public void CheckVictoryCondition(){
        if(GameController.getInstance().getPlayer().getBackpack(GameObjectEnum.CHEST) > 0){
            this.setPanelState(PanelStateEnum.VICTORY);
        }
    }
    @Override
    public AbstractUI getUiPanel() {
        return uiPanel;
    }

    public void paintPanel(){
        List<AbstractRendered> listToDisplay = GameController.getInstance().getListToDisplay();

        TileManager.getInstance().draw(graphics2D);
        for (AbstractRendered toDisplay : listToDisplay) {
            toDisplay.draw(graphics2D);
        }
        LightController.getInstance().drawLight(graphics2D);
        uiPanel.draw(graphics2D);
        MessageBox.getInstance().drawMessage();
        switch (this.panelState) {
            //TILE
            case PAUSE:
                break;
            case VICTORY:
                uiPanel.drawVictoryScreen(graphics2D);
                break;
            case PLAY:
                break;
            default:
                break;
        }
        for (AbstractRendered toDisplay : listToDisplay) {
            toDisplay.drawDebug(graphics2D);
        }
        uiPanel.drawMousePositionDebug();
    }

}


