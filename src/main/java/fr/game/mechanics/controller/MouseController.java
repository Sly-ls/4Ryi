package fr.game.mechanics.controller;

import fr.game.main.AppConstants;
import fr.game.main.ApplicationManager;
import fr.game.panel.menu.MenuController;
import fr.game.panel.menu.core.MenuSlot;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MouseController implements MouseListener {
    protected boolean pressed, entered, exited;
    protected ApplicationManager appManager;

    public MouseController(ApplicationManager applicationManager) {
        this.appManager = applicationManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(updateMouseMenuPosition()){
            MenuController.getInstance().useSelectedMenuSlot();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed= true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        entered = true;
        exited = false;
    }

    @Override
    public void mouseExited(MouseEvent e) {

        entered = false;
        exited = true;
    }

    public boolean updateMouseMenuPosition() {
        boolean isMouseOnButton = false;
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mouseLocation, appManager.getWindow());
        for(MenuSlot slot : MenuController.getInstance().getMenuSlotInPanel().values()){
            if(slot.isMouseCursorWithin((int) mouseLocation.getX(),(int) mouseLocation.getY())) {
                MenuController.getInstance().setCursor(new Pair<>(slot.getMenuX(), slot.getMenuY()));
                isMouseOnButton=true;
            }
        }
        printMousePositionDebu((int) mouseLocation.getX(),(int) mouseLocation.getY());
        return isMouseOnButton;
    }
    private void printMousePositionDebu(int mouseX, int mouseY){
        if(AppConstants.DEBUG && AppConstants.DEBUG_MOUSE) {
            StringBuffer sbDebug = new StringBuffer(" mouse screen : ");
            sbDebug.append(MouseInfo.getPointerInfo().getLocation().getX()).append( ",").append(MouseInfo.getPointerInfo().getLocation().getY());
            sbDebug.append(" | mouse window : ");
            sbDebug.append(mouseX).append( ",").append(mouseY);
            System.out.println(sbDebug);

        }
    }
}
