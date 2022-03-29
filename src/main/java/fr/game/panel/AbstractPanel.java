package fr.game.panel;

import fr.game.main.AppConstants;
import fr.game.constants.panel.PanelStateEnum;
import fr.game.main.ApplicationManager;
import fr.game.panel.menu.MenuController;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractPanel extends JPanel{

    protected AbstractUI absPanelUi;
    protected ApplicationManager applicationManager;
    protected PanelStateEnum panelState  = PanelStateEnum.NONE;
    protected Graphics2D graphics2D;

    public AbstractPanel(ApplicationManager applicationManager){
        this.applicationManager = applicationManager;
        MenuController.createInstance(this);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    //PANEL METHOD
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if(this.panelState == PanelStateEnum.IN_MENU){
            Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(mouseLocation, this.applicationManager.getWindow());
            this.applicationManager.getMouseHandler().updateMouseMenuPosition();
        }
        MenuController.getInstance().resetBeforeDraw();
        this.graphics2D = (Graphics2D) graphics;
        this.applicationManager.setGraphics2D(this.graphics2D);
        paintPanel();
        MenuController.getInstance().drawDebug(this.graphics2D);
        absPanelUi.drawMousePositionDebug();
        graphics2D.dispose();
    }
    public abstract void paintPanel();
    public abstract void quitPanel();
    public abstract void setupPanel();
    public abstract void update();

    //SPECIAL SETTER
    public void setPanelState(PanelStateEnum panelState) {
        if(AppConstants.DEBUG) {
            System.out.println(" changing panelState from "+ this.panelState
                    +" to "+ panelState);
        }
        this.panelState = panelState;
    }

    //GETTER & SETTER
    public abstract AbstractUI getUiPanel();
    public PanelStateEnum getPanelState() {
        return panelState;
    }
    public ApplicationManager getApplicationManager() {
        return applicationManager;
    }
    public Graphics2D getGraphics2D() {
        return graphics2D;
    }
}
