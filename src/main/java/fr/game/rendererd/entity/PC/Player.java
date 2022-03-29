package fr.game.rendererd.entity.PC;

import fr.game.main.AppConstants;
import fr.game.main.AppVariables;
import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.constants.game.SkillnEnum;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.game.SlotEnum;
import fr.game.constants.rendered.GameObjectEnum;
import fr.game.constants.rendered.WeaponEnum;
import fr.game.constants.rendered.entity.EntityEnum;
import fr.game.main.utils.ImageUtils;
import fr.game.main.utils.NumberUtils;
import fr.game.mechanics.controller.KeyboardController;
import fr.game.mechanics.controller.Camera;
import fr.game.mechanics.controller.CollisionChecker;
import fr.game.mechanics.core.MessageBox;
import fr.game.panel.game.GameController;
import fr.game.rendererd.AbstractRendered;
import fr.game.rendererd.TileManager;
import fr.game.rendererd.entity.AbstractEntity;
import fr.game.rendererd.object.EquipableObject;
import fr.game.rendererd.object.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Locale;

public class Player extends AbstractEntity {

    //CONSTRUCTOR
    public Player(EntityEnum entityType,
                  int worldX, int worldY) {
        super(entityType, worldX,worldY);
        this.backpack.changeQuantity(GameObjectEnum.YELLOW_KEY, 0);
        this.backpack.changeQuantity(GameObjectEnum.RED_KEY, 0);
        if(AppConstants.DEBUG && AppConstants.DEBUG_INVINCIBLE_MODE) {
            this.attributes.changeQuantity(GameValueEnum.INVINCIBLE, 100000, 100000);
            this.equipedObject.put(SlotEnum.MAIN_HAND,new EquipableObject(WeaponEnum.SWORD,26,26));
        }
    }

    //GRAPHICS METHOD
    private void faceMouseCursor() {
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mouseLocation, GameController.getInstance().getPanel().getApplicationManager().getWindow());
        int cursorX =(int) mouseLocation.getX();
        int cursorY =(int) mouseLocation.getY();
        float xDistance = cursorX - Camera.getInstance().getScreenX(worldX+AppVariables.tileSize/2);
        float yDistance = cursorY - Camera.getInstance().getScreenY(worldY+AppVariables.tileSize);
        this.facingAngle = Math.atan2(yDistance, xDistance);
        setDirectionFromAngle();
    }
    public Point2D getVelocity(){
        double xVelocity = (this.getAttributes(GameValueEnum.SPEED)) * Math.cos(this.facingAngle);
        double yVelocity = (this.getAttributes(GameValueEnum.SPEED)) * Math.sin(this.facingAngle);
        return new Point2D.Double( xVelocity, yVelocity);
    }
    private void goToMouseCursor() {
        Point2D velocity = getVelocity();
        if(!AppConstants.DEBUG ||(AppConstants.DEBUG && AppConstants.DEBUG_TILE_COLLISION) ) {
            CollisionChecker.checkTilesCollision(this, velocity);
        }
        if(!AppConstants.DEBUG ||(AppConstants.DEBUG && AppConstants.DEBUG_OBJECT_COLLISION) ) {
            CollisionChecker.checkGameObjectCollision(this, velocity);
        }
        if(!this.collisionOn ||
                (AppConstants.DEBUG && !AppConstants.DEBUG_TILE_COLLISION && !AppConstants.DEBUG_OBJECT_COLLISION )){
            printDebugUpdate();
            spriteCounter++;
            if(this.spriteCounter > 10) {
                this.spriteNum = NumberUtils.changeValue(1, spriteNum, walkAnimation.get(this.direction).bufferedImage.length - 1, NumberUtils.NumberLoopType.LOOP_PLUS);
                this.spriteCounter=0;
            }
            this.worldX = this.worldX + (int) velocity.getX();
            this.worldY = this.worldY + (int)  velocity.getY();
        }
    }
    public void update(){
        this.collisionOn = false;
        updateTimer();
        checkAlive();


        if(alive) {
            if(this.actionTimer == 0) {

                KeyboardController keyHandler = GameController.getInstance().getPanel().getApplicationManager().getKeyHandler();

                faceMouseCursor();

                if(keyHandler.isUpPressed()
                        || keyHandler.isDownPressed()
                        || keyHandler.isRightPressed()
                        || keyHandler.isLeftPressed()
                        || keyHandler.isSpacePressed() ){

                    if(keyHandler.isSpacePressed() ){
                        useAction(SkillnEnum.WEAPON_THRUST);
                    }else {
                        goToMouseCursor();
                    }
                }
            }else if (this.actionTimer > 0) {
                checkActionAreaHit();
            }
        }else{
            this.attributes.changeQuantity(GameValueEnum.HEALTH,this.type.getMaxLife());
            this.alive=true;
            //FIXME everything need to be done in the instance, some field seems to be filled outside of it
            this.worldX= TileManager.getInstance().getWorldSelected().getPlayerStartingX()*AppVariables.tileSize;
            this.worldY= TileManager.getInstance().getWorldSelected().getPlayerStartingY()*AppVariables.tileSize;
            MessageBox.getInstance().sendMessage(MessageTypeEnum.DIALOG,"MORT",0,0,true);
        }
    }

    public void draw(Graphics2D graphics2D) {

        BufferedImage image = null;
        int temporaryX = this.getWorldX();
        int temporaryY = this.getWorldY();
        if (this.actionTimer > 0) {
            image=this.actionAnimation.get(this.direction).getBufferedImage(spriteNum);
        } else {
            image=this.walkAnimation.get(this.direction).getBufferedImage(spriteNum);
        }
        drawTimerCheck(graphics2D);
        if(this.actionTimer > 0 && (this.direction == DirectionsEnum.LEFT || this.direction == DirectionsEnum.UP)){
            drawWeapon(graphics2D);
        }
        if(AppVariables.BIRD_EYED_PERSPECTIVE) {
            image = ImageUtils.createRotated(image, Math.toDegrees(this.facingAngle));
        }
        graphics2D.drawImage(image,
                Camera.getInstance().getScreenX(temporaryX),
                Camera.getInstance().getScreenY(temporaryY),
                image.getWidth(), image.getHeight(), null);
        if(this.actionTimer > 0 && (this.direction == DirectionsEnum.RIGHT || this.direction == DirectionsEnum.DOWN)){
            drawWeapon(graphics2D);
        }

        drawDebugActionCollision(graphics2D, this.actionInUse);
    }

    private void drawWeapon(Graphics2D graphics2D){
        if (this.actionTimer > 0) {
            EquipableObject weaponEquipped = this.equipedObject.get(SlotEnum.MAIN_HAND);
            int weaponX = this.actionInUse.getAttackArea().x;
            int weaponY = this.actionInUse.getAttackArea().y;
            weaponX -= weaponEquipped.getSolidAreaDefaultX(this.direction);
            weaponY -= weaponEquipped.getSolidAreaDefaultY(this.direction);
            int weaponWidth = this.equipedObject.get(SlotEnum.MAIN_HAND).getWeaponImages().get(this.direction).getBufferedImage().getWidth();
            int weaponHeight = this.equipedObject.get(SlotEnum.MAIN_HAND).getWeaponImages().get(this.direction).getBufferedImage().getHeight();
            BufferedImage image = this.equipedObject.get(SlotEnum.MAIN_HAND).getWeaponImages().get(this.direction).getBufferedImage();
            image = ImageUtils.createRotated(image,this.facingAngle);
            graphics2D.drawImage(image,
                    Camera.getInstance().getScreenX(weaponX),
                    Camera.getInstance().getScreenY(weaponY),
                    weaponWidth, weaponHeight, null);
        }
    }
    public void printDebugUpdate(){
        if(AppConstants.DEBUG && AppConstants.DEBUG_KEYBOARD) {
            System.out.println(new StringBuffer(getClass().getSimpleName().toUpperCase(Locale.ROOT)).append(" ").append(this.direction).append(" is pressed.").toString());
        }
    }
    //ACTION METHODS
    public void speak(AbstractEntity entity){
        entity.speak(this);
    }
    public void use(GameObject gameObject){
        gameObject.use(this);
    }
    public void pickup(GameObject object) {
        if(object instanceof GameObject) {
            this.backpack.changeQuantity(((GameObject) object).getType(), ((GameObject) object).getQuantity());
            GameController.getInstance().removeObjectFromTheWorld((AbstractRendered) object);
        }
    }

    // GETTER AND SETTER
}
