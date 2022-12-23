package com.shrey.mongo.core.crud;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.util.Arrays;

@Slf4j
public class InsertManyDemo {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://admin:admin@my-first-mongodb-cluste.78ca9qb.mongodb.net/?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // InsertMany demo
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_analytics");
            MongoCollection<Document> accountsCollection = sampleTrainingDB.getCollection("accounts");

            // Prepare multiple documents
            Document doc1 = new Document()
                    .append("account_id", 45454545)
                    .append("balance", 50000)
                    .append("limit", 10000)
                    .append("products", Arrays.asList("Der", "Forex"));

            Document doc2 = new Document()
                    .append("account_id", 46464646)
                    .append("balance", 40000)
                    .append("limit", 9999)
                    .append("products", Arrays.asList("IB", "CB"));

            // Execute insert many command
            InsertManyResult result = accountsCollection.insertMany(
                    Arrays.asList(doc1, doc2)
            );

            // Print result
            result.getInsertedIds().forEach((key, value) ->
                    log.info("Insert many - key -> {}, value -> {} ", key, value)
            );
        }
    }
}
