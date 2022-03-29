package fr.game.panel.menu.core;

import fr.game.constants.core.DirectionsEnum;
import fr.game.main.AppVariables;
import fr.game.panel.AbstractPanel;
import fr.game.panel.AbstractUI;
import fr.game.panel.menu.creation.EntityCreationPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class SubPanel extends AbstractUI {
    final int paddingForSelection = 10;

    public int panelX = 0;
    public int panelY = 0;
    protected int panelWidth = 0;
    protected int panelHeight = 0;
    protected List<InventoryLine> staticLinesInTheSubPanel = new ArrayList<>();
    protected List<InventoryLine> dynamicLinesInTheSubPanel = new ArrayList<>();
    protected int subwindowPadding = AppVariables.tileSize;
    protected int nextLineHeight = AppVariables.tileSize;
    protected int marginBetweenPanel = AppVariables.tileSize;
    protected int marginTop= AppVariables.tileSize*3;
    protected int marginLeft= AppVariables.tileSize*2;
    Color backgroundColor = Color.GRAY;
    Color outlineColor = Color.BLACK;


    public SubPanel(EntityCreationPanel currentPanel) {
        super(currentPanel);
        this.currentPanel = currentPanel;
    }

    public SubPanel(AbstractPanel currentPanel) {
        super(currentPanel);
    }
    public abstract void setupSubPanel(int panelX, int panelY, boolean panelOnLeft, boolean panelOnTop);
    public abstract void updateSubPanel();
    public void setupSubPanel(SubPanel previousPanel, DirectionsEnum direction ) {
        switch (direction){
            case DOWN:
                setupSubPanel(previousPanel.panelX,
                        previousPanel.panelY+previousPanel.panelHeight,
                        false,true);
                break;
            case RIGHT:
                setupSubPanel(previousPanel.panelX+previousPanel.panelWidth,
                        marginTop,
                        true,false);
                break;
            default:
                break;
        }
    }
    public void draw(Graphics2D graphics2D) {
        drawSubWindow(panelX - subwindowPadding,
                panelY - subwindowPadding,
                panelWidth + subwindowPadding*2,
                panelHeight + subwindowPadding*2,
                backgroundColor, outlineColor);
        System.out.println("lineInTheSubPanel : " + staticLinesInTheSubPanel.size());
        for(InventoryLine line : staticLinesInTheSubPanel){
            line.draw(graphics2D);
        }
        for(InventoryLine line : dynamicLinesInTheSubPanel){
            line.draw(graphics2D);
        }
    }
}
