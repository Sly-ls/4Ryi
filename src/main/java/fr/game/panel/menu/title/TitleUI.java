package fr.game.panel.menu.title;

import fr.game.main.AppConstants;
import fr.game.main.AppVariables;
import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.core.FontEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.constants.panel.PanelEnum;
import fr.game.constants.rendered.GameObjectEnum;
import fr.game.mechanics.core.Message;
import fr.game.mechanics.core.MessageBox;
import fr.game.mechanics.core.ScaledImage;
import fr.game.panel.AbstractUI;
import fr.game.panel.menu.MenuController;
import fr.game.panel.menu.core.MenuSlot;
import fr.game.panel.menu.core.MenuSlotActionEnum;
import javafx.util.Pair;

import java.awt.*;

public class TitleUI extends AbstractUI {

    final int paddingForSelection = 30;

    ScaledImage gameIcon;
    TitlePanel currentPanel;

    public TitleUI(TitlePanel currentPanel) {
        super(currentPanel);
        this.currentPanel = currentPanel;
    }

    public void drawMenu(Graphics2D graphics2D) {

        Pair<Integer, Integer> cursorValue = MenuController.getInstance().getCursor();
        this.gameIcon = new ScaledImage(GameObjectEnum.HOURGLASS.getImagesPath()[0]);

//        GAME ICON
        this.currentPanel.getGraphics2D().drawImage(this.gameIcon.getBufferedImage(),
                (AppVariables.SCREEN_WIDTH /2 + AppVariables.SCREEN_WIDTH /4) - (AppVariables.tileSize*6)/2,
                AppVariables.tileSize*5 - (AppVariables.tileSize*6)/2 ,
                AppVariables.tileSize*8,
                AppVariables.tileSize*6,null);

        int yToTop = AppVariables.tileSize*2;
        int xFromLeft = AppVariables.tileSize*2;
        MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT, AppConstants.GAME_TITLE,
                FontEnum.BEDROCK,new Color(71, 234, 196,100), 100,
                false, 28,
                xFromLeft-3 ,yToTop-3);
        MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT, AppConstants.GAME_TITLE,
                FontEnum.BEDROCK, Color.WHITE, 100,
                false, 28,
                xFromLeft,yToTop);

        //Subtitle
        yToTop += AppVariables.tileSize;
        MessageBox.getInstance().sendMessage( MessageTypeEnum.NANO_TEXT,"Time is passing by... " + System.currentTimeMillis(),
                FontEnum.BEDROCK,new Color(71, 234, 196,100), 28,
                false, 28,
                xFromLeft,yToTop);

        //Menu
        xFromLeft += AppVariables.tileSize;
        yToTop += AppVariables.tileSize*6;

        Message playMessage = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT,"Jouer",
                FontEnum.BEDROCK,new Color(71, 234, 196,100), 60,
                cursorValue.getValue() == 0, paddingForSelection,
                xFromLeft,yToTop);
        MenuSlot playButton = MenuController.getInstance().addMenuSlot(PanelEnum.GAME.name(), playMessage, DirectionsEnum.DOWN);
        playButton.addAction(MenuSlotActionEnum.CHANGE_PANEL);
        yToTop += AppVariables.tileSize*2;

        Message entityCreationMessage = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT,"Nouveau héro",
                FontEnum.BEDROCK,new Color(71, 234, 196,100), 60,
                cursorValue.getValue() == 1, paddingForSelection,
                xFromLeft,yToTop);
        MenuSlot entityCreationButton = MenuController.getInstance().addMenuSlot(PanelEnum.ENTITY_CREATION.name(), entityCreationMessage, DirectionsEnum.DOWN);
        entityCreationButton.addAction(MenuSlotActionEnum.CHANGE_PANEL);
        yToTop += AppVariables.tileSize*2;

        Message worldMessage = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT,"Mondes...",
                FontEnum.BEDROCK,new Color(71, 234, 196,100), 60,
                cursorValue.getValue() == 2, paddingForSelection,
                xFromLeft,yToTop);
        MenuSlot worldSelectionButton = MenuController.getInstance().addMenuSlot(PanelEnum.WORLD_SELECTION.name(), worldMessage, DirectionsEnum.DOWN);
        worldSelectionButton.addAction(MenuSlotActionEnum.CHANGE_PANEL);
        yToTop += AppVariables.tileSize*2;

        Message quitMessage= MessageBox.getInstance().sendMessage( MessageTypeEnum.MENU_TEXT,"Quitter",
                FontEnum.BEDROCK,new Color(71, 234, 196,100), 60,
                cursorValue.getValue() == 3, paddingForSelection,
                xFromLeft,yToTop);
        MenuSlot quitButton = MenuController.getInstance().addMenuSlot(null, quitMessage, DirectionsEnum.DOWN);
        quitButton.addAction(MenuSlotActionEnum.QUIT);

    }

}
