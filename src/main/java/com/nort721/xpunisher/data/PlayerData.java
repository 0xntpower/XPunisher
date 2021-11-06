package com.nort721.xpunisher.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlayerData {
    public static final int TOTAL_POINTS = 100;
    private String playerName;
    private String steamID;
    private int points;
    private List<Punishment> punishments;

    public PlayerData(String playerName, String steamID) {
        this.playerName = playerName;
        this.steamID = steamID;
    }

    public PlayerData(String playerName, String steamID, int points) {
        this.playerName = playerName;
        this.steamID = steamID;
        this.points = points;
    }

    public PlayerData(String playerName, String steamID, List<Punishment> punishments) {
        this.playerName = playerName;
        this.steamID = steamID;
        this.punishments = punishments;
    }

    public PlayerData(String playerName, String steamID, int points, List<Punishment> punishments) {
        this.playerName = playerName;
        this.steamID = steamID;
        this.points = points;
        this.punishments = punishments;
    }
}
