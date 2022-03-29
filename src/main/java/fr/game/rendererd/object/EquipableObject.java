package fr.game.rendererd.object;

import fr.game.main.AppVariables;
import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.rendered.GameObjectEnum;
import fr.game.constants.rendered.WeaponEnum;
import fr.game.main.utils.ImageUtils;
import fr.game.mechanics.core.ScaledImage;

import java.util.HashMap;
import java.util.Map;

public class EquipableObject extends GameObject{

    WeaponEnum weaponType;
    String name;
    GameValueEnum mainAttribute;
    int attackScale;
    int defenseScale;
    int requiredAttributeValue;
    Map<DirectionsEnum, ScaledImage> weaponImages;


    public EquipableObject(WeaponEnum weaponEnum,
                           int worldX, int worldY) {
        super(GameObjectEnum.HAND_OBJECT, worldX, worldY);
        this.name = weaponEnum.getName();
        this.mainAttribute = weaponEnum.getMainAttribute();
        this.attackScale = weaponEnum.getAttackScale();
        this.defenseScale = weaponEnum.getDefenseScale();
        this.requiredAttributeValue = weaponEnum.getRequiredAttributeValue();
        this.solidAreaDefaultX = weaponEnum.getSolidAreaDefaultX();
        this.solidAreaDefaultY = weaponEnum.getSolidAreaDefaultY();
        this.solidAreaDefaultWitdh = weaponEnum.getSolidAreaDefaultWitdh();
        this.solidAreaDefaultHeigth = weaponEnum.getSolidAreaDefaultHeigth();


        this.weaponImages =new HashMap<>();
        ScaledImage imageUp = new ScaledImage(weaponEnum.getImagesPath());
        ScaledImage imageRight = new ScaledImage(ImageUtils.createRotated(imageUp.getBufferedImage(), 90));
        ScaledImage imageLeft = new ScaledImage(ImageUtils.createFlipped(imageRight.getBufferedImage(),false));
        ScaledImage imageDown = new ScaledImage(ImageUtils.createFlipped(imageUp.getBufferedImage(),true));
        this.weaponImages.put(DirectionsEnum.UP,imageUp);
        this.weaponImages.put(DirectionsEnum.DOWN,imageDown);
        this.weaponImages.put(DirectionsEnum.RIGHT,imageRight);
        this.weaponImages.put(DirectionsEnum.LEFT,imageLeft);

    }

    @Override
    public String getName() {
        return name;
    }

    public GameValueEnum getMainAttribute() {
        return mainAttribute;
    }

    public int getAttackScale() {
        return attackScale;
    }

    public int getDefenseScale() {
        return defenseScale;
    }

    public int getRequiredAttributeValue() {
        return requiredAttributeValue;
    }

    public Map<DirectionsEnum, ScaledImage> getWeaponImages() {
        return weaponImages;
    }

    public int getSolidAreaDefaultX(DirectionsEnum direction) {
        switch (direction){
            case UP:
                //nothing special to do, the default position for the image , and so the default action area, MUST BE "UP"
                return this.solidAreaDefaultX;
            case RIGHT:
                return AppVariables.tileSize - this.getSolidAreaDefaultY() - this.solidAreaDefaultHeigth;
            case DOWN:
                return this.solidAreaDefaultX;
            case LEFT:
                return this.solidAreaDefaultY;
            default:
                break;
        }
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY(DirectionsEnum direction) {
        switch (direction){
            case UP:
                //nothing special to do, the default position for the image , and so the default action area, MUST BE "UP"
            return this.solidAreaDefaultY;
            case RIGHT:
            return this.solidAreaDefaultX;
            case DOWN:
            return AppVariables.tileSize - this.getSolidAreaDefaultY() - this.solidAreaDefaultHeigth;
            case LEFT:
            return this.solidAreaDefaultX;
            default:
                break;
        }
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultWitdh(DirectionsEnum direction) {
        switch (direction){
            case UP:
                //nothing special to do, the default position for the image , and so the default action area, MUST BE "UP"
            return this.solidAreaDefaultWitdh;
            case RIGHT:
            return this.solidAreaDefaultHeigth;
            case DOWN:
            return this.solidAreaDefaultWitdh;
            case LEFT:
            return this.solidAreaDefaultHeigth;
            default:
                break;
        }
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultHeigth(DirectionsEnum direction) {
        switch (direction){
            case UP:
            return this.solidAreaDefaultHeigth;
            case RIGHT:
            return this.solidAreaDefaultWitdh;
            case DOWN:
            return this.solidAreaDefaultHeigth;
            case LEFT:
            return this.solidAreaDefaultWitdh;
            default:
                break;
        }
        return solidAreaDefaultX;
    }
}
