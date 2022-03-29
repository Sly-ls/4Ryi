package fr.game.mechanics.core;

import fr.game.constants.game.GameValueEnum;
import fr.game.main.utils.NumberUtils;

public class GameValue{
    int currentValue = 0;
    int maxValue = 99;
    int minValue = 0;
    NumberUtils.NumberLoopType loop = NumberUtils.NumberLoopType.NO_LOOP;
    //CONSTRUCTOR
    public GameValue(Object valueType) {
        if (valueType != null) {
            if(valueType instanceof GameValueEnum){
                this.currentValue = 0;
                this.minValue = ((GameValueEnum) valueType).getMinValue();
                this.maxValue = ((GameValueEnum) valueType).getMaxValue();
            }
        }
    }
    public GameValue(Object valueType, int value) {
        if (valueType != null) {
            if(valueType instanceof GameValueEnum){
                this.currentValue = value;
                this.minValue = ((GameValueEnum) valueType).getMinValue();
                this.maxValue = ((GameValueEnum) valueType).getMaxValue();
            }
        }
    }
    public GameValue(int maxValue) {
        this.currentValue = maxValue;
        this.maxValue = maxValue;
    }
    public GameValue(int currentValue,int maxValue) {
        this.currentValue = currentValue;
        this.maxValue = maxValue;
    }
    //CLASS METHOD
    public boolean setValue(int init){
        boolean hasChanged = false;
        if(init > maxValue){
            init = maxValue;
        }else if(minValue > init){
            init = minValue;
        }
        if(init != currentValue){
            hasChanged = true;
            this.currentValue = init;
        }
        return hasChanged;
    }
    public boolean changeValue(int change){
        boolean hasChanged = false;
        int newValue = NumberUtils.changeValue(change,currentValue,minValue, maxValue,loop);
        if(newValue != currentValue){
            hasChanged = true;
            this.currentValue = newValue;
        }
        return hasChanged;
    }
    public boolean changeMinValue(int change){
        boolean hasChanged = false;
        int newValue = NumberUtils.changeValue(change,minValue,0, maxValue,NumberUtils.NumberLoopType.NO_LOOP);
        if(newValue != minValue){
            hasChanged = true;
            this.minValue = newValue;
        }
        return hasChanged;
    }
    public boolean changeMaxValue(int change){
        boolean hasChanged = false;
        int tempMax = Math.max(minValue,maxValue+change);
        int newValue = NumberUtils.changeValue(change,maxValue,minValue, tempMax,NumberUtils.NumberLoopType.NO_LOOP);
        if(newValue != maxValue){
            hasChanged = true;
            this.maxValue = newValue;
        }
        return hasChanged;
    }
    //GETTER && SETTER
    public int getValue() {
        return currentValue;
    }
    public int getMaxValue() {
        return maxValue;
    }
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
    public int getMinValue() {
        return minValue;
    }
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }
    public NumberUtils.NumberLoopType getLoop() {
        return loop;
    }
    public void setLoop(NumberUtils.NumberLoopType loop) {
        this.loop = loop;
    }
}