package fr.game.rendererd.entity.NPC;

import fr.game.main.AppConstants;
import fr.game.main.AppVariables;
import fr.game.constants.game.GameCondition;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.game.effect.EffectTypeEnum;
import fr.game.constants.rendered.entity.EntityEnum;
import fr.game.mechanics.game.combat.Skill;
import fr.game.mechanics.game.combat.GameEffect;
import fr.game.rendererd.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.List;

public class Monster extends AbstractNPC {

    int attackPower = 1;

    public Monster(EntityEnum entityType, int worldX, int worldY) {
        super(entityType, worldX, worldY);
    }

    public void attack(AbstractEntity entity){
        if(entity.getAttributes(GameValueEnum.INVINCIBLE) < 1) {
            List<GameEffect> listOfEffect = new ArrayList<GameEffect>();
            listOfEffect.add(new GameEffect(EffectTypeEnum.BONUS_MALUS, -1*this.attackPower, GameValueEnum.HEALTH, GameCondition.ON_HIT));
            listOfEffect.add(new GameEffect(EffectTypeEnum.BONUS_MALUS, AppVariables.FPS, GameValueEnum.INVINCIBLE, GameCondition.ON_HIT));
            entity.receiveEffects(new Skill(this,listOfEffect));
            if (AppConstants.DEBUG) {
                System.out.println(this.name + " at " + this.getWorldX() + "," + this.getWorldY() + " attack "
                        + entity.getName()
                        + " at " + entity.getWorldX()
                        + "," + entity.getWorldY());
            }
        }
    }

    public void setDialog(){
        this.dialogArray = new String[2];
        this.dialogArray[0] = "GRRRrrrr";
        this.dialogArray[1] = "RRaaawWW";
    }
}
