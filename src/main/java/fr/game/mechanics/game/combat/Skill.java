package fr.game.mechanics.game.combat;

import fr.game.constants.game.SkillnEnum;
import fr.game.constants.game.GameCondition;
import fr.game.rendererd.AbstractRendered;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Skill {

    SkillnEnum actionType = SkillnEnum.WEAPON_THRUST;
    AbstractRendered caster;
    Map<GameCondition, List<GameEffect>> effects;
    protected Rectangle attackArea = new Rectangle(0,0,0,0);

    //CONSTRUCTOR
    public Skill(SkillnEnum attactType, AbstractRendered caster, List<GameEffect> effects) {
        this.actionType = attactType;
        this.caster = caster;
        this.effects = new HashMap<>();
        addAll(effects);
    }
    public Skill(SkillnEnum attactType, AbstractRendered caster, GameEffect effect) {
        this.actionType = attactType;
        this.caster = caster;
        this.effects = new HashMap<>();
        add(effect);
    }
    public Skill(AbstractRendered caster, GameEffect effect) {
        this.caster = caster;
        this.effects = new HashMap<>();
        add(effect);
    }
    public Skill(AbstractRendered caster, List<GameEffect> effects) {
        this.caster = caster;
        this.effects = new HashMap<>();
        addAll(effects);
    }

    //CLASS METHOS
    public void addAll(List<GameEffect> effects){
        if(effects != null && effects.size() > 0){
            for(GameEffect effect: effects){
                add(effect);
            }
        }
    }
    public void add(GameEffect effect){
        if(effect != null) {
            List<GameEffect> effectsForthisCondition = this.effects.getOrDefault(effect.condition, new ArrayList<>());
            GameEffect foundEffect = null;
            for(GameEffect originalEffect : effectsForthisCondition){
                if (originalEffect.getType() == effect.getType()
                        && originalEffect.getTargetAttribute() == effect.getTargetAttribute()
                        && originalEffect.getTargetCoordinate() == effect.getTargetCoordinate()
                        && originalEffect.getTiming() == effect.getTiming()
                        && originalEffect.getTargetAttribute() == effect.getTargetAttribute()){
                    foundEffect = originalEffect;
                    effect.add(originalEffect);
                }
            }
            if (foundEffect != null){
                effectsForthisCondition.remove(foundEffect);
            }
            effectsForthisCondition.add(effect);
            this.effects.put(effect.condition,effectsForthisCondition);
        }
    }

    //GETTER  && SETTER
    public AbstractRendered getCaster() {
        return caster;
    }
    public Map<GameCondition, List<GameEffect>> getEffects() {
        return effects;
    }
    public SkillnEnum getActionType() {
        return actionType;
    }
    public Rectangle getAttackArea() {
        return attackArea;
    }
    public void setAttackArea(Rectangle attackArea) {
        this.attackArea = attackArea;
    }

}
