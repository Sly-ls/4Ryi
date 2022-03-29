package fr.game.rendererd.entity.NPC;

import fr.game.main.AppConstants;
import fr.game.main.AppVariables;
import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.core.FontEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.rendered.entity.EntityEnum;
import fr.game.main.utils.NumberUtils;
import fr.game.mechanics.controller.Camera;
import fr.game.mechanics.core.MessageBox;
import fr.game.mechanics.core.Randomizer;
import fr.game.rendererd.entity.AbstractEntity;
import fr.game.rendererd.entity.PC.Player;
import fr.game.rendererd.object.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Locale;

public abstract class AbstractNPC extends AbstractEntity {

    //NPC stat
    protected String dialogArray[];
    protected int dialogCounter = 0;
    protected int actionLockCounter = 0;

    public AbstractNPC(EntityEnum objectTypeEnum,
                       int worldX, int worldY) {
        super(objectTypeEnum, worldX, worldY);

        setDialog();
        setAction();
    }

    //GRAPHICS METHOD
    @Override
    public void update() {
        this.collisionOn = false;
        updateTimer();
        checkAlive();
        if(alive) {
            setAction();
            moveAccordingToDirection();
            checkActionAreaHit();
        }
    }
    public void draw(Graphics2D graphics2D) {

        //health bar
        if(alive && this.attributes.getInventory(GameValueEnum.HEALTH) > 0
                && this.attributes.getInventory(GameValueEnum.HEALTH)
                != this.attributes.getMaxInventory(GameValueEnum.HEALTH)){

            int x = Camera.getInstance().getScreenX(this.worldX);
            int y = Camera.getInstance().getScreenY(this.worldY);
            int barLength = AppVariables.tileSize/2 + AppVariables.tileSize/4;
            int barHeight = AppVariables.tileSize/8;
            double oneScale =(double) barLength/this.attributes.getMaxInventory(GameValueEnum.HEALTH);
            double hpBrValue = oneScale*this.attributes.getInventory(GameValueEnum.HEALTH);

            graphics2D.setColor(new Color(50,50,50));
            graphics2D.fillRect(
                    x-1,
                    y-1,
                    barLength+2,
                    barHeight+2
            );
            graphics2D.setColor(new Color(255,0,30));
            graphics2D.fillRect(
                    x,
                    y,
                    (int) hpBrValue,
                    barHeight
            );
        }
        BufferedImage image=this.walkAnimation.get(this.direction).getBufferedImage(spriteNum);
        drawTimerCheck(graphics2D);
        graphics2D.drawImage(image, Camera.getInstance().getScreenX(this.getWorldX()), Camera.getInstance().getScreenY(this.getWorldY()), null);
            drawDebugActionCollision(graphics2D, this.actionInUse);
    }

    //ACTION METHODS
    @Override
    public void speak(AbstractEntity entity){
        if((entity instanceof AbstractNPC && this.quietTime == 0)
                || entity instanceof Player) {
            if (dialogCounter >= dialogArray.length) {
                dialogCounter = 0;
            }
            MessageTypeEnum messageType = MessageTypeEnum.DIALOG;
            if (entity instanceof AbstractNPC) {
                this.quietTime = AppVariables.messageStayFor;
                messageType = MessageTypeEnum.WORLD_EVENT;
            }
            checkVoice();
            MessageBox.getInstance().sendMessage(messageType, dialogArray[dialogCounter],
                    FontEnum.BEDROCK, Color.BLACK, 40,
                    true, 28,
                    this.worldX, this.worldY);
            if (AppConstants.DEBUG) {
                System.out.println(getClass().getSimpleName().toUpperCase(Locale.ROOT) + " " + name + " at " + this.getWorldX() + "," + this.getWorldY() + " speak with " + entity.getName() + " at " + entity.getWorldX() + "," + entity.getWorldY()
                        + "\nIl dit : " + dialogArray[dialogCounter]);
            }
            dialogCounter++;

            switch (entity.getDirection()) {
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
    }

    private void checkVoice() {
//FIXME wav format not supported
//        switch (type){
//            case BLACK_DOG:
//                if (dialogCounter==0){
//                    SoundController.getInstance().playSoundEffect(SoundDescriptionEnum.LONG_WOUAF);
//                }else{
//                    SoundController.getInstance().playSoundEffect(SoundDescriptionEnum.SHORT_WOUAF);
//                }
//                break;
//            case CHIKEN:
//                if (dialogCounter==0){
//
//                }else{
//
//                }
//                break;
//        }
    }

    public abstract void setDialog();
    public void setAction() {
        if(actionLockCounter >= 1* AppVariables.FPS) {
            Randomizer randomizer = new Randomizer();
            int randomNumber = randomizer.getRandomNumberInRange(100);

            if (randomNumber <= 25) {
                this.direction = DirectionsEnum.UP;
            } else if (randomNumber <= 50) {
                this.direction = DirectionsEnum.LEFT;
            } else if (randomNumber <= 75) {
                this.direction = DirectionsEnum.RIGHT;
            } else if (randomNumber <= 100) {
                this.direction = DirectionsEnum.DOWN;
            }
            int maxValue=this.walkAnimation.get(this.direction).bufferedImage.length-1;
            this.spriteNum = NumberUtils.changeValue(1,spriteNum,maxValue,NumberUtils.NumberLoopType.LOOP_STOP);
            actionLockCounter = 0;
        }
        actionLockCounter++;
    }
    public void pickup(GameObject gameObject) {
        if(AppConstants.DEBUG){
            System.out.println(getClass() + " " + this.name + " at " + this.worldX+","+this.worldY + " could pickup "
                    +gameObject.getName() +" at " +gameObject.getWorldX() +"," +gameObject.getWorldY());
        }
    }
}