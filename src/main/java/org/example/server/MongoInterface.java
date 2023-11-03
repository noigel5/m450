package org.example.server;

import org.bson.Document;

import java.util.List;

public interface MongoInterface {
    /**
     * @param collectionName
     * @return
     */
    List<Document> findPackageDocuments(String collectionName);

    List<Document> findRecipientDocuments(String collectionName);

    List<Document> findSupplierDocuments(String collectionName);
}
