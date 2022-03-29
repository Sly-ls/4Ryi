package fr.game.constants.game;

import fr.game.constants.InInventory;
import fr.game.constants.game.effect.GameValueType;

public enum GameValueEnum implements InInventory {

    LEVEL( "Niveau",
            "Niveau de la créature. On ne change pas de niveau dans ce jeu la (hors circonstance de quetes). On aquiert juste de nouvelle compétences." +
                    "Le Niveau indique le nombre de point de carac à la création. " +
                    "Sachant qu'on ne peut avoir plus d'un point de bonus par carac principal et 2 par carac dérivées;" +
                    "Le Niveau indique le niveau de la classe de créature, i.e. à quel point la créature est forte de base.",
            GameValueType.PERMANENT,
            0,10,new
            String[]{"/icons/icon_level.png"}),
    HEALTH( "Santé",
            "Point de vie, quand y'en a plus, t'es mort.",
            GameValueType.SECONDARY_STAT,
            0,1000,new
            String[]{"/icons/life/heart_full.png","/icons/life/heart_half.png","/icons/life/heart_blank.png"}),
    MIND("Esprit",
            "l'attribut d'esprit domine les compétences magiques ainsi que les connaissances." +
                    "Les objets permettant de jeter des sorts et les armes à distance utilisent généralement cette compétences",
            GameValueType.PRIMARY_STAT,
            0,10,
            new String[]{"/icons/icon_mind.png"}),
    SOUL("Âme",
            "l'âme régit les compétences et pouvoir mentaux. " +
                    "Les objets qui permette de canaliser sa puissance mentale utilise en général cette caractéristiques",
            GameValueType.PRIMARY_STAT,
            0,10,
            new String[]{"/icons/icon_soul.png"}),
    BODY("Corps",
            "L'attribut de corps régit les compétences physiques tel que l'artisanat ou." +
                    "Elle est en général utilisé pour les armes de corps à corps.",
            GameValueType.PRIMARY_STAT,
            0,10,
            new String[]{"/icons/icon_body.png"}),
    SPEED("Vitesse",
                  "Initiative, rapidité, réflexe, capacité à agir avant les autres.",
          GameValueType.SECONDARY_STAT,
            0,1000,
                  new String[]{"/objects/boots.png"}),
    STAMINA("Endurance",
                    "Capacité à enchainer les actions pendant un tour",
            GameValueType.SECONDARY_STAT,
            0,1000,
                    new String[]{"/icons/icon_stamina.png"}),

    //TEMPORARY_STAT used for action RPG, need some fine tund to work for turned based RPG
    INVINCIBLE("Invincible",
                       "aucun effet ne s'applique plus après une action qui applique invincibilité",
               GameValueType.TIMED_DOWN,
            0,1000,
                       new String[]{"/icons/invincible.png"}),
    //TEMPORARY_STAT that translate to temporary stat during fight
    ARMOR("Armure",
                  "PV temporaire donnée en général par l'armure et les boucliers",
          GameValueType.TEMPORARY_STAT,
            0,1000,
                  new String[]{"/icons/icon_armor.png"}),
    //DICE VALUE that translate to temporary stat during fight
    DAMAGE("Damage",
                   "donné par les dés des armes",
           GameValueType.TEMPORARY_STAT,
            0,1000,
                   new String[]{"/icons/icon_damage.png"}),
    DEFENSE("Defense",
                    "par les dés des armures et des boucliers",
            GameValueType.TEMPORARY_STAT,
            0,1000,
                    new String[]{"/icons/icon_defense.png"}),
    DODGE("Defense",
                  "par les dés des armures",
          GameValueType.TEMPORARY_STAT,
            0,1000,
                  new String[]{"/icons/icon_defense.png"}),
    CRITICAL("Critique",
                     "par les dés des armes et des boucliers",
             GameValueType.TEMPORARY_STAT,
            0,1000,
                     new String[]{"/icons/icon_critik.png"})
    ;

    String name;
    String description;
    GameValueType typeOfStat;
    int minValue;
    int maxValue;
    String imagesPath[];

    GameValueEnum(String name,
                  String description,
                  GameValueType typeOfStat,
                  int minValue, int maxValue,
                  String[] path) {
        this.name = name;
        this.description = description;
        this.typeOfStat = typeOfStat;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.imagesPath = path;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public String getName() {
        return name;
    }

    public String[] getImagesPath() {
        return imagesPath;
    }

    public GameValueType getTypeOfStat() {
        return typeOfStat;
    }
    }
