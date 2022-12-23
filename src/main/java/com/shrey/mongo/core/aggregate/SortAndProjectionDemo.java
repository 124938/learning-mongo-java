package com.shrey.mongo.core.aggregate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;


@Slf4j
public class SortAndProjectionDemo {
    public static void main(String[] args) throws Exception {
        String connectionString = System.getenv("MONGO_URI");

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // Match & Group aggregation
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
            MongoCollection<Document> inspectionsCollection = sampleTrainingDB.getCollection("inspections");

            // Prepare match query
            Bson match = Aggregates.match(
                    Filters.and(
                        Filters.eq("result", "Pass"),
                        Filters.eq("address.city", "LAWRENCE")
                    )
            );

            // Prepare sort query
            Bson sort = Aggregates.sort(
                    Sorts.orderBy(
                            Sorts.ascending("sector"),
                            Sorts.descending("certificate_number")
                    )
            );

            // Prepare projection query
            Bson projection = Aggregates.project(
                    Projections.fields(
                            Projections.excludeId(),
                            Projections.include(
                                    "id",
                                    "sector",
                                    "certificate_number",
                                    "business_name",
                                    "date"
                            )
                    )
            );

            // Execute aggregation pipeline using match and group query
            inspectionsCollection.aggregate(
                    Arrays.asList(
                            match,
                            sort,
                            projection
                    )
            ).forEach(d -> {
                log.info(d.toJson());
            });
        }
    }
}
