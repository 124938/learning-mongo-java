package com.shrey.mongo.core.crud;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;


@Slf4j
public class FindDemo {
    public static void main(String[] args) throws Exception {
        String connectionString = System.getenv("MONGO_URI");

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // InsertOne demo
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_analytics");
            MongoCollection<Document> customersCollection = sampleTrainingDB.getCollection("customers");

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);

            // Execute find query
            customersCollection.find(
                    Filters.and(
                            Filters.regex("name", "ja"),
                            Filters.gt("birthdate", format.parse("1970-01-01T00:00:00Z"))
                    )
            ).forEach(doc -> {
                log.info("find() - {}", doc.toJson());
            });

            // Execute find query with first record to be fetched from cursor
            Document firstDoc = customersCollection.find(
                    Filters.in("active", true)
            ).first();
            log.info("find().first() - First document available in collection is -> {}",firstDoc);
        }
    }
}
