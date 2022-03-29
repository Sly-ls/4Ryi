package fr.game.mechanics.controller;

import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.panel.PanelEnum;
import fr.game.constants.panel.PanelStateEnum;
import fr.game.main.ApplicationManager;
import fr.game.mechanics.AbstractKeyHandler;
import fr.game.panel.menu.MenuController;

import java.awt.event.KeyEvent;

public class KeyboardController extends AbstractKeyHandler {

    public KeyboardController(ApplicationManager appManager) {
        super(appManager);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        code =  e.getKeyCode();
        if(this.appManager.getDisplayType() == PanelEnum.GAME){
            switch (code) {
                case KeyEvent.VK_P:
                    if (this.appManager.getCurrentPanel().getPanelState()== PanelStateEnum.PAUSE) {
                        this.appManager.getCurrentPanel().setPanelState(PanelStateEnum.PLAY);
                    } else if (this.appManager.getCurrentPanel().getPanelState() == PanelStateEnum.PLAY) {
                        this.appManager.getCurrentPanel().setPanelState(PanelStateEnum.PAUSE);
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        code =  e.getKeyCode();
        isKeyReleasd = true;
        switch (code){
            case KeyEvent.VK_SPACE:
                if (this.appManager.getCurrentPanel().getPanelState() == PanelStateEnum.IN_MENU) {
                    MenuController.getInstance().useSelectedMenuSlot();
                }
                if (this.appManager.getCurrentPanel().getPanelState() == PanelStateEnum.VICTORY) {
                    this.appManager.displayNewPanel(PanelEnum.TITLE_SCREEN);
                }
                if (this.appManager.getCurrentPanel().getPanelState() == PanelStateEnum.DIALOG) {
                    this.appManager.getCurrentPanel().setPanelState(PanelStateEnum.PLAY);
                }
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                if (this.appManager.getCurrentPanel().getPanelState() == PanelStateEnum.IN_MENU) {
                    MenuController.getInstance().setCursor(DirectionsEnum.DOWN);
                }
                break;
            case KeyEvent.VK_Z:
            case KeyEvent.VK_UP:
                if (this.appManager.getCurrentPanel().getPanelState() == PanelStateEnum.IN_MENU) {
                    MenuController.getInstance().setCursor(DirectionsEnum.UP);
                }
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                if (this.appManager.getCurrentPanel().getPanelState() == PanelStateEnum.IN_MENU) {
                    MenuController.getInstance().setCursor(DirectionsEnum.RIGHT);
                }
                break;
            case KeyEvent.VK_Q:
            case KeyEvent.VK_LEFT:
                if (this.appManager.getCurrentPanel().getPanelState() == PanelStateEnum.IN_MENU) {
                    MenuController.getInstance().setCursor(DirectionsEnum.LEFT);
                }
                break;
            default:
                break;
        }
    }


}
