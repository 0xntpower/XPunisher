package com.nort721.xpunisher.utils;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.nort721.xpunisher.data.PlayerData;
import com.nort721.xpunisher.data.Punishment;
import com.nort721.xpunisher.data.enums.AccessLevel;
import com.nort721.xpunisher.data.enums.PunishType;
import com.nort721.xpunisher.data.enums.SearchType;
import com.nort721.xpunisher.menus.LoginGUI;
import com.nort721.xpunisher.utils.console.Console;
import com.nort721.xpunisher.utils.console.LogType;
import lombok.experimental.UtilityClass;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class MongoUtil {

    /**
     * Returns the access level of a user
     * @param username the name of the user
     * @return if the user exists it will return his access level otherwise will return access level none
     */
    public AccessLevel getUserAccessLevel(String username) {

        Document userDocument = (Document) LoginGUI.mongoDB.getUsersCollection().find(Filters.eq("username", username)).first();

        if (userDocument == null) {
            return AccessLevel.NONE;
        }

        return AccessLevel.valueOf(userDocument.getString("accesslevel").toUpperCase());
    }

    /**
     * Checks if the username and password are correct for login
     * @param username the username
     * @param password the password
     * @return Incorrect username if the username does not exist, Incorrect password if the password is not
     * equal to the password thats saved for the username otherwise approved
     */
    @jnic
    public String checkUserLogin(String username, String password) {

        Document userDocument = (Document) LoginGUI.mongoDB.getUsersCollection().find(Filters.eq("username", username)).first();

        if (userDocument == null) {
            return "incorrect username";
        }

        if (!userDocument.getString("password").equals(password))
            return "incorrect password";

        return "approved";
    }

    /**
     * Adds a new user to the database
     * @param username the username
     * @param password the password
     * @param accessLevel the access level for that user
     */
    public void addUserToDatabase(String username, String password, AccessLevel accessLevel) {

        Document userDocument = (Document) LoginGUI.mongoDB.getUsersCollection().find(Filters.eq("username", username)).first();

        if (userDocument != null) {
            Console.log("user document " + username + " already exist (user already registered)", LogType.ERR);
            return;
        }

        Document newUserDocument = new Document();
        newUserDocument.put("username", username);
        newUserDocument.put("password", password);
        newUserDocument.put("accesslevel", accessLevel.getName());

        LoginGUI.mongoDB.getUsersCollection().insertOne(newUserDocument);
    }

    /**
     * Removes a user from the database
     * @param username the name of the user we want to remove
     */
    public void removeUserFromDatabase(String username) {
        Document userDocument = (Document) LoginGUI.mongoDB.getUsersCollection().find(Filters.eq("username", username)).first();

        if (userDocument == null) {
            return;
        }

        LoginGUI.mongoDB.getUsersCollection().deleteOne(userDocument);
    }

    /**
     * Saves a new punishment in the players data profile, if he doesn't have a profile, this will create a new profile for him
     * and store the new punishment in it
     * @param playerData the player data
     * @param punishment the new punishment
     */
    public void savePunishment(PlayerData playerData, Punishment punishment) {
        MongoCollection playersCollection = LoginGUI.mongoDB.getPlayersCollection();

        Document playerDocument = (Document) playersCollection.find(Filters.eq("playerName", playerData.getPlayerName())).first();

        if (playerDocument == null) {
            Console.log("Player does not have a document, creating a new document and adding the punishment", LogType.INFO);

            // player does not have a profile yet, so lets create one
            playerDocument = new Document();
            playerDocument.put("playerName", playerData.getPlayerName());
            playerDocument.put("steamID", playerData.getSteamID());

            int pointsToRemove = punishment.getType().getPoints();
            if (punishment.getType() == PunishType.BAN && punishment.getDuration().equalsIgnoreCase("forever"))
                pointsToRemove = 100;

            playerDocument.put("points", Math.max(PlayerData.TOTAL_POINTS - pointsToRemove, 0));

            playerDocument.put(punishment.getDate(), punishment.toString());

            Console.log("Inserting new player document to collection . . .", LogType.INFO);
            playersCollection.insertOne(playerDocument);
        } else {
            Console.log("Player already has a document, adding the new punishment", LogType.INFO);

            playerDocument.put(punishment.getDate(), punishment.toString());

            int pointsToRemove = punishment.getType().getPoints();
            if (punishment.getType() == PunishType.BAN && punishment.getDuration().equalsIgnoreCase("forever"))
                pointsToRemove = 100;

            playerDocument.replace("points", Math.max(playerDocument.getInteger("points") - pointsToRemove, 0));

            playersCollection.replaceOne(Filters.eq("playerName", playerData.getPlayerName()), playerDocument);
        }

        Console.log("Done adding new punishment", LogType.INFO);
    }

    public PlayerData getPlayerByName(String searchData, SearchType searchType) {
        Document playerDocument = null;

        if (searchType == SearchType.BY_NAME) {
            playerDocument = (Document) LoginGUI.mongoDB.getPlayersCollection().find(Filters.eq("playerName", searchData)).first();
        } else if (searchType == SearchType.BY_STEAM_ID)
            playerDocument = (Document) LoginGUI.mongoDB.getPlayersCollection().find(Filters.eq("steamID", searchData)).first();

        if (playerDocument == null) {
            return null;
        }

        List<String> staticKeys = Arrays.asList("playerName", "steamID", "points");

        List<Punishment> punishments = new ArrayList<>();
        for (String key : playerDocument.keySet()) {
            //ToDo maybe change this tactic to just detect if a key has numbers since punishments are named by their dates
            if (!staticKeys.contains(key))
                punishments.add(Punishment.deserialize(playerDocument.getString(key)));
        }

        return new PlayerData(playerDocument.getString("playerName"), playerDocument.getString("steamID"),
                playerDocument.getInteger("points"), punishments);
    }
}
