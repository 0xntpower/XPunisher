package com.nort721.xpunisher.utils;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;

@UtilityClass
public class TranslationUtil {

    private final ArrayList<TextData> translations = new ArrayList<>();

    static {
        translations.add(new TextData("שם משתמש:","username:"));
        translations.add(new TextData("ססמה:","password:"));
        translations.add(new TextData("כניסה","login"));
    }

    public String convertToHebrew(String msg) {
        for (TextData textData : translations) {
            if (textData.getEnglish().equals(msg))
                return textData.getHebrew();
        }
        return "translation error";
    }

    public String convertToEnglish(String msg) {
        for (TextData textData : translations) {
            if (textData.getHebrew().equals(msg))
                return textData.getEnglish();
        }
        return "translation error";
    }
}
