package fr.game.constants.world;

public enum DayDescriptorEnum {
    DAY_1(0, 21, 6, 4, 4, 5);
    int code;
    int duskHour;
    int dawnHour;
    int duskSpeed;
    int dawnSpeed;
    int daylLengthInMinute;
    DayDescriptorEnum(int code, int dusk, int dawn, int duskSpeed, int dawnSpeed, int daylLength) {
        this.code = code;
        this.duskHour = dusk;
        this.dawnHour = dawn;
        this.duskSpeed = duskSpeed;
        this.dawnSpeed = dawnSpeed;
        this.daylLengthInMinute = daylLength;
    }

    public int getCode() {
        return code;
    }

    public int getDuskHour() {
        return duskHour;
    }

    public int getDawnHour() {
        return dawnHour;
    }

    public int getDuskSpeed() {
        return duskSpeed;
    }

    public int getDawnSpeed() {
        return dawnSpeed;
    }

    public int getDaylLengthInMinute() {
        return daylLengthInMinute;
    }
}
