package fr.game.mechanics.dice;

import fr.game.constants.game.GameValueEnum;
import fr.game.mechanics.core.Inventory;

import java.util.List;

public class HandOfDice {
    List<Die> handOfDice;
    Inventory<GameValueEnum> result;


    //GETTER && SETTER
    public List<Die> getHandOfDice() {
        return handOfDice;
    }

    public void setHandOfDice(List<Die> handOfDice) {
        this.handOfDice = handOfDice;
    }

    public Inventory<GameValueEnum> getResult() {
        return result;
    }

    public void setResult(Inventory<GameValueEnum> result) {
        this.result = result;
    }
}
