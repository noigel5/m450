package org.example.server;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.example.model.Package;
import org.example.model.Recipient;
import org.example.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class MongoServer implements MongoInterface {
    public static final String PACKAGE = "Package";
    public static final String RECIPIENT = "Recipient";
    public static final String SUPPLIER = "Supplier";
    private final MongoCollection<Document> recipientCollection;
    private final MongoCollection<Document> packageCollection;
    private final MongoCollection<Document> supplierCollection;
    private MongoDatabase database;

    public MongoServer() {

        // Create custom codec for Recipient
        RecipientCodec recipientCodec = new RecipientCodec();

        // Create custom codec for Package
        PackageCodec packageCodec = new PackageCodec();

        // Create custom codec for Supplier
        SupplierCodec supplierCodec = new SupplierCodec();

        // Register the custom codec along with the default codecs
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromCodecs(recipientCodec, packageCodec, supplierCodec)
        );

        // Configure MongoClientSettings with the custom codec registry
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(codecRegistry)
                .build();

        // Create MongoClient with custom settings
        MongoClient mongoClient = MongoClients.create(settings);
        // Connect to the MongoDB instance running in the Docker container
        if (mongoClient.getDatabase("db") != null) {

            database = mongoClient.getDatabase("db");
            database.drop();
        }
        database = mongoClient.getDatabase("db");

        //Create Collection
        database.createCollection(RECIPIENT);
        database.createCollection(PACKAGE);
        database.createCollection(SUPPLIER);
        recipientCollection = database.getCollection(RECIPIENT);
        packageCollection = database.getCollection(PACKAGE);
        supplierCollection = database.getCollection(SUPPLIER);

        //Daten erstellen

        Recipient recipient1 = new Recipient(1, "s", "d", "a", "1", "e");
        Recipient recipient2 = new Recipient(2, "s2", "d2", "a2", "12", "e2");

        Package aPackage1 = new Package(1,"asdf1", 1, 1, 1, 1, 1);
        Package aPackage2 = new Package(2,"asdf2", 2, 2, 2, 2, 1);
        Package aPackage3 = new Package(3,"asdf3", 1, 1, 1, 1, 2);
        Package aPackage4 = new Package(4,"asdf4", 2, 2, 2, 2, 2);

        Supplier supplier = new Supplier(1,"a1", "s2", "3f");

        recipientCollection.insertOne(new Document(recipient1.toMap()));
        recipientCollection.insertOne(new Document(recipient2.toMap()));

        packageCollection.insertOne(new Document(aPackage1.toMap()));
        packageCollection.insertOne(new Document(aPackage2.toMap()));
        packageCollection.insertOne(new Document(aPackage3.toMap()));
        packageCollection.insertOne(new Document(aPackage4.toMap()));

        supplierCollection.insertOne(new Document(supplier.toMap()));

    }

    @Override
    public List<Package> findPackages() {
        List<Package> packageList = new ArrayList<>();
        MongoCollection<Document> packageCollection = database.getCollection(PACKAGE);
        FindIterable<Document> find = packageCollection.find();

        for (Document document : find) {
            packageList.add(new Package(document.getString("content"), document.getInteger("weight"),
                    document.getInteger("length"), document.getInteger("depth"),
                    document.getInteger("height"), document.getInteger("recipientId")));
        }
        return packageList;
    }

    @Override
    public List<Recipient> findRecipients() {
        List<Recipient> recipientList = new ArrayList<Recipient>();
        MongoCollection<Document> collection = database.getCollection(RECIPIENT);
        FindIterable<Document> find = collection.find();
        for (Document document : find) {
            recipientList.add(new Recipient(document.getInteger("id"), document.getString("firstName"),
                    document.getString("lastName"), document.getString("address"), document.getString("phoneNumber"), document.getString("email")));

        }
        return recipientList;
    }

    @Override
    public List<Supplier> findSuppliers() {
        List<Supplier> supplierList = new ArrayList<Supplier>();
        MongoCollection<Document> collection = database.getCollection(SUPPLIER);
        FindIterable<Document> find = collection.find();
        for (Document document : find) {
            supplierList.add(new Supplier(document.getString("firstName"),
                    document.getString("lastName"), document.getString("storeLocation")));
        }
        return supplierList;
    }

    /**
     * @param aPackage
     */
    @Override
    public void addPackage(Package aPackage) {
        packageCollection.insertOne(new Document(aPackage.toMap()));
    }

    /**
     * @param recipient
     */
    @Override
    public void addRecipient(Recipient recipient) {
        recipientCollection.insertOne(new Document(recipient.toMap()));
    }

    /**
     * @param supplier
     */
    @Override
    public void addSupplier(Supplier supplier) {
        supplierCollection.insertOne(new Document(supplier.toMap()));
    }

    /**
     * @param recipientEdit
     */
    @Override
    public void editRecipient(Recipient recipientEdit) {
        Bson filter = Filters.eq("id", recipientEdit.getId());
        recipientCollection.findOneAndReplace(filter, new Document(recipientEdit.toMap()));
    }

    /**
     * @param supplierEdit
     */
    @Override
    public void editSupplier(Supplier supplierEdit) {
        Bson filter = Filters.eq("id", supplierEdit.getId());
        supplierCollection.findOneAndReplace(filter, new Document(supplierEdit.toMap()));
    }

    /**
     * @param aPackageEdit
     */
    @Override
    public void editPackage(Package aPackageEdit) {
        Bson filter = Filters.eq("id", aPackageEdit.getId());
        packageCollection.findOneAndReplace(filter, new Document(aPackageEdit.toMap()));
    }
}
