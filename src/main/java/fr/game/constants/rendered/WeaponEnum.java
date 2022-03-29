package fr.game.constants.rendered;

import fr.game.constants.InInventory;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.game.SlotEnum;
import fr.game.mechanics.core.Inventory;
import fr.game.mechanics.dice.DiceCodex;

public enum WeaponEnum implements InInventory {

    SWORD("Épéée standard", new SlotEnum[]{SlotEnum.MAIN_HAND,SlotEnum.OFF_HAND},
            GameValueEnum.BODY,
            new Inventory<DiceCodex>().changeQuantityThen(DiceCodex.MEDIUM_BLADE,1),
            1,1,1,
            new String[]{"/objects/weapons/sword2.png"},
             10,11, 20,30),
    AXE("Hache standard", new SlotEnum[]{SlotEnum.MAIN_HAND,SlotEnum.OFF_HAND},
          GameValueEnum.BODY,
            new Inventory<DiceCodex>().changeQuantityThen(DiceCodex.MEDIUM_BLADE,1),
            2,0,1,
            new String[]{"/objects/weapons/axe.png"},
            14,10, 23,36),
    DAGGER("Dague standard", new SlotEnum[]{SlotEnum.MAIN_HAND,SlotEnum.OFF_HAND},
            GameValueEnum.SPEED,
            new Inventory<DiceCodex>().changeQuantityThen(DiceCodex.SMALL_BLADE,1),
            1,0,1,
            new String[]{"/objects/weapons/sword.png"},
            10,10, 23,36),
    SHIELD("Bouclier standard", new SlotEnum[]{SlotEnum.OFF_HAND},
            GameValueEnum.BODY,
            new Inventory<DiceCodex>().changeQuantityThen(DiceCodex.MEDIUM_ARMOR,1),
            0,2,1,
            new String[]{"/icons/invincible.png"},
            10,10, 23,36),
    MACE("Masse standard", new SlotEnum[]{SlotEnum.MAIN_HAND,SlotEnum.OFF_HAND},
            GameValueEnum.BODY,
            new Inventory<DiceCodex>().changeQuantityThen(DiceCodex.MEDIUM_BLUNT,1),
            1,1,1,
            new String[]{"/objects/weapons/mace.png"},
            10,10, 23,36)
    ;

    final private String name;
    final private SlotEnum equipableSlot[];
    final private GameValueEnum mainAttribute;
    final private Inventory<DiceCodex> dice;
    final private int attackScale;
    final private int defenseScale;
    final private int requiredAttributeValue;
    final private String[] imagesPath;
    final private int solidAreaDefaultX;
    final private int solidAreaDefaultY;
    final private int solidAreaDefaultWitdh;
    final private int solidAreaDefaultHeigth;

    //CONSTRUCTOR
    WeaponEnum(String name, SlotEnum[] equipableSlot,
               GameValueEnum mainAttribute,
               Inventory<DiceCodex> dice, int attackScale, int defenseScale, int requiredAttributeValue,
               String[] weaponImage,
               int solidAreaDefaultX, int solidAreaDefaultY, int solidAreaDefaultWitdh, int solidAreaDefaultHeigth) {
        this.name = name;
        this.equipableSlot = equipableSlot;
        this.mainAttribute = mainAttribute;
        this.dice = dice;
        this.attackScale = attackScale;
        this.defenseScale = defenseScale;
        this.requiredAttributeValue = requiredAttributeValue;
        this.imagesPath = weaponImage;
        this.solidAreaDefaultX = solidAreaDefaultX;
        this.solidAreaDefaultY = solidAreaDefaultY;
        this.solidAreaDefaultWitdh = solidAreaDefaultWitdh;
        this.solidAreaDefaultHeigth = solidAreaDefaultHeigth;
    }

    //GETTER
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
    public String[] getImagesPath() {
        return imagesPath;
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
    public SlotEnum[] getEquipableSlot() {
        return equipableSlot;
    }

}