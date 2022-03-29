package fr.game.panel.game;

import fr.game.constants.core.IconSizeEnum;
import fr.game.main.AppConstants;
import fr.game.main.AppVariables;
import fr.game.constants.core.FontEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.panel.PanelStateEnum;
import fr.game.constants.rendered.GameObjectEnum;
import fr.game.main.utils.BufferedObjects;
import fr.game.main.utils.Graphics2DUtils;
import fr.game.mechanics.core.MessageBox;
import fr.game.mechanics.core.ScaledImage;
import fr.game.panel.AbstractUI;

import java.awt.*;
import java.util.Map;

public class GameUI extends AbstractUI {

    GamePanel gamePanel;

    ScaledImage timeIcon;

    double playedTime;

    public GameUI(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.timeIcon = new ScaledImage(GameObjectEnum.HOURGLASS.getImagesPath());
    }

    public void draw(Graphics2D graphics2D){
        Graphics2DUtils.setUIFont(graphics2D, FontEnum.ARIAL,40,Color.WHITE);
        //PLAYER STATS
        drawPlayerUI();
        //TIMER
        graphics2D.drawImage(this.timeIcon.getBufferedImage(), AppVariables.tileSize*13, AppVariables.tileSize / 4, null);
        graphics2D.drawString(" : " + AppConstants.INTEGER_FORMAT.format(this.playedTime), AppVariables.tileSize*14, AppVariables.tileSize);

        if (GameController.getInstance().getPanel().getPanelState() == PanelStateEnum.PAUSE) {
            MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT, "PAUSE", AppVariables.SCREEN_WIDTH/2, AppVariables.SCREEN_HEIGHT/2, true);
        }else  if (GameController.getInstance().getPanel().getPanelState() == PanelStateEnum.PLAY) {
            this.playedTime += (double) 1/ AppVariables.FPS;
        }

        drawDebug();
    }
    private void drawDebug(){
        if(AppConstants.DEBUG) {
            MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT, GameController.getInstance().getPanel().getPanelState().name(), AppVariables.SCREEN_WIDTH/2, AppVariables.tileSize, true);
        }
    }

    public void drawVictoryScreen(Graphics2D graphics2D) {

        String winningText = "Félicitations";
        Graphics2DUtils.setUIFont(graphics2D, FontEnum.ARIAL,80,Color.GREEN);
        graphics2D.setColor(Color.GREEN);
        int textLength = (int) graphics2D.getFontMetrics().getStringBounds(winningText, graphics2D).getWidth();
        int x = AppVariables.SCREEN_WIDTH /2 - textLength/2;
        int y = AppVariables.SCREEN_HEIGHT /2 - AppVariables.tileSize*3;
        graphics2D.drawString(winningText, x, y);

        String winningText3 = "Tu as trouvé le trésor en "+ AppConstants.INTEGER_FORMAT.format(this.playedTime) +" secondes !";
        Graphics2DUtils.setUIFont(graphics2D, FontEnum.ARIAL,40,Color.GREEN);
        graphics2D.setColor(Color.YELLOW);
        int textLength3 = (int) graphics2D.getFontMetrics().getStringBounds(winningText3, graphics2D).getWidth();
        int x3 = AppVariables.SCREEN_WIDTH /2 - textLength3/2;
        int y3 = AppVariables.SCREEN_HEIGHT /2 + AppVariables.tileSize*3;
        graphics2D.drawString(winningText3, x3, y3);

    }
    private void drawPlayerUI() {
        int xStepInventory = AppVariables.tileSize/4;
        int yStepStat = AppVariables.tileSize+10;

        //inventory horizontal
        Graphics2DUtils.setUIFont(this.currentPanel.getGraphics2D(), FontEnum.FRANCISB,28,Color.WHITE);
        for (Map.Entry<GameObjectEnum, fr.game.mechanics.core.GameValue> backpackEntry : GameController.getInstance().getPlayer().getBackpack().getInventory()){

            this.currentPanel.getGraphics2D().drawImage(BufferedObjects.getInstance().getIcon(backpackEntry.getKey(), IconSizeEnum.SMALL).getBufferedImage(), xStepInventory, 0, null);
            this.currentPanel.getGraphics2D().drawString(String.valueOf(backpackEntry.getValue().getValue()), xStepInventory + AppVariables.tileSize, AppVariables.tileSize);
            xStepInventory += 2* AppVariables.tileSize;
            if(AppConstants.DEBUG && AppConstants.DEBUG_UI) {
                StringBuffer sbDebug = new StringBuffer(backpackEntry.getKey().name()).append(":");
                sbDebug.append(backpackEntry.getValue().getValue()).append("/").append(backpackEntry.getValue().getMaxValue());
                this.currentPanel.getGraphics2D().drawString(sbDebug.toString(),
                        +AppVariables.tileSize + AppVariables.tileSize / 4, yStepStat + AppVariables.tileSize);
                yStepStat += AppVariables.tileSize/2;
            }
        }

        //attributes vertical
        for (Map.Entry<GameValueEnum, fr.game.mechanics.core.GameValue> attributesEntry : GameController.getInstance().getPlayer().getAttributes().getInventory()){
            switch (attributesEntry.getKey()){
                case HEALTH:
                    int x = AppVariables.tileSize/4;
                    for(int i = 0; i < GameController.getInstance().getPlayer().getMaxAttributes(GameValueEnum.HEALTH); i+=2){
                        if(i+2 <= GameController.getInstance().getPlayer().getAttributes(GameValueEnum.HEALTH)){
                            this.currentPanel.getGraphics2D().drawImage(BufferedObjects.getInstance().getIcon(GameValueEnum.HEALTH, IconSizeEnum.SMALL).getBufferedImage(0),x,yStepStat,null);
                        }else if(i+1 <= GameController.getInstance().getPlayer().getAttributes(GameValueEnum.HEALTH)){
                            this.currentPanel.getGraphics2D().drawImage(BufferedObjects.getInstance().getIcon(GameValueEnum.HEALTH, IconSizeEnum.SMALL).getBufferedImage(1),x,yStepStat,null);
                        }else{
                            this.currentPanel.getGraphics2D().drawImage(BufferedObjects.getInstance().getIcon(GameValueEnum.HEALTH, IconSizeEnum.SMALL).getBufferedImage(2),x,yStepStat,null);
                        }
                        x += AppVariables.tileSize;
                    }
                    yStepStat += AppVariables.tileSize;
                default:
                    this.currentPanel.getGraphics2D().drawImage(BufferedObjects.getInstance().getIcon(attributesEntry.getKey(), IconSizeEnum.SMALL).getBufferedImage(), AppVariables.tileSize/4, yStepStat, null);
                    this.currentPanel.getGraphics2D().drawString(String.valueOf(attributesEntry.getValue().getValue()), + AppVariables.tileSize+ AppVariables.tileSize/4, yStepStat + AppVariables.tileSize/2);

                    yStepStat += AppVariables.tileSize;
                    break;
            }
            if(AppConstants.DEBUG && AppConstants.DEBUG_UI) {
                StringBuffer sbDebug = new StringBuffer(attributesEntry.getKey().name()).append(":");
                sbDebug.append(attributesEntry.getValue().getValue()).append("/").append(attributesEntry.getValue().getMaxValue());
                this.currentPanel.getGraphics2D().drawString(sbDebug.toString(),
                        +AppVariables.tileSize + AppVariables.tileSize / 4, yStepStat + AppVariables.tileSize);
                yStepStat += AppVariables.tileSize;
            }
        }

    }
}
