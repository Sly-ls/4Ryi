package fr.game.constants.game.effect;

public enum GameValueType {
    PERMANENT("PERMANENT",
            "stats permanentes. La première, obligatoire pour tous le monde, est le niveau " +
            "(aka le niveau de créature ,voir la desciption dans [???-faut que je cherche])" +
                    "mais on peut penser à d'autre chose, genre un niveau de maturation pour les dragons;" +
                    "une usure pour les objets, une note pour des skill de craft ou de connaissance" +
                    "des marqueurs pour les quetes" +
                    "des titres"),
    PRIMARY_STAT("PRIMARY_STAT",
            "les stats utilisés pour les objets, la plus parts des skills et pour calculé les secondary stats"),
    SECONDARY_STAT("SECONDARY_STAT",
            "qui est calculé à partir des primary stat;" +
            "pour l'instant, les calculs se font dans EntityCreationPanel.recalculateSecondaryAttributes()"),
    TIMED_DOWN("TIMED_DOWN","valeur qui diminue de 1 à chaque tour. " +
            "Arrivé à 0, la valeur disparait de l'inventaire."),
    TEMPORARY_STAT("TEMPORARY_STAT",
            "stat qui a une durée de vie determinée;" +
            " c'est le jeu qui dit quand on les ajotue ou quand les retires." +
            "Exemple : Défense, reset à chaque tour," +
            "armure, que si on a des équipements qui en donnent, et qu'il en reste");

    String name;
    String description;

    //CONSTRUCTOR
    GameValueType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //GETTER & SETTER
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
