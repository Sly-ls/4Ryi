package fr.game.rendererd.event;

import fr.game.main.AppConstants;
import fr.game.main.AppVariables;
import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.core.GameUnitEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.constants.game.GameCondition;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.game.effect.EffectTypeEnum;
import fr.game.constants.rendered.GameEventEnum;
import fr.game.mechanics.controller.Camera;
import fr.game.mechanics.game.combat.Skill;
import fr.game.mechanics.game.combat.GameEffect;
import fr.game.mechanics.core.MessageBox;
import fr.game.mechanics.core.ScaledImage;
import fr.game.panel.game.GameController;
import fr.game.rendererd.AbstractRendered;
import fr.game.rendererd.entity.AbstractEntity;
import fr.game.rendererd.entity.NPC.AbstractNPC;
import javafx.util.Pair;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Locale;

//FIXME i want to try to define the effect in a method in the enum ('Entity e) -> (entityReceive new Action (...)),
// i should may be try with this as this is the easiest action to debug
public class GameEvent extends AbstractRendered {

    GameEventEnum eventType;
    String name;
    boolean triggered = false;
    boolean reset;
    int interval;
    boolean visible;
    ScaledImage image;
    AbstractEntity target;

    EffectTypeEnum effectType;
    Integer power;
    Pair<Integer,Integer> targetCoordinate;
    GameValueEnum targetAttribute;

    int quantity;

    public GameEvent(GameEventEnum eventType,
                     int worldX, int worldY) {
        super();
        this.eventType = eventType;
        this.name = eventType.name();
        this.solidAreaDefaultX = eventType.getSolidAreaDefaultX();
        this.solidAreaDefaultY = eventType.getSolidAreaDefaultY();
        this.solidAreaDefaultWitdh = eventType.getSolidAreaDefaultWitdh();
        this.solidAreaDefaultHeigth  = eventType.getSolidAreaDefaultHeigth();
        this.direction  = eventType.getDirection();
        this.collision = eventType.isCollision();
        this.visible = eventType.isVisibile();
        this.reset = eventType.isResetable();
        this.quantity = eventType.getQuantity();
        this.effectType = eventType.getType();
        this.power = eventType.getPower();
        this.targetCoordinate = eventType.getTargetCoordinate();
        this.targetAttribute = eventType.getTargetAttribute();
        //if interval > -1, it's reset by distance, else by time
        if(interval < 0) {
            this.interval = eventType.getInteval();
        }else{
            this.interval=0;
        }

        if(eventType.getImagePath() != null){
            image = new ScaledImage(eventType.getImagePath());
        }

        this.solidArea = new Rectangle(solidAreaDefaultX, solidAreaDefaultY, solidAreaDefaultWitdh, solidAreaDefaultHeigth);

        this.worldX = worldX * AppVariables.tileSize;
        this.worldY = worldY * AppVariables.tileSize;
    }

    //CLASS METHOD
    public void reload(){
        if(target != null) {

            int distance = -1;
            int xDistance = Math.abs(this.getWorldX() - this.target.getWorldX());
            int yDistance = Math.abs(this.getWorldY() - this.target.getWorldY());
            distance = Math.max(xDistance, yDistance);
            if(distance > AppVariables.tileSize && interval < 1){
                this.target = null;
            }
        }
        if(isTriggered() && (interval == 0 || (interval < 0 && target == null))){
            if (!reset){
                quantity -=power;
                //FIXME to show that it's depleting/decay, progress througth the animation.
                // To be tested with the healing consumable spot
//                if (this.eventType.getImagePath().length-1 > 0
//                && spriteNum > (quantity/this.eventType.getImagePath().length-1)){
//                    spriteNum++;
//                }
            }
            if(reset || quantity > 0) {
                this.visible = eventType.isVisibile();
                this.triggered = false;
                this.interval = eventType.getInteval();
                printlReloadDebug();
            }
        } else if (interval>0){
            this.interval--;
        }
    }
    private void activateEvent(){
        MessageTypeEnum messageTypeToSend = eventType.getMessageType();
        boolean isNPC = this.target instanceof AbstractNPC;
        if (messageTypeToSend == MessageTypeEnum.DIALOG && isNPC){
            messageTypeToSend = MessageTypeEnum.WORLD_EVENT;
        }
        MessageBox.getInstance().sendMessage(messageTypeToSend, eventType.getMessage(),
                this.worldX,
                this.worldY,
                true);
        GameEffect eventEffect = new GameEffect(this.effectType,this.power,this.targetCoordinate, GameCondition.ON_HIT);
        eventEffect.setTargetAttribute(this.targetAttribute);
        this.target.receiveEffects(new Skill(this,eventEffect));
        if(!visible){
            visible=true;
        }
        this.interval = eventType.getInteval();
        printlTriggerDebug();
    }
    public void use(AbstractEntity entity) {
        this.target=entity;
        if(AppConstants.DEBUG) {
            System.out.println(getClass() + " " + this.eventType.name() + " is used by " + entity.getName());
        }
    }

    //GRAPHICS METHOD
    public void update() {
        if(interval < 1 && target != null) {
            target.getSolidArea().x = target.getWorldX() + target.getSolidArea().x;
            target.getSolidArea().y = target.getWorldY() + target.getSolidArea().y;
            this.getSolidArea().x = this.getWorldX() + this.getSolidArea().x;
            this.getSolidArea().y = this.getWorldY() + this.getSolidArea().y;

            if (collision && target.isCollision()){
                Point2D velocity;
                velocity = target.getVelocity();
                target.getSolidArea().y += velocity.getY();
                target.getSolidArea().x += velocity.getX();
            }
            if (target.getSolidArea().intersects(this.getSolidArea())
                    && (target.getDirection() == this.getDirection() || this.getDirection() == DirectionsEnum.ANY)
                    && this.triggered == false) {
                this.triggered= true;
                activateEvent();
            }
            target.getSolidArea().x = target.getSolidAreaDefaultY();
            target.getSolidArea().y = target.getSolidAreaDefaultY();
            this.solidArea.x = this.solidAreaDefaultX;
            this.solidArea.y = this.solidAreaDefaultY;
        }

        reload();
    }
    public void draw(Graphics2D graphics2D) {
        Color subWindowColor  = new Color(46, 194, 165, 50);
        Color subWindowFontColor  = new Color(255,255,255, 255);
        if(isTriggered()) {
            GameController.getInstance().getPanel().getUiPanel().drawSubWindow(
                    Camera.getInstance().getScreenX(this.worldX)  - AppVariables.tileSize/ AppVariables.originalTileSize,
                    Camera.getInstance().getScreenY(this.worldY)  - AppVariables.tileSize/ AppVariables.originalTileSize,
                    AppVariables.tileSize + 2*(AppVariables.tileSize/ AppVariables.originalTileSize),
                    AppVariables.tileSize + 2*(AppVariables.tileSize/ AppVariables.originalTileSize),
                    new Color(46, 194, 165, 50),
                    new Color(255,255,255, 255)

            );
        }
        if(visible && image != null){
            graphics2D.drawImage(image.getBufferedImage(spriteNum), Camera.getInstance().getScreenX(this.worldX), Camera.getInstance().getScreenY(this.worldY),null);
        }
        drawDebug(graphics2D);
    }
    public void printlTriggerDebug(){

        if (AppConstants.DEBUG) {
            System.out.println(this.target.getName() +" triggered " + this.eventType.name() + " at "
                    + Camera.getInstance().getScreenX(this.worldX + AppVariables.SCREEN_WIDTH/2, GameUnitEnum.TILE)
                    +","
                    + Camera.getInstance().getScreenY(this.worldY + AppVariables.SCREEN_WIDTH/2, GameUnitEnum.TILE)
                    +" !"
            );
        }
    }public void printlReloadDebug(){

        if (AppConstants.DEBUG) {
            StringBuffer sbDebug = new StringBuffer(getClass().getSimpleName().toUpperCase(Locale.ROOT)).append(" ");
            sbDebug.append(this.name).append(" reload");
            if (!reset){
                sbDebug.append(", ").append(this.quantity).append(" left");
            }
            sbDebug.append(".");
            System.out.println(sbDebug);
        }
    }
    //GETTER && SETTER
    public String getName() {
        return name;
    }
    public boolean isTriggered() {
        return triggered;
    }
    public void setTriggered(boolean eventDone) {
        this.triggered = eventDone;
    }
    public GameEventEnum getEventType() {
        return eventType;
    }
    public boolean isReset() {
        return reset;
    }
    public int isInterval() {
        return interval;
    }
    public boolean isVisible() {
        return visible;
    }
    public ScaledImage getImage() {
        return image;
    }
    public AbstractEntity getTarget() {
        return target;
    }
    public EffectTypeEnum getEffectType() {
        return effectType;
    }
    public Integer getPower() {
        return power;
    }
    public Pair<Integer, Integer> getTargetCoordinate() {
        return targetCoordinate;
    }
    public int getQuantity() {
        return quantity;
    }
}
