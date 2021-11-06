package com.nort721.xpunisher.data.enums;

import lombok.Getter;

public enum SearchType {
    BY_NAME("By name"), BY_STEAM_ID("By SteamID");

    @Getter
    private final String name;

    SearchType(String name) {
        this.name = name;
    }

    public static SearchType getByName(String val) {
        for (SearchType searchType : SearchType.values())
            if (searchType.name().equalsIgnoreCase(val))
                return searchType;
        return null;
    }
}
