package fr.game.constants.rendered.entity;
import fr.game.main.AppVariables;
import fr.game.constants.core.DirectionsEnum;
import fr.game.mechanics.core.ScaledImage;

import java.util.HashMap;
import java.util.Map;

public enum EnemyEnum {

    OLD_MAN("OLD_MAN", 1, 6,
            new HashMap<DirectionsEnum,ScaledImage>(){
                {put(DirectionsEnum.UP, new ScaledImage(new String[]{"/characters/old_man/oldman_up_1.png",
                        "/characters/old_man/oldman_up_2.png"}));}
                {put(DirectionsEnum.DOWN, new ScaledImage(new String[]{"/characters/old_man/oldman_down_1.png",
                        "/characters/old_man/oldman_down_2.png"}));}
                {put(DirectionsEnum.LEFT, new ScaledImage(new String[]{"/characters/old_man/oldman_left_1.png",
                        "/characters/old_man/oldman_left_2.png"}));}
                {put(DirectionsEnum.RIGHT, new ScaledImage(new String[]{"/characters/old_man/oldman_right_1.png",
                        "/characters/old_man/oldman_right_2.png"}));}
            },
            true, DirectionsEnum.LEFT,
            AppVariables.tileSize/4, AppVariables.tileSize/4, AppVariables.tileSize/2, AppVariables.tileSize/2)
    ,BLUE_BOY("BLUE_BOY", 3,6,
            new HashMap<DirectionsEnum,ScaledImage>(){
                {put(DirectionsEnum.UP, new ScaledImage(new String[]{"/characters/blue_boy/boy_up_1.png",
                        "/characters/blue_boy/boy_up_2.png"}));}
                {put(DirectionsEnum.DOWN, new ScaledImage(new String[]{"/characters/blue_boy/boy_down_1.png",
                        "/characters/blue_boy/boy_down_2.png"}));}
                {put(DirectionsEnum.LEFT, new ScaledImage(new String[]{"/characters/blue_boy/boy_left_1.png",
                        "/characters/blue_boy/boy_left_2.png"}));}
                {put(DirectionsEnum.RIGHT, new ScaledImage(new String[]{"/characters/blue_boy/boy_right_1.png",
                        "/characters/blue_boy/boy_right_2.png"}));}
            },
            true, DirectionsEnum.DOWN,
            AppVariables.tileSize/4, AppVariables.tileSize/4, AppVariables.tileSize/2, AppVariables.tileSize/2)
    ,GREEN_SLIME("GREEN_SLIME", 1,4,
            new HashMap<DirectionsEnum,ScaledImage>(){
                {put(DirectionsEnum.UP, new ScaledImage(new String[]{"/characters/monster/greenslime_down_1.png",
                        "/characters/monster/greenslime_down_2.png"}));}
                {put(DirectionsEnum.DOWN, new ScaledImage(new String[]{"/characters/monster/greenslime_down_1.png",
                        "/characters/monster/greenslime_down_2.png"}));}
                {put(DirectionsEnum.LEFT, new ScaledImage(new String[]{"/characters/monster/greenslime_down_1.png",
                        "/characters/monster/greenslime_down_2.png"}));}
                {put(DirectionsEnum.RIGHT, new ScaledImage(new String[]{"/characters/monster/greenslime_down_1.png",
                        "/characters/monster/greenslime_down_2.png"}));}
            },
            true, DirectionsEnum.DOWN,
            AppVariables.tileSize/4, AppVariables.tileSize/4, AppVariables.tileSize/2, AppVariables.tileSize/2)
;

    final private String name;
    final private int speed;
    final private int maxLife;
    final private Map<DirectionsEnum,ScaledImage> walkingAnimation;
    final private int solidAreaDefaultX;
    final private int solidAreaDefaultY;
    final private int solidAreaDefaultWitdh;
    final private int solidAreaDefaultHeigth;
    final private boolean collision;
    DirectionsEnum direction;

    //CONSTRUCTOR
    EnemyEnum(String name, int speed, int maxLife,
              Map<DirectionsEnum, ScaledImage> walkingAnimation,
              boolean collision, DirectionsEnum direction,
              int solidAreaDefaultX, int solidAreaDefaultY, int solidAreaDefaultWitdh, int solidAreaDefaultHeigth
    ) {
        this.name = name;
        this.speed = speed;
        this.maxLife = maxLife;
        this.walkingAnimation = walkingAnimation;
        this.collision = collision;
        this.solidAreaDefaultX = solidAreaDefaultX;
        this.solidAreaDefaultY = solidAreaDefaultY;
        this.solidAreaDefaultWitdh = solidAreaDefaultWitdh;
        this.solidAreaDefaultHeigth = solidAreaDefaultHeigth;
        this.direction = direction;
    }

    //GETTER ONLY
    public String getName() {
        return name;
    }
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }
    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }
    public int getSolidAreaDefaultWitdh() {
        return solidAreaDefaultWitdh;
    }
    public int getSolidAreaDefaultHeigth() {
        return solidAreaDefaultHeigth;
    }
    public boolean isCollision() {
        return collision;
    }
    public DirectionsEnum getDirection() {
        return direction;
    }
    public int getSpeed() {
        return speed;
    }
    public int getMaxLife() {
        return maxLife;
    }
    public Map<DirectionsEnum, ScaledImage> getWalkingAnimation() {
        return walkingAnimation;
    }
}