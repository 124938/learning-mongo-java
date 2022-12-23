package com.shrey.mongo.core.crud;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class UpdatedOneDemo {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://admin:admin@my-first-mongodb-cluste.78ca9qb.mongodb.net/?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // InsertOne demo
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
            MongoCollection<Document> inspectionsCollection = sampleTrainingDB.getCollection("inspections");

            // Prepare filter command
            Bson filterQuery = Filters.eq("id", "10022-25222-14855");

            // Prepare update command
            Bson updateQuery = Updates.combine(
                    Updates.set("certificate_number", 855555),
                    Updates.set("result", "Not that good")
            );
            // Execute update one command
            UpdateResult result = inspectionsCollection.updateOne(
                    filterQuery,
                    updateQuery
            );

            // Print result
            log.info("Update many - Matched count is -> {}", result.getMatchedCount());
            log.info("Update many - Modified count is -> {}", result.getModifiedCount());
        }
    }
}
