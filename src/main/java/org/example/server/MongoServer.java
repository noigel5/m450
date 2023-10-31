package org.example.server;

import com.mongodb.client.*;
import org.bson.Document;
import org.example.model.Package;
import org.example.model.Recipient;
import org.example.model.Supplier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MongoServer implements MongoInterface{
    private MongoDatabase database;

    public MongoServer(){
        // Connect to the MongoDB instance running in the Docker container
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        // Get a handle to the "myapp" database
        database = mongoClient.getDatabase("db");
        //Create Collection
        database.createCollection("Package");
        database.createCollection("Recipient");
        database.createCollection("Supplier");

        MongoCollection<Document> packageCollection = database.getCollection("Package");
        Recipient recipient1 = new Recipient("s", "d", "a", "1","e");
        Recipient recipient2 = new Recipient("s2", "d2", "a2", "12","e2");
        Package aPackage1 = new Package("asdf1",1,1,1,1, recipient1);
        Package aPackage2 = new Package("asdf2",2,2,2,2, recipient2);
        Package aPackage3 = new Package("asdf3",2,2,2,2, recipient2);
        Supplier supplier = new Supplier("a1", "s2", "3f");
        Document document = new Document();
        database.getCollection("Recipient").insertOne(document.append("r1",recipient1));
        document.clear();
        database.getCollection("Recipient").insertOne(document.append("r2",recipient2));
        document.clear();
        database.getCollection("Package").insertOne(document.append("p1",aPackage1));
        document.clear();
        database.getCollection("Package").insertOne(document.append("p2",aPackage2));
        document.clear();
        database.getCollection("Package").insertOne(document.append("p3",aPackage3));
        document.clear();
        database.getCollection("Supplier").insertOne(document.append("s1",supplier));
        document.clear();
    }

    @Override
    public List<Package> findPackageDocuments(String collectionName) {
        List<Package> result = new ArrayList<Package>();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        FindIterable<Document> find = collection.find();
        for (Document document : find) {
            result.add((Package) document.get("p1"));
            result.add((Package) document.get("p2"));
            result.add((Package) document.get("p3"));
        }
        return result;
    }

    @Override
    public List<Recipient> findRecipientDocuments(String collectionName) {
        List<Recipient> result = new ArrayList<Recipient>();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        FindIterable<Document> find = collection.find();
        for (Document document : find) {
            result.add((Recipient) document.get("r1"));
            result.add((Recipient) document.get("r2"));
        }
        return result;
    }

    @Override
    public List<Supplier> findSupplierDocuments(String collectionName) {
        List<Supplier> result = new ArrayList<Supplier>();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        FindIterable<Document> find = collection.find();
        for (Document document : find) {
            result.add((Supplier) document.get("s1"));
        }
        return result;
    }
}
