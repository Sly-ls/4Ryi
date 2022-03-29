package fr.game.panel.menu.creation;

import fr.game.main.AppVariables;
import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.core.FontEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.constants.panel.PanelEnum;
import fr.game.mechanics.core.Message;
import fr.game.mechanics.core.MessageBox;
import fr.game.panel.AbstractUI;
import fr.game.panel.menu.MenuController;
import fr.game.panel.menu.core.MenuSlot;
import fr.game.panel.menu.core.MenuSlotActionEnum;

import java.awt.*;

public class EntityCreationUI extends AbstractUI {
    final int paddingForSelection = 10;
    EntityCreationPanel currentPanel;

    public EntityCreationUI(EntityCreationPanel currentPanel) {
        super(currentPanel);
        this.currentPanel = currentPanel;
    }

    public void drawSubPanel(Graphics2D graphics2D) {

        this.currentPanel.subPanelInTheScreen.get("STAT").draw(graphics2D);
        this.currentPanel.subPanelInTheScreen.get("CLASS").draw(graphics2D);

    }

    public void drawValidateButton(Graphics2D graphics2D) {

        Message worldMessage = MessageBox.getInstance().sendMessage(MessageTypeEnum.MENU_TEXT,"Valider la création",
                FontEnum.BEDROCK,new Color(71, 234, 196,100), 60,
                true, paddingForSelection,
                currentPanel.marginLeft,
                AppVariables.SCREEN_HEIGHT-currentPanel.marginTop);
        MenuSlot creationValidationButton = MenuController.getInstance().addMenuSlot(PanelEnum.WORLD_SELECTION.name(), worldMessage, DirectionsEnum.DOWN);
        creationValidationButton.addAction(MenuSlotActionEnum.VALIDATE_CREATION);
        creationValidationButton.addAction(MenuSlotActionEnum.CHANGE_PANEL);
    }
}
