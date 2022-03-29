package fr.game.panel.game;

import fr.game.main.AppVariables;
import fr.game.panel.AbstractPanel;
import fr.game.panel.AbstractUI;

import java.awt.*;

public class CombatUI extends AbstractUI {


    public CombatUI(AbstractPanel currentPanel) {
        super(currentPanel);
    }

    public void drawBattleArena(){
        drawSubWindow(AppVariables.tileSize,
                AppVariables.tileSize,
                AppVariables.SCREEN_WIDTH-AppVariables.tileSize*2,
                AppVariables.SCREEN_HEIGHT-AppVariables.tileSize*2,
                new Color(0, 255, 221,150),
                new Color(0,0,0,255)
                );
    }
}
