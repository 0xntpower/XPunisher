package com.nort721.xpunisher.data.enums;

import lombok.Getter;

@Getter
public enum AccessLevel {
    ADMIN("admin", 1), MANAGER("manager", 2),
    INSPECTOR("inspector", 3), OWNER("owner", 4), NONE("none", 0);

    private final String name;
    private final int accessId;

    AccessLevel(String name, int accessId) {
        this.name = name;
        this.accessId = accessId;
    }
}
