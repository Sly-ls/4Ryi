package fr.game.rendererd.entity.NPC;

import fr.game.constants.rendered.entity.EntityEnum;

public class Neutral extends AbstractNPC {
    public Neutral(EntityEnum objectTypeEnum, int worldX, int worldY) {
        super(objectTypeEnum, worldX, worldY);
    }

    @Override
    public void setDialog(){
        if(this.type == EntityEnum.OLD_MAN) {
            this.dialogArray = new String[4];
            this.dialogArray[0] = "Bonjour !";
            this.dialogArray[1] = "J'étais un grand mage quand j'étais plus jeune.";
            this.dialogArray[2] = "J'aime la chantilly, vraiment beaucoup, mais il faut pas en\nabuser, car c'est mauvais pour la santé.";
            this.dialogArray[3] = "Attention quand tu traverse la route.";
        }else if(this.type == EntityEnum.CHIKEN) {
            this.dialogArray = new String[2];
            this.dialogArray[0] = "Cot cot cot !";
            this.dialogArray[1] = "Coooot cot !";
        }else if(this.type == EntityEnum.BLACK_DOG) {
            this.dialogArray = new String[2];
            this.dialogArray[0] = "Wouaf Wouaf";
            this.dialogArray[1] = "WOUAF";
        }else if(this.type == EntityEnum.LADY_MAGE) {
            this.dialogArray = new String[2];
            this.dialogArray[0] = "J'essaie d'apprendre mon premier sort : levitation !";
            this.dialogArray[1] = "C'est dificile d'être mage, il faut beaucoup s'entrainer...";
        }else if(this.type == EntityEnum.MAGIC_SWORD) {
            this.dialogArray = new String[2];
            this.dialogArray[0] = "Attrape-moi si tu peux !";
            this.dialogArray[1] = "bien joué, mais tu n'es pas l'élu...";
        }else if(this.type == EntityEnum.SIREN_MAGE) {
            this.dialogArray = new String[2];
            this.dialogArray[0] = "Je serais la plus puissante des mages sous marines !";
            this.dialogArray[1] = "Pour l'instant, j'augmente mon affinité avec l'eau.";
        }else {
            this.dialogArray = new String[1];
            this.dialogArray[0] = "DIALOGUE DU NPC PAS IMPLEMENTE !";
        }
    }
}
