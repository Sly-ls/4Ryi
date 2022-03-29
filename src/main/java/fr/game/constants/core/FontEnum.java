package fr.game.constants.core;

public enum FontEnum {
    BEDROCK("/fonts/BEDROCKN.TTF"),
    FRANCIS("/fonts/FRANCIS.TTF"),
    FRANCISB("/fonts/FRANCISB.TTF"),
    ARIAL("/fonts/FRANCISB.TTF");

    String path;


    FontEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
