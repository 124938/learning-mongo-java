package com.shrey.mongo.core.crud;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class InsertOneDemo {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://admin:admin@my-first-mongodb-cluste.78ca9qb.mongodb.net/?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // InsertOne demo
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
            MongoCollection<Document> inspectionsCollection = sampleTrainingDB.getCollection("inspections");

            // Execute insert one command
            InsertOneResult result = inspectionsCollection.insertOne(
                    new Document("_id", new ObjectId())
                            .append("id", "10022-25222-14855")
                            .append("certificate_number", 9278661)
                            .append("business_name", "Shreyash business coop")
                            .append("date", Date.from(LocalDate.of(2022, Month.DECEMBER, 21).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                            .append("result", "Not impressive")
                            .append("sector", "Software retailer")
                            .append("address",
                                    new Document()
                                            .append("city", "pune")
                                            .append("zip", 411057)
                                            .append("street", "16 no bus stop")
                                            .append("number", 106)
                            )
            );

            // Print result
            log.info("Insert one - Inserted id is -> {}", result.getInsertedId());
        }
    }
}
