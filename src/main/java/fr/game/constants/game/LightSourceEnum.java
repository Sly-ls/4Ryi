package fr.game.constants.game;

import fr.game.main.AppVariables;
import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.rendered.GameObjectEnum;

public enum LightSourceEnum{

    GROUND_TORCH(GameObjectEnum.GROUND_TORCH,
            150, AppVariables.tileSize*4, 500
            , DirectionsEnum.ANY)
    ;

    GameObjectEnum gameObject;

    LightSourceEnum(GameObjectEnum gameObject, int power, int radius, int durability, DirectionsEnum direction) {
        this.gameObject = gameObject;
        this.power = power;
        this.radius = radius;
        this.durability = durability;
        this.direction = direction;
    }

    int power;
    int radius;
    int durability;
    DirectionsEnum direction;

    public GameObjectEnum getGameObject() {
        return gameObject;
    }

    public int getPower() {
        return power;
    }

    public int getRadius() {
        return radius;
    }

    public int getDurability() {
        return durability;
    }

    public DirectionsEnum getDirection() {
        return direction;
    }
}
