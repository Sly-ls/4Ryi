package fr.game.mechanics.dice;

public class Die {
    DiceCodex dieType;
    boolean rolled;
    int rolledFace;
    int reRolled =0;

    //GETTER && SETTER
    public DiceCodex getDieType() {
        return dieType;
    }

    public void setDieType(DiceCodex dieType) {
        this.dieType = dieType;
    }

    public boolean isRolled() {
        return rolled;
    }

    public void setRolled(boolean rolled) {
        this.rolled = rolled;
    }

    public int getRolledFace() {
        return rolledFace;
    }

    public void setRolledFace(int rolledFace) {
        this.rolledFace = rolledFace;
    }

    public int getReRolled() {
        return reRolled;
    }

    public void setReRolled(int reRolled) {
        this.reRolled = reRolled;
    }
}
