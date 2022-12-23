package com.shrey.mongo.core.crud;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;

@Slf4j
public class DeleteManyDemo {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://admin:admin@my-first-mongodb-cluste.78ca9qb.mongodb.net/?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // InsertOne demo
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_analytics");
            MongoCollection<Document> inspectionsCollection = sampleTrainingDB.getCollection("accounts");

            // Prepare filter command
            Bson filterQuery = Filters.in("account_id", 45454545, 46464646);

            // Execute delete command
            DeleteResult result = inspectionsCollection.deleteMany(filterQuery);

            // Print result
            log.info("Delete many - No of document deleted is -> {}", result.getDeletedCount());
        }
    }
}
