package fr.game.constants.core;

public enum DirectionsEnum {
    UP("up"),
    DOWN("down"),
    RIGHT("right"),
    LEFT("left"),
    ANY("any");

    String name;

    DirectionsEnum(String name) {
        this.name = name;
    }
}
