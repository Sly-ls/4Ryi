package fr.game.rendererd.object;

import fr.game.main.AppVariables;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.constants.game.LightSourceEnum;
import fr.game.main.utils.NumberUtils;
import fr.game.mechanics.controller.Camera;
import fr.game.mechanics.core.MessageBox;
import fr.game.rendererd.entity.AbstractEntity;
import fr.game.rendererd.entity.NPC.AbstractNPC;

import java.awt.*;

public class LightSource extends GameObject{

    protected LightSourceEnum type;
    protected int power;
    protected int durability;
    protected int radius;
    protected boolean lightOn;

    public LightSource(LightSourceEnum objectTypeEnum,
                       int worldX, int worldY) {
        super(objectTypeEnum.getGameObject(), worldX, worldY);

        this.type = objectTypeEnum;
        this.power = objectTypeEnum.getPower();
        this.durability = objectTypeEnum.getDurability();
        this.radius = objectTypeEnum.getRadius();
        this.lightOn = true;
    }

    //GRAPHICS METHOD
    @Override
    public void update() {
        if(spriteCounter> AppVariables.flameWaveEvery){
            spriteCounter=0;
            /*
            the first image is for the support of the animation
            you get the list one number shorter to add +1 after getting the 0 value to draw
             */
            this.spriteNum = NumberUtils.changeValue(1,spriteNum,this.image.bufferedImage.length-2,NumberUtils.NumberLoopType.LOOP_PLUS);
        }else{
            spriteCounter++;
        }
    }
    @Override
    public void draw(Graphics2D graphics2D){
        draw(graphics2D, spriteNum);
        graphics2D.drawImage(image.getBufferedImage(0)
                , Camera.getInstance().getScreenX(this.getWorldX()), Camera.getInstance().getScreenY(this.getWorldY()), null);
    }
    public void draw(Graphics2D graphics2D, int imageIndex){
        graphics2D.drawImage(image.getBufferedImage(imageIndex+1)
                , Camera.getInstance().getScreenX(this.getWorldX()),
                Camera.getInstance().getScreenY(this.getWorldY())-AppVariables.tileSize/2-AppVariables.tileSize/6, null);
    }

    //INTERFACE METHOD
    @Override
    public void use(AbstractEntity entity) {
        if((entity instanceof AbstractNPC && this.quietTime == 0)) {
            switch (this.type){
                case GROUND_TORCH:
                    MessageBox.getInstance().sendMessage(MessageTypeEnum.MESSAGE_TO_PLAYER,
                            "TOO HEAVY",entity.getWorldX(),entity.getWorldY(), true);
                    break;
                default:
                    break;
            }
            this.quietTime = AppVariables.messageStayFor;
        }
    }

    // GETTER AND SETTER

    public int getPower() {
        return power;
    }

    public int getDurability() {
        return durability;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isLightOn() {
        return lightOn;
    }
}
