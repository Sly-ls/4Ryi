package fr.game.constants.game;

import fr.game.constants.InInventory;

public enum SkillnEnum implements InInventory {
//xPerFrame and yPerFrame indicate how far from the personnage the weapon goes during action animation, it's reset to 0 for each new sprite, then the values are added
    //FIXME the heros should have one animation for the type of action (Magic, Shoot, Slash)
    WEAPON_THRUST("Épée plongeante",
        new String[]{"/icons/invincible.png"},
            true,
            0, 0,36, 36,
            30, new int[]{5,30}, new int[]{-10,0}, new int[]{-15,5}, new boolean[]{true,true},
            null, SoundDescriptionEnum.HITMONSTER)
    ,POISONOUS_TOUCH("Touché venimeux",
            new String[]{"/icons/icon_armor.png"},
            true,
            0, 0,36, 36,
            30, new int[]{30}, new int[]{0}, new int[]{0}, new boolean[]{true},
            null, SoundDescriptionEnum.HITMONSTER)
    ,MAGIC_ARROW("Fléche magique",
            new String[]{"/icons/icon_STRENGTH.png"},
                             true,
                             0, 0,36, 36,
                             30, new int[]{30}, new int[]{0}, new int[]{0}, new boolean[]{true},
            null, SoundDescriptionEnum.HITMONSTER);


    final private String name;
    final private String[] imagesPath;
    final private boolean collision;
    final private int solidAreaDefaultX;
    final private int solidAreaDefaultY;
    final private int solidAreaDefaultWitdh;
    final private int solidAreaDefaultHeigth;
    final private int frame;
    final private int[] framePerImage;
    final private int[] xPerImage;
    final private int[] yPerImage;
    final private boolean[] impactPerImage;
    final private SoundDescriptionEnum useSound;
    final private SoundDescriptionEnum sucessSound;

    SkillnEnum(String name,
               String[] image, boolean collision,
               int solidAreaDefaultX, int solidAreaDefaultY,
               int solidAreaDefaultWitdh, int solidAreaDefaultHeigth, int frame, int[] framePerImage, int[] xPerImage, int[] yPerImage, boolean[] impactPerImage, SoundDescriptionEnum useSound, SoundDescriptionEnum sucessSound) {
        this.name = name;
        this.imagesPath = image;
        this.solidAreaDefaultX = solidAreaDefaultX;
        this.solidAreaDefaultY = solidAreaDefaultY;
        this.solidAreaDefaultWitdh = solidAreaDefaultWitdh;
        this.solidAreaDefaultHeigth = solidAreaDefaultHeigth;
        this.collision = collision;
        this.frame = frame;
        this.framePerImage = framePerImage;
        this.xPerImage = xPerImage;
        this.yPerImage = yPerImage;
        this.impactPerImage = impactPerImage;
        this.useSound = useSound;
        this.sucessSound = sucessSound;
    }

    public String getName() {
        return name;
    }
    public boolean isCollision() {
        return collision;
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
    public String[] getImagesPath() {
        return imagesPath;
    }
    public int[] getFramePerImage() {
        return framePerImage;
    }
    public int getFrame() {
        return frame;
    }
    public SoundDescriptionEnum getUseSound() {
        return useSound;
    }
    public SoundDescriptionEnum getSucessSound() {
        return sucessSound;
    }
    public boolean[] getImpactPerImage() {
        return impactPerImage;
    }
    public int[] getxPerImage() {
        return xPerImage;
    }
    public int[] getyPerImage() {
        return yPerImage;
    }
}