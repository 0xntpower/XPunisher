package com.nort721.xpunisher.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PunishedData {
    private String username;
    private int points;
    private String steamID;
    private List<Punishment> punishments;
}
