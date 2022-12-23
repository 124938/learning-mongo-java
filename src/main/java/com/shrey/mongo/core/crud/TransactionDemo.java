package com.shrey.mongo.core.crud;

import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.conversions.Bson;

import java.util.UUID;

@Slf4j
public class TransactionDemo {
    public static void main(String[] args) {
        String connectionString = System.getenv("MONGO_URI");

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {

            // Get collection details
            MongoCollection accountsCollection = mongoClient
                    .getDatabase("sample_analytics")
                    .getCollection("accounts");

            try (ClientSession mongoClientSession = mongoClient.startSession()) {
                mongoClientSession.withTransaction(() -> {

                    String transactionId = UUID.randomUUID().toString();

                    // Prepare from account filter & update command
                    Bson fromAccountFilterQuery = Filters.eq("account_id", 45454545);
                    Bson fromAccountUpdateQuery = Updates.combine(
                            Updates.inc("balance", -200),
                            Updates.set("transaction_id", transactionId)
                    );

                    // Prepare to account filter & update command
                    Bson toAccountFilterQuery = Filters.eq("account_id", 46464646);
                    Bson toAccountUpdateQuery = Updates.combine(
                            Updates.inc("balance", 200),
                            Updates.set("transaction_id", transactionId)
                    );

                    // Execute delete command
                    UpdateResult fromAccountUpdateResult = accountsCollection.updateOne(fromAccountFilterQuery, fromAccountUpdateQuery);
                    UpdateResult toAccountUpdateResult = accountsCollection.updateOne(toAccountFilterQuery, toAccountUpdateQuery);

                    // Print result
                    log.info("fromAccountUpdateResult - No of document updated is -> {}", fromAccountUpdateResult.getModifiedCount());
                    log.info("toAccountUpdateResult - No of document updated is -> {}", toAccountUpdateResult.getModifiedCount());

                    return "success";
                });
            }
        }
    }
}
