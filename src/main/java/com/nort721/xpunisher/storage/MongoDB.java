package com.nort721.xpunisher.storage;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;

import java.util.Collections;

@Getter
public class MongoDB {

    private MongoClient client;
    private MongoDatabase database;

    private MongoCollection usersCollection;
    private MongoCollection punishedCollection;
    private MongoCollection pendingPunishedCollection;

    public MongoDB() {
        try {

            // read the database info from the config
            String host = "";
            int port = 27017;
            String username = "";
            String password = ""; // ZgQA32!szfUf@zz
            String databaseName = "XPunisherDB";

            // connect to the database
            client = new MongoClient(new ServerAddress(host, port), Collections.singletonList(MongoCredential.createCredential(username, databaseName, password.toCharArray())));
            database = client.getDatabase(databaseName);

            // get the collections from the database (like SQL tables)
            usersCollection = database.getCollection("users");
            punishedCollection = database.getCollection("punished");

        } catch (Exception e) {
            System.out.println("&cDisabling due to issues with mongo database.");
            e.printStackTrace();
        }
    }
}
