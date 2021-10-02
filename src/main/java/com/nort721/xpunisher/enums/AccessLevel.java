package com.nort721.xpunisher.enums;

import lombok.Getter;

@Getter
public enum AccessLevel {
    ADMIN("admin"), MANAGER("manager"), INSPECTOR("inspector"), OWNER("owner"), NONE("none");

    private final String name;

    AccessLevel(String name) {
        this.name = name;
    }
}
