package com.nort721.xpunisher.utils;

import com.nort721.xpunisher.data.enums.Language;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;

@UtilityClass
public class TranslationUtil {

    public static Language currentLanguage;
    private final ArrayList<TextData> translations = new ArrayList<>();

    static {
        currentLanguage = Language.ENGLISH;
        translations.add(new TextData("שם משתמש:","username:"));
        translations.add(new TextData("ססמה:","password:"));
        translations.add(new TextData("כניסה","login"));
        translations.add(new TextData("רמת גישה:", "               Access-Level: "));
        translations.add(new TextData("חפש שחקן:", "search player:"));
        translations.add(new TextData("חיפוס", "Search"));
        translations.add(new TextData("הוסף הענשה", "Add punishment"));
        translations.add(new TextData("לפי שם", "By name"));
        translations.add(new TextData("לפי סטים איידי", "By SteamID"));
        translations.add(new TextData("לפי סיבת הענשה", "By Punish Reason"));
        translations.add(new TextData("סטים איידי:", "steamId:"));
        translations.add(new TextData("סיבה:", "reason:"));
        translations.add(new TextData("תאריך:", "date:"));
        translations.add(new TextData("הענשה:", "punishment:"));
        translations.add(new TextData("משך הענשה:", "duration:"));
        translations.add(new TextData("הוסף", "Add"));
        translations.add(new TextData("ביטול", "Cancel"));
        translations.add(new TextData("באן", "ban"));
        translations.add(new TextData("אזהרה", "warning"));
        translations.add(new TextData("קיק", "kick"));
        translations.add(new TextData("שם משתמש או ססמה לא נכונים", "Incorrect username or password"));
        translations.add(new TextData("ברוך הבא", "Welcome"));
        translations.add(new TextData("", ""));
        translations.add(new TextData("", ""));
        translations.add(new TextData("", ""));
        translations.add(new TextData("", ""));
    }

    public String convertToHebrew(String msg) {
        currentLanguage = Language.HEBREW;
        for (TextData textData : translations) {
            if (textData.getEnglish().equals(msg))
                return textData.getHebrew();
        }
        return msg;
    }

    public String convertToEnglish(String msg) {
        currentLanguage = Language.ENGLISH;
        for (TextData textData : translations) {
            if (textData.getHebrew().equals(msg))
                return textData.getEnglish();
        }
        return msg;
    }
}
