package org.example.server;

import com.mongodb.client.*;
import org.bson.Document;
import org.example.model.Package;
import org.example.model.Recipient;
import org.example.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class MongoServer implements MongoInterface{
    private MongoDatabase database;

    public MongoServer(){
        // Connect to the MongoDB instance running in the Docker container
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        // Get a handle to the "myapp" database
        database = mongoClient.getDatabase("db");
        //TODO: Datenbank zum laufen kriegen
        //Create Collection
        database.createCollection("Package");
        database.createCollection("Recipient");
        database.createCollection("Supplier");

        MongoCollection<Document> recipientCollection = database.getCollection("Recipient");
        MongoCollection<Document> packageCollection = database.getCollection("Package");
        MongoCollection<Document> supplierCollection = database.getCollection("Supplier");

        //Daten erstellen
        Recipient recipient1 = new Recipient(1, "s", "d", "a", "1", "e");
        Recipient recipient2 = new Recipient(2, "s2", "d2", "a2", "12", "e2");

        Package aPackage1 = new Package("asdf1", 1, 1, 1, 1, 1);
        Package aPackage2 = new Package("asdf2", 2, 2, 2, 2, 1);
        Package aPackage3 = new Package("asdf3", 1, 1, 1, 1, 2);
        Package aPackage4 = new Package("asdf4", 2, 2, 2, 2, 2);

        Supplier supplier = new Supplier("a1", "s2", "3f");
        Document document = new Document();

        recipientCollection.insertOne(document.append("r1", recipient1));
        recipientCollection.insertOne(document.append("r2", recipient2));

        packageCollection.insertOne(document.append("p1", aPackage1));
        packageCollection.insertOne(document.append("p2", aPackage2));
        packageCollection.insertOne(document.append("p3", aPackage3));
        packageCollection.insertOne(document.append("p4", aPackage4));

        supplierCollection.insertOne(document.append("s1", supplier));
    }

    @Override
    public List<Document> findPackageDocuments(String collectionName) {
        MongoCollection<Document> packageCollection = database.getCollection("Package");
        FindIterable<Document> find = packageCollection.find();
        List<Document> documents = null;
        for (Document document : find) {
            if (document != null) {
                documents.add(document);
            }
        }
        return documents;
    }

    @Override
    public List<Document> findRecipientDocuments(String collectionName) {
        List<Recipient> result = new ArrayList<Recipient>();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        FindIterable<Document> find = collection.find();
        for (Document document : find) {
            result.add((Recipient) document.get("r1"));
            result.add((Recipient) document.get("r2"));
        }
        return null;
    }

    @Override
    public List<Document> findSupplierDocuments(String collectionName) {
        List<Supplier> result = new ArrayList<Supplier>();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        FindIterable<Document> find = collection.find();
        for (Document document : find) {
            result.add((Supplier) document.get("s1"));
        }
        return null;
    }
}
