package fr.game.mechanics.core;

import fr.game.constants.game.GameValueEnum;
import fr.game.constants.game.effect.GameValueType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//TODO to transform this into a <K,V> , i need to move the switch part to Entity
public class Inventory <K>{


    protected HashMap<K, fr.game.mechanics.core.GameValue> inventory;

    public Inventory() {
        this.inventory = new HashMap<K, fr.game.mechanics.core.GameValue>();;
    }

    public boolean changeQuantity(K valueType, int quantity) {
        if(this.inventory.get(valueType) == null){
            this.inventory.put(valueType, new fr.game.mechanics.core.GameValue(valueType));
        }
        boolean hasChanged = this.inventory.get(valueType).changeValue(quantity);
        if (hasChanged){
            if (valueType instanceof GameValueEnum){
                if (((GameValueEnum) valueType).getTypeOfStat() != GameValueType.PERMANENT){
                    if (this.inventory.get(valueType).getValue() == 0){
                        this.inventory.remove(valueType);
                    }
                }
            }
        }
        return hasChanged;
    }
    public Inventory<K> changeQuantityThen(K valueType, int quantity) {
        changeQuantity( valueType, quantity);
        return this;
    }
    public boolean changeQuantity(K valueType, int quantity, int maxQuantity) {
        if(this.inventory.get(valueType) == null){
            this.inventory.put(valueType, new fr.game.mechanics.core.GameValue(valueType));
        }
        this.inventory.get(valueType).setMaxValue(maxQuantity);
        boolean hasChanged = this.inventory.get(valueType).changeValue(quantity);
        if (hasChanged){
            removeTemporaryValue(valueType);
        }
        return hasChanged;
    }
    private  void removeTemporaryValue(K valueType){
        if (valueType instanceof GameValueEnum){
            if (((GameValueEnum) valueType).getTypeOfStat() != GameValueType.PERMANENT){
                if (this.inventory.get(valueType).getValue() == 0){
                    this.inventory.remove(valueType);
                }
            }
        }
    }
    public int getInventory(K valueType) {
        return inventory.getOrDefault(valueType,new fr.game.mechanics.core.GameValue(valueType)).getValue();
    }

    public int getMaxInventory(K valueType) {
        return inventory.getOrDefault(valueType,new fr.game.mechanics.core.GameValue(valueType)).getMaxValue();
    }
    public Set<Map.Entry<K, fr.game.mechanics.core.GameValue>> getInventory() {
        return this.inventory.entrySet();
    }

}
