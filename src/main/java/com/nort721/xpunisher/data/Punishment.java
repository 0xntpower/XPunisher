package com.nort721.xpunisher.data;

import com.nort721.xpunisher.data.enums.PunishType;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Punishment {
    private final PunishType type;
    private final String date;
    private final String reason;
    @Setter
    private boolean pending;

    public Punishment(PunishType punishType, String date, String reason) {
        type = punishType;
        this.date = date;
        this.reason = reason;
    }

    public Punishment(PunishType punishType, String date, String reason, boolean pending) {
        type = punishType;
        this.date = date;
        this.pending = pending;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "|type=" + type +
                "|date=" + date +
                "|pending=" + pending +
                "|reason=" + reason +
                "|";
    }

    public static Punishment deserialize(String str) {

        PunishType type = null;
        String date = null;
        boolean pending = false;
        String reason = null;

        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();
        boolean isValue = false;

        for (char c : str.toCharArray()) {
            if (c == '|') {

                if (key.toString().equals("type"))
                    type = PunishType.getPunishTypeFromString(value.toString());
                else {
                    if (key.toString().equals("date"))
                        date = value.toString();
                    else {
                        if (key.toString().equals("pending"))
                            pending = value.toString().equals("true");
                        else
                            if (key.toString().equals("reason"))
                                reason = value.toString();
                    }
                }

                key = new StringBuilder();
                value = new StringBuilder();
                isValue = false;

            } else {

                if (c == '=')
                    isValue = true;
                else {
                    if (!isValue)
                        key.append(c);
                    else
                        value.append(c);
                }

            }
        }

        return new Punishment(type, date, reason, pending);
    }
}
