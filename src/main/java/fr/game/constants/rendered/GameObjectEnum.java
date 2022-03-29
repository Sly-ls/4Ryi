package fr.game.constants.rendered;

import fr.game.constants.InInventory;
import fr.game.main.AppVariables;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.game.effect.EffectTypeEnum;
import javafx.util.Pair;

public enum GameObjectEnum implements InInventory {

    CHEST("COFFRE AU TRESOR", new String[]{"/objects/chest.png"},
            false,false,
            true,false, null,
            null,0, null,null,
            0,0, AppVariables.tileSize, AppVariables.tileSize),
    YELLOW_KEY("CLEF JAUNE",new String[]{"/objects/key.png"},
            false,false,
            true,false,null,
            null, 0,null,null,
            0, 0, AppVariables.tileSize, AppVariables.tileSize),
    RED_KEY("CLEF ROUGE",new String[]{"/objects/red_key.png"},
            false, false,
            true,false,null,
            null, 0,null,null,
            0,0, AppVariables.tileSize, AppVariables.tileSize),
    BOOT("BONUS DE VITESSE",new String[]{"/objects/boots.png"},
            false,false,
            false,true,null,
            EffectTypeEnum.BONUS_MALUS, 2,null, GameValueEnum.SPEED,
            0,0, AppVariables.tileSize, AppVariables.tileSize),
    DOOR("PORTE EN BOIS",new String[]{"/objects/door.png"},
            true,false,
            false,true,GameObjectEnum.YELLOW_KEY,
            null, 0,null,null,
            0,0, AppVariables.tileSize, AppVariables.tileSize),
    RED_DOOR("PORTE ROUGE",new String[]{"/objects/red_door.png"},
            true,false,
            false,true,GameObjectEnum.RED_KEY,
            null, 0,null,null,
            0,0, AppVariables.tileSize,
            AppVariables.tileSize),
    HOURGLASS("HourGlass", new String[]{"/icons/hourglass.png"},
            true,false,
            false,false,null,
            null, 0,null,null,
            0,0, AppVariables.tileSize, AppVariables.tileSize),
    GROUND_TORCH("TORCHE", new String[]{"/objects/groundtorch/groundtorch.png"
            ,"/objects/groundtorch/flame/grndtorchflameani3.png"
            ,"/objects/groundtorch/flame/grndtorchflameani4.png"
            ,"/objects/groundtorch/flame/grndtorchflameani5.png"
            ,"/objects/groundtorch/flame/grndtorchflameani6.png"
            ,"/objects/groundtorch/flame/grndtorchflameani7.png"
            ,"/objects/groundtorch/flame/grndtorchflameani8.png"
            ,"/objects/groundtorch/flame/grndtorchflameani9.png"},
            false, true,
            false,false,null,
            null, 0,null,null,
            0,0, AppVariables.tileSize, AppVariables.tileSize),
    HAND_OBJECT("Object équipble dans les mains",
            null,false,false,true,false,null,
            null,0,null,null,
            0,0,AppVariables.tileSize,AppVariables.tileSize);
    ;

    String name;
    String imagesPath[];
    boolean collision;
    boolean animated;
    boolean pickup;
    boolean action;
    GameObjectEnum requiredForSucess;
    EffectTypeEnum effectType;
    int power;
    Pair<Integer,Integer> targetCoordinate;
    GameValueEnum targetAttribute;
    int solidAreaDefaultX;
    int solidAreaDefaultY;
    int solidAreaDefaultWitdh;
    int solidAreaDefaultHeigth;
//CONSTRUCTOR
    GameObjectEnum(String name, String[] path,
                   boolean collision, boolean animated,
                   boolean pickable, boolean acctionable, GameObjectEnum required,
                   EffectTypeEnum effectType, int power, Pair<Integer, Integer> targetCoordinate, GameValueEnum targetAttribute,
                   int solidAreaDefaultX, int solidAreaDefaultY, int solidAreaDefaultWitdh,
                   int solidAreaDefaultHeigth) {
        this.name = name;
        this.imagesPath = path;
        this.collision = collision;
        this.animated = animated;
        this.pickup = pickable;
        this.action = acctionable;
        this.requiredForSucess = required;
        this.effectType = effectType;
        this.power = power;
        this.targetCoordinate = targetCoordinate;
        this.targetAttribute = targetAttribute;
        this.solidAreaDefaultX = solidAreaDefaultX;
        this.solidAreaDefaultY = solidAreaDefaultY;
        this.solidAreaDefaultWitdh = solidAreaDefaultWitdh;
        this.solidAreaDefaultHeigth = solidAreaDefaultHeigth;
    }
//GETTER
    public String getName() {
        return name;
    }

    public String[] getImagesPath() {
        return imagesPath;
    }
    public boolean isCollision() {
        return collision;
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
    public boolean isAnimated() {
        return animated;
    }
    public boolean isPickup() {
        return pickup;
    }
    public boolean isAction() {
        return action;
    }
    public GameObjectEnum getRequiredForSucess() {
        return requiredForSucess;
    }
    public EffectTypeEnum getEffectType() {
        return effectType;
    }
    public int getPower() {
        return power;
    }
    public Pair<Integer, Integer> getTargetCoordinate() {
        return targetCoordinate;
    }
    public GameValueEnum getTargetAttribute() {
        return targetAttribute;
    }

}
