package fr.game.rendererd.entity;

import fr.game.main.AppConstants;
import fr.game.main.AppVariables;
import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.game.SkillnEnum;
import fr.game.constants.game.GameCondition;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.game.SlotEnum;
import fr.game.constants.game.effect.GameValueType;
import fr.game.constants.game.effect.EffectTypeEnum;
import fr.game.constants.rendered.GameObjectEnum;
import fr.game.constants.rendered.entity.EntityEnum;
import fr.game.constants.rendered.entity.RaceCodex;
import fr.game.main.utils.NumberUtils;
import fr.game.mechanics.controller.Camera;
import fr.game.mechanics.controller.CollisionChecker;
import fr.game.mechanics.game.combat.Skill;
import fr.game.mechanics.game.combat.GameEffect;
import fr.game.mechanics.core.Inventory;
import fr.game.mechanics.core.ScaledImage;
import fr.game.panel.game.GameController;
import fr.game.rendererd.AbstractRendered;
import fr.game.rendererd.entity.PC.Player;
import fr.game.rendererd.event.GameEvent;
import fr.game.rendererd.object.EquipableObject;
import fr.game.rendererd.object.GameObject;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;

public abstract class AbstractEntity extends AbstractRendered {

    protected EntityEnum type;
    protected String name;
    //Stats
    protected int level=1;
    RaceCodex race;
    protected Inventory<GameValueEnum> attributes;
    protected Inventory<GameObjectEnum> backpack;
    protected Map<SlotEnum, EquipableObject > equipedObject;
    protected boolean alive = true;
    //Action => To entity only ? event to Action to; may be we can simplify
    protected int actionTimer = 0;
    protected int loopAnimation =0;
    protected Map<DirectionsEnum, ScaledImage> actionAnimation;
    protected Skill actionInUse = null;

    //animation stat
    protected Map<DirectionsEnum,ScaledImage> walkAnimation;
    protected boolean collisionOn = false;

    //CONSTRUCTOR
    public AbstractEntity(EntityEnum entityType, int worldX, int worldY) {
        super();
        this.attributes = new Inventory<GameValueEnum>();
        this.backpack = new Inventory<GameObjectEnum>();
        this.equipedObject = new HashMap<SlotEnum, EquipableObject>();
        this.type = entityType;
        this.name = entityType.getName();
        this.solidAreaDefaultX = entityType.getSolidAreaDefaultX();
        this.solidAreaDefaultY = entityType.getSolidAreaDefaultY();
        this.solidAreaDefaultWitdh = entityType.getSolidAreaDefaultWitdh();
        this.solidAreaDefaultHeigth  = entityType.getSolidAreaDefaultHeigth();
        this.direction  = entityType.getDirection();
        this.collision = entityType.isCollision();
        this.solidArea = new Rectangle(solidAreaDefaultX, solidAreaDefaultY,
                solidAreaDefaultWitdh, solidAreaDefaultHeigth);
        this.walkAnimation = entityType.getWalkingAnimation();

        this.worldX = worldX * AppVariables.tileSize;
        this.worldY = worldY * AppVariables.tileSize;
        if(this.attributes.getInventory(GameValueEnum.SPEED) == 0) {
            this.attributes.changeQuantity(GameValueEnum.SPEED, entityType.getSpeed(), GameValueEnum.SPEED.getMaxValue());
        }
        if(this.attributes.getInventory(GameValueEnum.HEALTH)== 0) {
            this.attributes.changeQuantity(GameValueEnum.HEALTH, entityType.getMaxLife(), entityType.getMaxLife());
        }
        this.attributes.changeQuantity(GameValueEnum.LEVEL, 1, GameValueEnum.LEVEL.getMaxValue());
//        this.attributes.changeQuantity(GameValueEnum.STRENGHT, 1, GameValueEnum.STRENGHT.getMaxValue());
//        this.attributes.changeQuantity(GameValueEnum.DEXTERITY, 1, GameValueEnum.DEXTERITY.getMaxValue());

    }

    //GRAPHICS METHOD
    public abstract void update();
    public abstract void draw(Graphics2D graphics2D);
    //DEBUG METHOD
    public void drawDebugActionCollision(Graphics2D graphics2D, Skill action) {
        if(action != null){
            Rectangle attackArea = this.actionInUse.getAttackArea();
            if(AppConstants.DEBUG && AppConstants.DEBUG_ACTION_COLLISION) {
                graphics2D.setColor(new Color(255, 0, 255, 150));
                graphics2D.fillRect(Camera.getInstance().getScreenX(attackArea.x),
                        Camera.getInstance().getScreenY(attackArea.y),
                        attackArea.width,
                        attackArea.height);
            }
        }
    }
    //CLASS METHOD
    public abstract void pickup(GameObject object);
    public void checkActionAreaHit(){

        if(this.actionTimer > 0) {
            //FIXME not how it should workd
            // we should rotate the original attack depending on the direction
            EquipableObject weaponEquipped = this.equipedObject.get(SlotEnum.MAIN_HAND);
            this.actionInUse.getAttackArea().x = this.worldX + weaponEquipped.getSolidAreaDefaultX(this.direction);
            this.actionInUse.getAttackArea().y = this.worldY + weaponEquipped.getSolidAreaDefaultY(this.direction);
            this.actionInUse.getAttackArea().width = weaponEquipped.getSolidAreaDefaultWitdh(this.direction);
            this.actionInUse.getAttackArea().height = weaponEquipped.getSolidAreaDefaultHeigth(this.direction);
            //placing the action frame on the side of the solid area of the entity
            //and apply the action area move on the actionFrame
            switch (this.direction){
                case UP:
                    this.actionInUse.getAttackArea().y -= this.actionInUse.getAttackArea().height;
                    this.actionInUse.getAttackArea().x += this.actionInUse.getActionType().getxPerImage()[spriteNum];
                    this.actionInUse.getAttackArea().y -= this.actionInUse.getActionType().getyPerImage()[spriteNum];
                    break;
                case DOWN:
                    this.actionInUse.getAttackArea().y += this.solidArea.height;
                    this.actionInUse.getAttackArea().x -= this.actionInUse.getActionType().getxPerImage()[spriteNum];
                    this.actionInUse.getAttackArea().y += this.actionInUse.getActionType().getyPerImage()[spriteNum];
                    break;
                case LEFT:
                    this.actionInUse.getAttackArea().x -= this.actionInUse.getAttackArea().width;
                    this.actionInUse.getAttackArea().x -= this.actionInUse.getActionType().getyPerImage()[spriteNum];
                    this.actionInUse.getAttackArea().y += this.actionInUse.getActionType().getxPerImage()[spriteNum];
                    break;
                case RIGHT:
                    this.actionInUse.getAttackArea().x += this.solidArea.width;
                    this.actionInUse.getAttackArea().x += this.actionInUse.getActionType().getyPerImage()[spriteNum];
                    this.actionInUse.getAttackArea().y -= this.actionInUse.getActionType().getxPerImage()[spriteNum];
                    break;
                default:
                    break;
            }
            if(this.actionInUse != null && this.actionInUse.getActionType().getImpactPerImage()[spriteNum]) {
                CollisionChecker.checkActionCollision(this.actionInUse);
            }
        }
    }
    public void receiveEffects(Skill attack) {

        StringBuffer sbDebug = null;
        if(AppConstants.DEBUG && AppConstants.DEBUG_ACTION){
            sbDebug = new StringBuffer(getClass().getSimpleName().toUpperCase(Locale.ROOT)).
                    append(" ").append(this.getName()).append( " receive ");
        }
        if(this.attributes.getInventory(GameValueEnum.INVINCIBLE)<1) {
            for (GameEffect effect : attack.getEffects().get(GameCondition.ON_HIT)){
                switch (effect.getType()){
                    case BONUS_MALUS:
                        this.attributes.changeQuantity(effect.getTargetAttribute(), effect.getPower());
                        if(AppConstants.DEBUG && AppConstants.DEBUG_ACTION){
                            sbDebug.append(effect.getPower()).append(" ").append(effect.getTargetAttribute()).append(" point.");
                        }
                        break;
                    case TELEPORT:
                        this.worldX+=effect.getTargetCoordinate().getKey();
                        this.worldY+=effect.getTargetCoordinate().getValue();
                        if(AppConstants.DEBUG && AppConstants.DEBUG_ACTION){
                            sbDebug.append(" teleportation to ").append(effect.getTargetCoordinate().getKey()).append(",").append(effect.getTargetCoordinate().getValue());
                        }
                        break;
                    default:
                        break;
                }
            }
            if(AppConstants.DEBUG && AppConstants.DEBUG_ACTION){
                System.out.println(sbDebug);
            }
        }else{
            if(AppConstants.DEBUG && AppConstants.DEBUG_ACTION){
                sbDebug.append(attack.getActionType().getName()).append(" but is invincible for " + this.attributes.getInventory(GameValueEnum.INVINCIBLE));
                System.out.println(sbDebug);
            }
        }
    }
    protected void updateTimer(){
        this.collisionOn=false;
        if(this.quietTime > 0) {
            this.quietTime--;
        }
        if(this.attributes.getInventory(GameValueEnum.INVINCIBLE) > 0) {
            this.attributes.changeQuantity(GameValueEnum.INVINCIBLE, -1);
        }
        if(this.actionTimer > 0) {
            if(this.actionInUse.getActionType().getImagesPath() != null) {
                int currentStep = this.actionInUse.getActionType().getFrame() - (this.actionInUse.getActionType().getFramePerImage()[spriteNum]);
                if (this.actionTimer < currentStep) {
                    spriteNum = NumberUtils.changeValue(1, spriteNum, this.actionInUse.getActionType().getFramePerImage().length - 1, NumberUtils.NumberLoopType.NO_LOOP);
                }
            }
            this.actionTimer--;
        }else  if(this.actionTimer == 0 && this.actionInUse != null) {
            this.actionInUse.getAttackArea().width =0;
            this.actionInUse.getAttackArea().height =0;
            this.actionInUse = null;
        }
    }
    protected void drawTimerCheck(Graphics2D graphics2D){
        //FIXME draw icon for all temporary attributes
        // do the same for cupdate timer with != PERMANENT
        Set<GameValueEnum> iconToDraw = null;
        for(Map.Entry<GameValueEnum, fr.game.mechanics.core.GameValue> entry : this.attributes.getInventory()){
            if(entry.getKey().getTypeOfStat() == GameValueType.TIMED_DOWN
                    && this.attributes.getInventory(entry.getKey()) > 0){
                if(iconToDraw == null){
                    iconToDraw = new HashSet<>();
                }
                iconToDraw.add(entry.getKey());
            }
        }
        if(iconToDraw != null) {
            for (GameValueEnum statToDraw : iconToDraw) {
                GameController.getInstance().getPanel().getUiPanel().drawIcon(statToDraw,
                        Camera.getInstance().getScreenX(this.worldX),
                        Camera.getInstance().getScreenY(this.worldY) - AppVariables.tileSize/2
                );
            }
        }
    }
    protected void checkAlive() {
        if (this.alive && this.attributes.getInventory(GameValueEnum.HEALTH) <= 0) {
            this.alive=false;
            this.attributes.changeQuantity(GameValueEnum.INVINCIBLE,AppVariables.corpseStayFor);
        }
        if(AppConstants.DEBUG){
            StringBuffer sbDebug = new StringBuffer(this.getClass().getSimpleName().toUpperCase(Locale.ROOT));
            sbDebug.append(" is dead");
        }
    }
    protected void useAction(SkillnEnum action){
        if(AppConstants.DEBUG && AppConstants.DEBUG_ACTION){
            StringBuffer sbDebug = new StringBuffer(getClass().getSimpleName().toUpperCase(Locale.ROOT));
            sbDebug.append(" ").append(" use ").append(action.getName());
            System.out.println(sbDebug);
        }
        this.actionTimer = action.getFrame();
        this.actionAnimation = new HashMap<>();
        this.actionAnimation.put(DirectionsEnum.UP,new ScaledImage(action.getImagesPath()));
        this.spriteNum = 0;
        this.loopAnimation =0;

        EquipableObject equippedWeapon = this.equipedObject.get(SlotEnum.MAIN_HAND);
        int weaponDamage = -1 * equippedWeapon.getAttackScale() * this.attributes.getInventory(equippedWeapon.getMainAttribute());
        Skill actionEffects =  new Skill(action,this,
                new ArrayList(){ {add(new GameEffect(EffectTypeEnum.BONUS_MALUS,weaponDamage, GameValueEnum.HEALTH, GameCondition.ON_HIT));}
                    {add(new GameEffect(EffectTypeEnum.BONUS_MALUS,action.getFrame(), GameValueEnum.INVINCIBLE, GameCondition.ON_HIT));} } );
        this.actionInUse = actionEffects;
    }
    protected void moveAccordingToDirection(){
        if(!AppConstants.DEBUG ||(AppConstants.DEBUG && AppConstants.DEBUG_TILE_COLLISION) ) {
            CollisionChecker.checkTilesCollision(this);
        }
        if(!AppConstants.DEBUG ||(AppConstants.DEBUG && AppConstants.DEBUG_OBJECT_COLLISION) ) {
            CollisionChecker.checkGameObjectCollision(this);
        }

        if(!this.collisionOn){
            this.spriteCounter++;

            if(this.spriteCounter > 10) {
                this.spriteNum = NumberUtils.changeValue(1, spriteNum, walkAnimation.get(this.direction).bufferedImage.length - 1, NumberUtils.NumberLoopType.LOOP_STOP);
            }
            switch (this.direction){
                case UP:
                    this.worldY = NumberUtils.changeValue(-this.getAttributes(GameValueEnum.SPEED), this.worldY, AppVariables.maxWorldY, AppVariables.goesAroundScreen);
                    break;
                case DOWN:
                    this.worldY = NumberUtils.changeValue(this.getAttributes(GameValueEnum.SPEED), this.worldY, AppVariables.maxWorldY, AppVariables.goesAroundScreen);
                    break;
                case LEFT:
                    this.worldX = NumberUtils.changeValue(-this.getAttributes(GameValueEnum.SPEED), this.worldX, AppVariables.maxWorldX, AppVariables.goesAroundScreen);
                    break;
                case RIGHT:
                    this.worldX = NumberUtils.changeValue(this.getAttributes(GameValueEnum.SPEED), this.worldX, AppVariables.maxWorldX, AppVariables.goesAroundScreen);
                    break;
                default:
                    break;
            }
            if(this.spriteCounter > 10){
                this.spriteCounter=0;
            }
        }
    }
    public void setDirectionFromAngle(){
        double angle = Math.toDegrees(this.facingAngle);
        if(angle >= -45 && angle <=45){
            this.direction = DirectionsEnum.RIGHT;
        }
        if(angle < -45 && angle >= -135){
            this.direction = DirectionsEnum.UP;
        }
        if(angle < -135 || angle > 135){
            this.direction = DirectionsEnum.LEFT;
        }
        if(angle > 45
                && angle <= 135){
            this.direction = DirectionsEnum.DOWN;
        }
    }

    public Point2D getVelocity(){
        int speed = this.attributes.getInventory(GameValueEnum.SPEED);
        Point2D velocity = new Point2D.Double(0,0);
        switch (direction) {
            case UP:
                velocity.setLocation(0,-speed);
                break;
            case DOWN:
                velocity.setLocation(0,speed);
                break;
            case LEFT:
                velocity.setLocation(-speed,0);
                break;
            case RIGHT:
                velocity.setLocation(speed,0);
                break;
            default:
                break;
        }
        return velocity;
    }
    //INTERFACE METHOD
    public void speak(AbstractEntity entity){
        if(AppConstants.DEBUG && AppConstants.DEBUG_SPEAK){
            StringBuffer sbDebug = new StringBuffer(getClass().getSimpleName().toUpperCase(Locale.ROOT));
            sbDebug.append(" ").append(this.getName()).append(" at ")
                    .append(this.getWorldX()).append(",").append(this.getWorldY())
                    .append(" speak with ").append(entity.getName()).append(" at ")
                    .append(entity.getWorldX()).append(",").append(entity.getWorldY());
            System.out.println(sbDebug);
        }
        switch (entity.getDirection()){
            case UP:
                this.direction = DirectionsEnum.DOWN;
                break;
            case DOWN:
                this.direction = DirectionsEnum.UP;
                break;
            case LEFT:
                this.direction = DirectionsEnum.RIGHT;
                break;
            case RIGHT:
                this.direction = DirectionsEnum.LEFT;
                break;
            default:
                break;
        }
    }
    public void use(GameObject gameObject){

        if(this.quietTime == 0 || this instanceof Player) {
            if(AppConstants.DEBUG) {
                System.out.println(this.getClass() + " " + this.name + " at " + this.getWorldX() + "," + this.getWorldY() + " uses " + gameObject.getName() + " at " + gameObject.getWorldX() + "," + gameObject.getWorldY());
            }
            switch (gameObject.getDirection()){
                case UP:
                    this.direction = DirectionsEnum.DOWN;
                    break;
                case DOWN:
                    this.direction = DirectionsEnum.UP;
                    break;
                case LEFT:
                    this.direction = DirectionsEnum.RIGHT;
                    break;
                case RIGHT:
                    this.direction = DirectionsEnum.LEFT;
                    break;
                default:
                    break;
            }
        }
        this.quietTime = AppVariables.messageStayFor;
    }
    public void trigger(GameEvent newEvent){
        newEvent.use(this);
    }

    // GETTER AND SETTER
    public Rectangle getSolidArea() {
        return solidArea;
    }
    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }
    public boolean isCollisionOn() {
        return collisionOn;
    }
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }
    public DirectionsEnum getDirection() {
        return direction;
    }
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }
    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }
    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }
    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isAlive() {
        return alive;
    }
    public int getAttributes(GameValueEnum valueType) {
        return attributes.getInventory(valueType);
    }
    public Inventory<GameValueEnum> getAttributes() {
        return attributes;
    }
    public Inventory<GameObjectEnum> getBackpack() {
        return backpack;
    }
    public int getMaxAttributes(GameValueEnum valueType) {
        return attributes.getMaxInventory(valueType);
    }
    public int getBackpack(GameObjectEnum valueType) {
        return backpack.getInventory(valueType);
    }
    public RaceCodex getRace() {
        return race;
    }
    public void setRace(RaceCodex race) {
        this.race = race;
    }
}