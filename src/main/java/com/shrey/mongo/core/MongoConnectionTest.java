package com.shrey.mongo.core;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoConnectionTest {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://admin:admin@my-first-mongodb-cluste.78ca9qb.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            mongoClient.listDatabases().forEach(db ->
                    System.out.println(db.toJson())
            );
        }
    }
}
