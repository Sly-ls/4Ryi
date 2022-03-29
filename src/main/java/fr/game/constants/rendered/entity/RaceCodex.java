package fr.game.constants.rendered.entity;

import fr.game.constants.InInventory;
import fr.game.constants.game.GameValueEnum;
import fr.game.mechanics.core.Inventory;

public enum RaceCodex  implements InInventory {
    HUMAN("Humain",
            "Les HUMAINS...",
            new String[]{"/portraits/banner_human.png"},
            new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.BODY,1)
                    .changeQuantityThen(GameValueEnum.MIND,2)
                    .changeQuantityThen(GameValueEnum.SOUL,1)),
    ORC("ORC",
            "Les ORCS...",
            new String[]{"/portraits/banner_orc.png"},
            new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.BODY,2)
                    .changeQuantityThen(GameValueEnum.MIND,1)
                    .changeQuantityThen(GameValueEnum.SOUL,1)),
    ELF("ELFE",
            "Les ELFES...",
            new String[]{"/portraits/banner_elf.png"},
            new Inventory<GameValueEnum>().changeQuantityThen(GameValueEnum.BODY,1)
                    .changeQuantityThen(GameValueEnum.MIND,1)
                    .changeQuantityThen(GameValueEnum.SOUL,2));

    String name;
    String description;
    final private String[] imagesPath;
    Inventory<GameValueEnum> attributeBonus;

    //CONSTRUCTOR
    RaceCodex(String name, String description, String[] imagesPath, Inventory<GameValueEnum> attributeBonus) {
        this.name = name;
        this.description = description;
        this.imagesPath = imagesPath;
        this.attributeBonus = attributeBonus;
    }

    //GETTER ET SETTER
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Inventory<GameValueEnum> getAttributeBonus() {
        return attributeBonus;
    }
    public void setAttributeBonus(Inventory<GameValueEnum> attributeBonus) {
        this.attributeBonus = attributeBonus;
    }
    public String[] getImagesPath() {
        return imagesPath;
    }
}
