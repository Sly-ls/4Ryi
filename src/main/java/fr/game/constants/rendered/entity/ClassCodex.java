package fr.game.constants.rendered.entity;

import fr.game.constants.InInventory;
import fr.game.constants.game.SkillnEnum;
import fr.game.constants.rendered.GameObjectEnum;
import fr.game.constants.rendered.WeaponEnum;

public enum ClassCodex  implements InInventory {
    GUERRIER("GUERRIER",
            "Les GUERRIERS...",
            new String[]{"/icons/icon_warrior.jpg"},
            new SkillnEnum[]{SkillnEnum.WEAPON_THRUST,SkillnEnum.POISONOUS_TOUCH},
            new InInventory[]{GameObjectEnum.RED_KEY,WeaponEnum.DAGGER,WeaponEnum.MACE}),
    MAGE("MAGE",
            "Les MAGES...",
            new String[]{"/icons/icon_mage.jpg"},
            new SkillnEnum[]{SkillnEnum.MAGIC_ARROW,SkillnEnum.WEAPON_THRUST,SkillnEnum.POISONOUS_TOUCH},
            new InInventory[]{GameObjectEnum.RED_KEY,WeaponEnum.DAGGER,WeaponEnum.MACE}),
    NINJA("NINJA",
            "Les NINJAS...",
            new String[]{"/icons/icon_ninja.jpg"},
            new SkillnEnum[]{SkillnEnum.MAGIC_ARROW,SkillnEnum.WEAPON_THRUST},
            new InInventory[]{GameObjectEnum.RED_KEY,WeaponEnum.DAGGER,WeaponEnum.MACE});

    final private String name;
    final private String description;
    final private String[] imagesPath;
    final private SkillnEnum[] classSkill;
    final private InInventory[] equimentSet;

    //CONSTRUCTOR
    ClassCodex(String name, String description, String[] image, SkillnEnum[] attributeBonus, InInventory[] equimentSet) {
        this.name = name;
        this.imagesPath = image;
        this.description = description;
        this.classSkill = attributeBonus;
        this.equimentSet = equimentSet;
    }

    //GETTER ET SETTER
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String[] getImagesPath() {
        return imagesPath;
    }
    public SkillnEnum[] getClassSkill() {
        return classSkill;
    }
    public InInventory[] getEquimentSet() {
        return equimentSet;
    }
}
