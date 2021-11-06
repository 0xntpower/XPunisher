package com.nort721.xpunisher.data.enums;

import lombok.Getter;

public enum PunishType {
    WARNING(10), KICK(20), BAN(30);

    public static PunishType getPunishTypeFromString(String str) {
        for (PunishType type : PunishType.values()) {
            if ((type + "").equalsIgnoreCase(str))
                return type;
        }
        return null;
    }

    @Getter
    private final int points;

    PunishType(int points) {
        this.points = points;
    }
}
