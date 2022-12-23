package com.shrey.mongo.core.crud;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;

@Slf4j
public class UpdatedManyDemo {
    public static void main(String[] args) {
        String connectionString = System.getenv("MONGO_URI");

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // InsertOne demo
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
            MongoCollection<Document> inspectionsCollection = sampleTrainingDB.getCollection("inspections");

            // Prepare filter command
            Bson filterQuery = Filters.and(
                    Filters.eq("address.zip", 10601),
                    Filters.eq("result", "Fail")
            );

            // Prepare update command
            Bson updateQuery = Updates.combine(
                    Updates.set("result", "Fail - Updated")
            );

            // Execute update many command
            UpdateResult result = inspectionsCollection.updateMany(
                    filterQuery,
                    updateQuery
            );

            // Print result
            log.info("Update many - Matched count is -> {}", result.getMatchedCount());
            log.info("Update many - Modified count is -> {}", result.getModifiedCount());
        }
    }
}
