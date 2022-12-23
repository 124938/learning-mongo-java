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
public class DeleteOneDemo {
    public static void main(String[] args) {
        String connectionString = System.getenv("MONGO_URI");

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // InsertOne demo
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
            MongoCollection<Document> inspectionsCollection = sampleTrainingDB.getCollection("inspections");

            // Prepare filter command
            Bson filterQuery = Filters.eq("id", "10022-25222-14855");

            // Execute delete command
            DeleteResult result = inspectionsCollection.deleteMany(filterQuery);

            // Print result
            log.info("Delete one - No of document deleted is -> {}", result.getDeletedCount());
        }
    }
}
