package com.shrey.mongo.core.aggregate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;


@Slf4j
public class MatchAndGroupDemo {
    public static void main(String[] args) throws Exception {
        String connectionString = System.getenv("MONGO_URI");

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // Match & Group aggregation
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
            MongoCollection<Document> inspectionsCollection = sampleTrainingDB.getCollection("inspections");

            // Prepare match query
            Bson match = Aggregates.match(
                    Filters.eq("result", "Fail")
            );

            // Prepare group query
            Bson group = Aggregates.group(
                    "$sector",
                    Accumulators.sum("CountZip", 1),
                    Accumulators.sum("SumZip", "$address.zip"),
                    Accumulators.avg("AvgZip", "$address.zip")
            );

            // Execute aggregation pipeline using match and group query
            inspectionsCollection.aggregate(
                    Arrays.asList(
                            match,
                            group
                    )
            ).forEach(d -> {
                log.info(d.toJson());
            });
        }
    }
}
