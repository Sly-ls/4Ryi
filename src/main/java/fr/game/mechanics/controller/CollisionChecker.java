package fr.game.mechanics.controller;

import fr.game.main.AppVariables;
import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.game.GameValueEnum;
import fr.game.mechanics.game.combat.Skill;
import fr.game.panel.game.GameController;
import fr.game.rendererd.AbstractRendered;
import fr.game.rendererd.TileManager;
import fr.game.rendererd.entity.AbstractEntity;
import fr.game.rendererd.entity.NPC.AbstractNPC;
import fr.game.rendererd.entity.NPC.Monster;
import fr.game.rendererd.entity.NPC.Neutral;
import fr.game.rendererd.entity.PC.Player;
import fr.game.rendererd.event.GameEvent;
import fr.game.rendererd.object.GameObject;

import java.awt.geom.Point2D;

public class CollisionChecker {

    int defaultObjectIndex = -1;

    public CollisionChecker() {
    }



    public static void checkActionCollision(Skill actionEffects){
        for(AbstractRendered objectToCheckForCollision : GameController.getInstance().getListToUpdate()){
            if(actionEffects.getCaster() == objectToCheckForCollision) continue;
            objectToCheckForCollision.getSolidArea().x = objectToCheckForCollision.getWorldX() + objectToCheckForCollision.getSolidArea().x;
            objectToCheckForCollision.getSolidArea().y = objectToCheckForCollision.getWorldY() + objectToCheckForCollision.getSolidArea().y;
            boolean hit = checkActionCollision(actionEffects, objectToCheckForCollision);
            objectToCheckForCollision.getSolidArea().x = objectToCheckForCollision.getSolidAreaDefaultX();
            objectToCheckForCollision.getSolidArea().y = objectToCheckForCollision.getSolidAreaDefaultY();
            if(hit){
                if(objectToCheckForCollision instanceof AbstractEntity){
                    ((AbstractEntity) objectToCheckForCollision).receiveEffects(actionEffects);
                }
            }
        }
    }


    public static void checkGameObjectCollision(AbstractEntity entity, Point2D velocity) {
        for(AbstractRendered objectToCheckForCollision : GameController.getInstance().getListToUpdate()){
            if(entity == objectToCheckForCollision) continue;
            if(entity == objectToCheckForCollision) continue;
            boolean interactWith = false;
            entity.getSolidArea().x = entity.getWorldX()  + entity.getSolidArea().x;
            entity.getSolidArea().y = entity.getWorldY()  + entity.getSolidArea().y;
            objectToCheckForCollision.getSolidArea().x = objectToCheckForCollision.getWorldX() + objectToCheckForCollision.getSolidArea().x;
            objectToCheckForCollision.getSolidArea().y = objectToCheckForCollision.getWorldY() + objectToCheckForCollision.getSolidArea().y;
            entity.getSolidArea().y += velocity.getY();
            entity.getSolidArea().x += velocity.getX();
            interactWith = checkGameObjectCollision(entity, objectToCheckForCollision);
            entity.getSolidArea().x = entity.getSolidAreaDefaultX();
            entity.getSolidArea().y = entity.getSolidAreaDefaultY();
            objectToCheckForCollision.getSolidArea().x = objectToCheckForCollision.getSolidAreaDefaultX();
            objectToCheckForCollision.getSolidArea().y = objectToCheckForCollision.getSolidAreaDefaultY();
            //TODO fine tune, check and externalize
            if(interactWith){
                if(entity instanceof AbstractEntity){

                    if(entity instanceof Player) {
                        if (objectToCheckForCollision instanceof Neutral) {
                            entity.speak((AbstractEntity) objectToCheckForCollision);
                        } else if (objectToCheckForCollision instanceof Monster) {
                            ((Monster) objectToCheckForCollision).attack(entity);
                        } else if (objectToCheckForCollision instanceof GameObject) {
                            entity.use((GameObject) objectToCheckForCollision);
                        } else if (objectToCheckForCollision instanceof GameEvent) {
                            entity.trigger((GameEvent) objectToCheckForCollision);
                        }
                    }
                    if(entity instanceof AbstractNPC) {
                        if(entity instanceof Neutral){
                            if (objectToCheckForCollision instanceof Neutral) {
                                entity.speak((AbstractEntity) objectToCheckForCollision);
                            } else if (objectToCheckForCollision instanceof Monster) {
                                ((Monster) objectToCheckForCollision).attack(entity);
                            } else if (objectToCheckForCollision instanceof GameObject) {
                                entity.use((GameObject) objectToCheckForCollision);
                            } else if (objectToCheckForCollision instanceof GameEvent) {
                                entity.trigger((GameEvent) objectToCheckForCollision);
                            }
                        }
                        if(entity instanceof Monster){
                            if (objectToCheckForCollision instanceof Monster) {
                                ((Monster) objectToCheckForCollision).speak((AbstractEntity) objectToCheckForCollision);
                            } else if (objectToCheckForCollision instanceof GameObject) {
                                entity.use((GameObject) objectToCheckForCollision);
                            } else if (objectToCheckForCollision instanceof GameEvent) {
                                entity.trigger((GameEvent) objectToCheckForCollision);
                            }else{
                                ((Monster) entity).attack((AbstractEntity) objectToCheckForCollision);
                            }
                        }
                    }
                }
            }
        }

    }
    public static void checkGameObjectCollision(AbstractEntity entity){
        Point2D velocity = convertDirectionIntoPosition2D(entity.getDirection(), entity.getAttributes(GameValueEnum.SPEED));
        checkGameObjectCollision(entity, velocity);
    }
    public static void checkTilesCollision(AbstractEntity entity, Point2D velocity) {
        int entityLeftWorldX = (int) (velocity.getX() + entity.getWorldX() + entity.getSolidArea().x);
        int entityRightWorldX = (int) (velocity.getX() + entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width);
        int entityTopWorldY = (int) (velocity.getY() + entity.getWorldY() + entity.getSolidArea().y);
        int entityBottomWorldY = (int) (velocity.getY() + entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height);

        int entityLeftCol = entityLeftWorldX / AppVariables.tileSize;
        int entityRightCol = entityRightWorldX / AppVariables.tileSize;
        int entityTopRow = entityTopWorldY / AppVariables.tileSize;
        int entityBottomRow = entityBottomWorldY / AppVariables.tileSize;
        int tileNum1, tileNum2;
        if(velocity.getY() < 0){
            entityTopRow = (entityTopWorldY - entity.getAttributes(GameValueEnum.SPEED)) / AppVariables.tileSize;
            tileNum1 = TileManager.getInstance().getMapTileArray()[entityLeftCol][entityTopRow];
            tileNum2 = TileManager.getInstance().getMapTileArray()[entityRightCol][entityTopRow];
            checkTilesCollision(tileNum1, tileNum2, entity);
        }else if (velocity.getY() > 0){
            entityBottomRow = (entityBottomWorldY + entity.getAttributes(GameValueEnum.SPEED)) / AppVariables.tileSize;
            tileNum1 = TileManager.getInstance().getMapTileArray()[entityLeftCol][entityBottomRow];
            tileNum2 = TileManager.getInstance().getMapTileArray()[entityRightCol][entityBottomRow];
            checkTilesCollision(tileNum1, tileNum2, entity);
        }
        if(velocity.getX() < 0){
            entityLeftCol = (entityLeftWorldX - entity.getAttributes(GameValueEnum.SPEED)) / AppVariables.tileSize;
            tileNum1 = TileManager.getInstance().getMapTileArray()[entityLeftCol][entityTopRow];
            tileNum2 = TileManager.getInstance().getMapTileArray()[entityLeftCol][entityBottomRow];
            checkTilesCollision(tileNum1, tileNum2, entity);
        }else if (velocity.getX() > 0){
            entityRightCol = (entityRightWorldX + entity.getAttributes(GameValueEnum.SPEED)) / AppVariables.tileSize;
            tileNum1 = TileManager.getInstance().getMapTileArray()[entityRightCol][entityTopRow];
            tileNum2 = TileManager.getInstance().getMapTileArray()[entityRightCol][entityBottomRow];
            checkTilesCollision(tileNum1, tileNum2, entity);
        }
    }
    public static void checkTilesCollision(AbstractEntity entity){
        checkTilesCollision(entity, convertDirectionIntoPosition2D(entity.getDirection(), entity.getAttributes(GameValueEnum.SPEED)));
    }
    public static Point2D convertDirectionIntoPosition2D(DirectionsEnum directionsEnum, int spoeed){

        Point2D velocity = new Point2D.Double(0,0);
        switch (directionsEnum) {
            case UP:
                velocity.setLocation(0,-spoeed);
                break;
            case DOWN:
                velocity.setLocation(0,spoeed);
                break;
            case LEFT:
                velocity.setLocation(-spoeed,0);
                break;
            case RIGHT:
                velocity.setLocation(spoeed,0);
                break;
            default:
                break;
        }
        return velocity;
    }
    private static void checkTilesCollision(int tileNum1, int tileNum2, AbstractEntity objectMoving){
        if(TileManager.getInstance().getTileIndex().get(tileNum1).isCollision()
                || TileManager.getInstance().getTileIndex().get(tileNum2).isCollision()){
            objectMoving.setCollisionOn(true);
        }
    }

    private static boolean checkGameObjectCollision(AbstractEntity objectMoving, AbstractRendered objectTocheck){
        boolean interactWith = false;
        if(objectMoving.getSolidArea().intersects(objectTocheck.getSolidArea())){
            interactWith = true;
            if(objectTocheck.isCollision()){
                objectMoving.setCollisionOn(true);
            }
        }
        return interactWith;
    }

    private static boolean checkActionCollision(Skill actionEffects, AbstractRendered objectToCheckForCollision) {
        boolean interactWith = false;
        if(actionEffects.getAttackArea().intersects(objectToCheckForCollision.getSolidArea())){
            if(objectToCheckForCollision.isCollision()){
                interactWith = true;
            }
        }
        return interactWith;
    }
}
