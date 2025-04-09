package com.nort721.xpunisher.storage;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.nort721.xpunisher.utils.console.Console;
import com.nort721.xpunisher.utils.console.LogType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MongoDB {

    private MongoClient client;
    private MongoDatabase database;

    private MongoCollection usersCollection;
    private MongoCollection playersCollection;

    public MongoDB() {
        try {

            // read the database info from the config
            String host = "testdb.l47li.mongodb.net/TestDB";
            int port = 27017;
            String username = "11";
            String password = "11";
            String databaseName = "TestDB";

            MongoClientURI uri = new MongoClientURI("mongodb+srv://dbuser:Bu2ys8CPBG2ejtD7@testdb.l47li.mongodb.net/TestDB?retryWrites=true&w=majority");

            Console.log("attempting to connect to database", LogType.INFO);

            // connect to the database
            //client = new MongoClient(new ServerAddress(host, port), Collections.singletonList(MongoCredential.createCredential(username, databaseName, password.toCharArray())));
            client = new MongoClient(uri);
            database = client.getDatabase(databaseName);

            Console.log("connected to database successfully", LogType.INFO);

            // get the collections from the database (like SQL tables)
            List<String> collectionsNames = database.listCollectionNames().into(new ArrayList<>());

            if (!collectionsNames.contains("users"))
                database.createCollection("users");
            usersCollection = database.getCollection("users");

            if (!collectionsNames.contains("players"))
                database.createCollection("players");
            playersCollection = database.getCollection("players");

        } catch (Exception e) {
            Console.log("disabling due to issues with mongo database.", LogType.ERR);
            e.printStackTrace();
        }
    }
}
