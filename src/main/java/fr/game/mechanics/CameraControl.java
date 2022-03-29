package fr.game.mechanics;

import fr.game.constants.core.GameUnitEnum;
import fr.game.rendererd.AbstractRendered;
import fr.game.rendererd.entity.PC.Player;

public interface CameraControl {

    boolean isDisplayable(AbstractRendered toCheckForVisibility);

    boolean isDisplayable(int x, int y);

    int getScreenY(int worldY);
    int getScreenX(int worldX);

    void centerCameraOnPlayer(Player player);

    int getScreenY(int worldY, GameUnitEnum unit);
    int getScreenX(int worldX, GameUnitEnum unit);
    int getScreenXTileUnit(int worldX);
    int getScreenYTileUnit(int worldY);
    int getCameraMaxWorldX(GameUnitEnum unit);
    int getCameraMaxWorlY(GameUnitEnum unit);

    int getCameraWorldX(GameUnitEnum unit);

    int getCameraWorldY(GameUnitEnum unit);
}
