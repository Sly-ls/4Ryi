package fr.game.mechanics.game.combat;

import fr.game.constants.game.GameCondition;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.game.effect.GameValueType;
import fr.game.constants.game.effect.EffectTypeEnum;
import javafx.util.Pair;

import java.awt.*;
import java.util.Objects;

public class GameEffect {

    GameCondition condition;
    EffectTypeEnum type;
    GameValueType timing = GameValueType.PERMANENT;
    Integer power;
    GameValueEnum targetAttribute = GameValueEnum.HEALTH;
    Pair<Integer,Integer> targetCoordinate = null;
    protected Rectangle attackArea = new Rectangle(0,0,0,0);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameEffect effect = (GameEffect) o;
        return condition == effect.condition && type == effect.type && timing == effect.timing && Objects.equals(power, effect.power) && targetAttribute == effect.targetAttribute && Objects.equals(targetCoordinate, effect.targetCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, type, timing, targetAttribute, targetCoordinate);
    }

    public GameEffect(EffectTypeEnum type, Integer power, GameValueEnum targetAttribute, GameCondition condition) {
        this.targetAttribute = targetAttribute;
        this.type = type;
        this.power = power;
        this.condition = condition;
    }

    public GameEffect(EffectTypeEnum type, Integer power, Pair<Integer, Integer> targetCoordinate, GameCondition condition) {
        this.type = type;
        this.power = power;
        this.targetCoordinate = targetCoordinate;
        this.condition = condition;
    }


    public void add(GameEffect effect) {
        this.power += effect.getPower();
        if ( effect.getTargetCoordinate() != null ){
            int x = effect.getTargetCoordinate().getKey();
            int y = effect.getTargetCoordinate().getValue();
            if(this.targetCoordinate != null){
                x += this.targetCoordinate.getKey();
                y += this.targetCoordinate.getValue();
            }
            if(x != targetCoordinate.getKey()||y!=targetCoordinate.getValue()){
                this.targetCoordinate = new Pair<>(x,y);
            }
        }
    }
    public EffectTypeEnum getType() {
        return type;
    }

    public GameValueType getTiming() {
        return timing;
    }

    public Integer getPower() {
        return power;
    }

    public Pair<Integer, Integer> getTargetCoordinate() {
        return targetCoordinate;
    }

    public GameValueEnum getTargetAttribute() {
        return targetAttribute;
    }

    public void setType(EffectTypeEnum type) {
        this.type = type;
    }

    public void setTiming(GameValueType timing) {
        this.timing = timing;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public void setTargetAttribute(GameValueEnum targetAttribute) {
        this.targetAttribute = targetAttribute;
    }

    public void setTargetCoordinate(Pair<Integer, Integer> targetCoordinate) {
        this.targetCoordinate = targetCoordinate;
    }

    public GameCondition getCondition() {
        return condition;
    }

    public Rectangle getAttackArea() {
        return attackArea;
    }
}
