package fr.game.panel.menu.title;

import fr.game.constants.panel.PanelStateEnum;
import fr.game.main.ApplicationManager;
import fr.game.mechanics.controller.KeyboardController;
import fr.game.mechanics.core.MessageBox;
import fr.game.panel.AbstractPanel;
import fr.game.panel.AbstractUI;
import fr.game.panel.menu.MenuController;

import java.awt.*;

public class TitlePanel extends AbstractPanel {

    TitleUI uiPanel;

    public TitlePanel(ApplicationManager applicationManager) {
        super(applicationManager);
        this.applicationManager = applicationManager;
        this.uiPanel = new TitleUI(this);
        this.absPanelUi = this.uiPanel;
        this.setPanelState(PanelStateEnum.IN_MENU);
    }
    public void setupPanel() {
    }
    public void quitPanel() {
    }
    public void update() {
    }
    public AbstractUI getUiPanel() {
        return uiPanel;
    }
    public void paintPanel(){
        uiPanel.drawMenu(graphics2D);
        MessageBox.getInstance().drawMessage();
    }

}
