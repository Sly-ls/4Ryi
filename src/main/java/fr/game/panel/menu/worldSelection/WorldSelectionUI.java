package fr.game.panel.menu.worldSelection;

import fr.game.main.AppVariables;
import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.core.FontEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.constants.panel.PanelEnum;
import fr.game.constants.rendered.GameObjectEnum;
import fr.game.constants.world.WorldEnum;
import fr.game.mechanics.core.Message;
import fr.game.mechanics.core.MessageBox;
import fr.game.mechanics.core.ScaledImage;
import fr.game.panel.AbstractUI;
import fr.game.panel.menu.MenuController;
import fr.game.panel.menu.core.MenuSlot;
import fr.game.panel.menu.core.MenuSlotActionEnum;
import javafx.util.Pair;

import java.awt.*;

public class WorldSelectionUI extends AbstractUI {

    final int paddingForSelection = 30;
    public final int MAX_ITEM_BY_COLUMN = 5;
    ScaledImage gameIcon;
    WorldSelectionPanel currentPanel;

    public WorldSelectionUI(WorldSelectionPanel currentPanel) {
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
                AppVariables.tileSize*6,
                AppVariables.tileSize*6,null);

        int yToTop = AppVariables.tileSize*1;

        //Title
        MessageBox.getInstance().sendMessage(MessageTypeEnum.NANO_TEXT,"Choisis ton monde :",
                FontEnum.BEDROCK,new Color(71, 234, 196,100), 68,
                false, 28,
                AppVariables.tileSize*2,yToTop);

        //Menu
        yToTop += AppVariables.tileSize*2;
        int slotY = 0;
        int slotX = 0;
        DirectionsEnum nextItemDirection = DirectionsEnum.DOWN;
        for(WorldEnum worldEnum : WorldEnum.values()){

            int messageX = slotX*AppVariables.tileSize*10+AppVariables.tileSize*2;
            int messageY = slotY*AppVariables.tileSize*2+yToTop;
            Message message = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT,
                    (slotY+slotX+1)+". " + worldEnum.getTitle(),
                    FontEnum.BEDROCK,new Color(71, 234, 196,100), 40,
                    true
                    , paddingForSelection,
                    messageX,
                    messageY);
            MenuSlot worldEntryButton = MenuController.getInstance().addMenuSlot(PanelEnum.GAME.name() +";"+ worldEnum.name(), message, nextItemDirection);
            worldEntryButton.addAction(MenuSlotActionEnum.CHANGE_WORLD);
            worldEntryButton.addAction(MenuSlotActionEnum.CHANGE_PANEL);
            slotY++;
            if (slotY> MAX_ITEM_BY_COLUMN){
                slotY =0;
                slotX++;
                nextItemDirection = DirectionsEnum.RIGHT;
            }else{
                nextItemDirection = DirectionsEnum.DOWN;
            }
        }

        int messageX = AppVariables.tileSize*2;
        int messageY = MAX_ITEM_BY_COLUMN*AppVariables.tileSize*2+yToTop+AppVariables.tileSize*2;
        Message message = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT,
                "Return to title screen...",
                FontEnum.BEDROCK,new Color(71, 234, 196,100), 40,
                true
                , paddingForSelection,
                messageX,
                messageY);
        MenuSlot quitButton = MenuController.getInstance().addMenuSlot(PanelEnum.TITLE_SCREEN.name(), message, DirectionsEnum.DOWN);
        quitButton.addAction(MenuSlotActionEnum.CHANGE_PANEL);

    }


}
