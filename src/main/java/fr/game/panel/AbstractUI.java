package fr.game.panel;

import fr.game.constants.InInventory;
import fr.game.constants.core.IconSizeEnum;
import fr.game.main.AppConstants;
import fr.game.main.AppVariables;
import fr.game.constants.core.FontEnum;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.rendered.GameObjectEnum;
import fr.game.main.utils.BufferedObjects;
import fr.game.main.utils.Graphics2DUtils;
import fr.game.mechanics.core.Message;
import fr.game.mechanics.core.ScaledImage;
import fr.game.panel.menu.MenuController;

import javax.swing.*;
import java.awt.*;
import java.util.*;


public abstract class AbstractUI {

    protected AbstractPanel currentPanel;

    public AbstractUI(AbstractPanel currentPanel) {
        this.currentPanel = currentPanel;
    }
    //CLASS METHOD
    public void drawIcon(InInventory icon, int x, int y){
        this.currentPanel.getGraphics2D().drawImage(BufferedObjects.getInstance().getIcon(icon,
                        IconSizeEnum.MEDIUM).getBufferedImage(),
                x, y, null);
    }
    public void drawTextSelection(Message message, int x, int y) {
        //FIXME to rework, it's not doing the job properply; the subWindow to long on the rigth
        x -= message.padding;
        y -= message.textBound.getValue() + message.padding;
        int width = message.textBound.getKey() + message.padding*2;
        int height = message.textBound.getValue() + message.padding*2;
        Color subWindowColor = message.subWindowColor;
        Color subWindowBorderColor = message.subWindowBorderColor;
        if(message.selected) {
            switch (message.type){
                case DIALOG:
                    width = AppVariables.SCREEN_WIDTH - x*2;
                    height = AppVariables.tileSize*4;
                    subWindowColor = new Color(0,0,0,255);
                    subWindowBorderColor = new Color(255,255,255,255);
                    break;
                default:
                    break;
            }
            drawSubWindow(x, y, width, height
                    ,subWindowColor , subWindowBorderColor);
        }
    }
    public void drawSubWindow(int x, int y, int width, int height, Color subWindowColor, Color subWindowBorderColor){
        this.currentPanel.getGraphics2D().setColor(subWindowColor);
        this.currentPanel.getGraphics2D().fillRoundRect(x,y,width,height, AppVariables.tileSize/2, AppVariables.tileSize/2);

        this.currentPanel.getGraphics2D().setColor(subWindowBorderColor);
        this.currentPanel.getGraphics2D().setStroke(new BasicStroke(5));
        this.currentPanel.getGraphics2D().drawRoundRect(x,y,width,height, AppVariables.tileSize/2, AppVariables.tileSize/2);;
    }

    //DEBUG METHOD
    public void drawMousePositionDebug() {
        if (AppConstants.DEBUG && AppConstants.DEBUG_MOUSE) {
            Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(mouseLocation, this.currentPanel.getApplicationManager().getWindow());
            StringBuffer sbDebug = new StringBuffer().append(" mouse screen : ");
            sbDebug.append(MouseInfo.getPointerInfo().getLocation().getX()).append(",").append(MouseInfo.getPointerInfo().getLocation().getY());
            sbDebug.append(" | mouse window : ");
            sbDebug.append((int) mouseLocation.getX()).append(",").append((int) mouseLocation.getY());
            sbDebug.append(" | menu cursor : ");
            sbDebug.append(MenuController.getInstance().getCursor().getKey()).append(",").append(MenuController.getInstance().getCursor().getValue());
            Graphics2DUtils.setUIFont(this.currentPanel.getGraphics2D(), FontEnum.FRANCISB,20,Color.GREEN);
            this.currentPanel.getGraphics2D().drawString(sbDebug.toString(), AppVariables.tileSize, AppVariables.SCREEN_HEIGHT - AppVariables.tileSize);
        }
    }
}
