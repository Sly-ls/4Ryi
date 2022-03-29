package fr.game.rendererd;

import fr.game.main.AppConstants;
import fr.game.main.AppVariables;
import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.core.FontEnum;
import fr.game.constants.core.GameUnitEnum;
import fr.game.main.utils.Graphics2DUtils;
import fr.game.mechanics.controller.Camera;
import fr.game.rendererd.entity.NPC.AbstractNPC;
import fr.game.rendererd.entity.PC.Player;
import fr.game.rendererd.event.GameEvent;
import fr.game.rendererd.object.GameObject;

import java.awt.*;


public abstract class AbstractRendered   {

    //Coordinate
    protected int worldX;
    protected int worldY;
    protected Rectangle solidArea;
    protected int solidAreaDefaultX, solidAreaDefaultY;
    protected int solidAreaDefaultWitdh;
    protected int solidAreaDefaultHeigth;
    protected boolean collision = false;
    protected DirectionsEnum direction = DirectionsEnum.UP;
    protected double facingAngle = 0;

    //animations
    protected int spriteCounter = 0;
    protected int spriteNum = 0;
    protected boolean blink = false;

    //NPC
    protected int quietTime = 0;


    //CONSTRUCTOR
    public AbstractRendered() {
    }

    //CLASS METHODS

    //GRAPHICS METHOD
    public abstract void update();
    public abstract void draw(Graphics2D graphics2D);
    public void drawDebug(Graphics2D graphics2D){
        if(AppConstants.DEBUG) {
            if (this instanceof Player){
                //drawing the camera position in the upper left corner of the screen, only player do that, so it's only draw one time

                Graphics2DUtils.setUIFont(graphics2D, FontEnum.ARIAL,20, Color.GREEN);
                StringBuffer messageCamera = new StringBuffer();
                messageCamera.append("c").append(Camera.getInstance().getCameraWorldX(GameUnitEnum.TILE))
                        .append(",").append(Camera.getInstance().getCameraWorldY(GameUnitEnum.TILE));
                graphics2D.drawString(messageCamera.toString(),
                        AppVariables.tileSize,
                        AppVariables.tileSize);
            }
            if(AppConstants.DEBUG && (AppConstants.DEBUG_OBJECT_COLLISION||AppConstants.DEBUG_ACTION_COLLISION)) {
                if (this instanceof Player){
                    graphics2D.setColor(new Color(0, 0, 255, 150));
                }else if(this instanceof AbstractNPC) {
                    graphics2D.setColor(new Color(255, 0, 255, 150));
                }else if(this instanceof GameEvent){
                    graphics2D.setColor(new Color(255, 0, 0, 150));
                }else if (this instanceof GameObject){
                    graphics2D.setColor(new Color(255, 255, 0, 150));
                }else{
                    graphics2D.setColor(new Color(255, 255, 255, 150));
                }
            }
            //sollid AREA for object ollision
            if(AppConstants.DEBUG && AppConstants.DEBUG_OBJECT_COLLISION) {
                graphics2D.fillRect(Camera.getInstance().getScreenX(this.getWorldX()) + this.getSolidArea().x,
                        Camera.getInstance().getScreenY(this.getWorldY()) + this.getSolidArea().y,
                        this.getSolidArea().width,
                        this.getSolidArea().height);

            }

            //world position for all
            Graphics2DUtils.setUIFont(graphics2D, FontEnum.ARIAL,28,Color.DARK_GRAY);
            StringBuffer debugMessage = new StringBuffer();
            debugMessage.append(this.getWorldX()/ AppVariables.tileSize)
                    .append(",").append(this.getWorldY()/ AppVariables.tileSize);
            graphics2D.drawString(debugMessage.toString(),
                    Camera.getInstance().getScreenX(this.getWorldX()),
                    Camera.getInstance().getScreenY(this.getWorldY()) + AppVariables.tileSize);

            //info on player
            if(this instanceof Player){
                debugMessage = new StringBuffer();
                debugMessage.append("f:").append(AppConstants.DECIMAL_FORMAT.format(this.facingAngle));
                graphics2D.drawString(debugMessage.toString(),
                        Camera.getInstance().getScreenX(this.getWorldX()),
                        Camera.getInstance().getScreenY(this.getWorldY()) + AppVariables.tileSize+15);
                debugMessage = new StringBuffer();
                debugMessage.append("sprite:").append(spriteCounter);
                debugMessage.append(",").append(spriteNum);
                graphics2D.drawString(debugMessage.toString(),
                        Camera.getInstance().getScreenX(this.getWorldX()),
                        Camera.getInstance().getScreenY(this.getWorldY()) + AppVariables.tileSize+30);
            }
            //Dot as world position
            if (this instanceof Player){
                graphics2D.setColor(new Color(255, 0, 0, 255));
            }else if(this instanceof AbstractNPC) {
                graphics2D.setColor(new Color(255, 255, 0, 255));
            }else if(this instanceof GameEvent){
                graphics2D.setColor(new Color(0, 0, 255, 255));
            }else if (this instanceof GameObject){
                graphics2D.setColor(new Color(255, 0, 255, 255));
            }else{
                graphics2D.setColor(new Color(0, 0, 0, 255));
            }
            graphics2D.fillOval(Camera.getInstance().getScreenX(this.worldX- 5) ,
                    Camera.getInstance().getScreenY(this.worldY- 5),
                    10,10);
        }
    }

    //GETTER && SETTER
    public int getWorldX() {
        return worldX;
    }
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }
    public int getWorldY() {
        return worldY;
    }
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
    public Rectangle getSolidArea() {
        return solidArea;
    }
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }
    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }
    public boolean isCollision() {
        return collision;
    }
    public DirectionsEnum getDirection() {
        return direction;
    }
    public double getFacingAngle() {
        return facingAngle;
    }
}
