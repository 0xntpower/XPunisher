package com.nort721.xpunisher.utils;

import com.nort721.xpunisher.enums.AccessLevel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MongoUtil {

    public AccessLevel isUserCorrect(String username, String password) {
        String replyFromServer = "owner";
        return AccessLevel.valueOf(replyFromServer.toUpperCase());
    }


}
