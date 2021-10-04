package com.nort721.xpunisher.utils;

import com.nort721.xpunisher.data.PunishedData;
import com.nort721.xpunisher.data.enums.AccessLevel;
import com.nort721.xpunisher.data.enums.SearchType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MongoUtil {

    public AccessLevel isUserCorrect(String username, String password) {
        String replyFromServer = "owner";
        return AccessLevel.valueOf(replyFromServer.toUpperCase());
    }

    public PunishedData getPunishedPlayerDataByUsername(String data, SearchType searchType) {
        // search if there is a document for that player in database, if there is
        // build a PunishedData object with the data from the document and return it
        return null;
    }
}
