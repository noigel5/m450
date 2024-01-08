package org.example.gatling;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoTest {
    public static void testMongo() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");
        MongoDatabase database = mongoClient.getDatabase("db");
        MongoCollection<Document> collection = database.getCollection("Supplier");

        collection.find();
        // Perform MongoDB operation (e.g., find documents)
        // ...
    }
}