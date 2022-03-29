package fr.game.constants.rendered;

import fr.game.main.AppVariables;
import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.game.effect.EffectTypeEnum;
import javafx.util.Pair;

public enum GameEventEnum {
    TELEPORTER_5_5(MessageTypeEnum.DIALOG, "Teleportation",
            EffectTypeEnum.TELEPORT,1,new Pair<>(5*AppVariables.tileSize,5*AppVariables.tileSize),
            null, true,AppVariables.FPS*2,0,
            true,true, DirectionsEnum.ANY,
            AppVariables.tileSize/4, AppVariables.tileSize/4,
            AppVariables.tileSize/2, AppVariables.tileSize/2,
            new String[]{"/events/teleporterEvent.png"})
    , HIDDENT_TRAP(MessageTypeEnum.WORLD_EVENT, "TRAP TRAP TRAP",
            EffectTypeEnum.BONUS_MALUS,-1,null,
            GameValueEnum.HEALTH, true,-1,0,
            false,false, DirectionsEnum.ANY,
            AppVariables.tileSize/4, AppVariables.tileSize/4,
            AppVariables.tileSize/2, AppVariables.tileSize/2,
            new String[]{"/events/trapEvent.png"})
    , CONSUMABLE_HEALING_SPOT(MessageTypeEnum.MESSAGE_TO_PLAYER, "Healing power...",
            EffectTypeEnum.BONUS_MALUS,1,null,
            GameValueEnum.HEALTH, false,AppVariables.FPS/2,3,
            false,true, DirectionsEnum.ANY,
            AppVariables.tileSize/4, AppVariables.tileSize/4,
            AppVariables.tileSize/2, AppVariables.tileSize/2,
            new String[]{"/icons/life/heart_half.png"} )
    ;



    final private MessageTypeEnum messageType;
    final private String message;
    final private EffectTypeEnum effectType;
    final private int power;
    final private Pair<Integer,Integer> targetCoordinate;
    final private GameValueEnum targetAttribute;
    final private boolean resetable;
    final private int inteval;
    final private int quantity;
    final private boolean collision;
    final private boolean visibile;
    final private DirectionsEnum direction;
    final private int solidAreaDefaultX;
    final private int solidAreaDefaultY;
    final private int solidAreaDefaultWitdh;
    final private int solidAreaDefaultHeigth;
    String[] imagePath;


    //CONSTRUCTOR

    GameEventEnum(MessageTypeEnum messageType, String message, EffectTypeEnum effectType, Integer power, Pair<Integer, Integer> targetCoordinate, GameValueEnum targetAttribute, boolean resetable, int inteval, int quantity, boolean collision, boolean visibile, DirectionsEnum direction, int solidAreaDefaultX, int solidAreaDefaultY, int solidAreaDefaultWitdh, int solidAreaDefaultHeigth, String[] imagePath) {
        this.messageType = messageType;
        this.message = message;
        this.effectType = effectType;
        this.power = power;
        this.targetCoordinate = targetCoordinate;
        this.targetAttribute = targetAttribute;
        this.resetable = resetable;
        this.inteval = inteval;
        this.quantity = quantity;
        this.collision = collision;
        this.visibile = visibile;
        this.direction = direction;
        this.solidAreaDefaultX = solidAreaDefaultX;
        this.solidAreaDefaultY = solidAreaDefaultY;
        this.solidAreaDefaultWitdh = solidAreaDefaultWitdh;
        this.solidAreaDefaultHeigth = solidAreaDefaultHeigth;
        this.imagePath = imagePath;
    }

    //GETTER && SETTER
    public String getMessage() {
        return message;
    }
    public int getInteval() {
        return inteval;
    }
    public boolean isCollision() {
        return collision;
    }
    public DirectionsEnum getDirection() {
        return direction;
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
    public String[] getImagePath() {
        return imagePath;
    }
    public boolean isVisibile() {
        return visibile;
    }
    public MessageTypeEnum getMessageType() {
        return messageType;
    }
    public EffectTypeEnum getType() {
        return effectType;
    }
    public Integer getPower() {
        return power;
    }
    public Pair<Integer, Integer> getTargetCoordinate() {
        return targetCoordinate;
    }
    public boolean isResetable() {
        return resetable;
    }
    public int getQuantity() {
        return quantity;
    }
    public EffectTypeEnum getEffectType() {
        return effectType;
    }
    public GameValueEnum getTargetAttribute() {
        return targetAttribute;
    }
}
