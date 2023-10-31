package org.example.server;
import java.util.List;

import org.bson.Document;
import org.example.model.Package;
import org.example.model.Recipient;
import org.example.model.Supplier;

public interface MongoInterface {
    /**
     * @param collectionName
     * @return
     */
    List<Package> findPackageDocuments(String collectionName);
    List<Recipient> findRecipientDocuments(String collectionName);
    List<Supplier> findSupplierDocuments(String collectionName);
}
