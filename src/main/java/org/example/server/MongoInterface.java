package org.example.server;
import java.util.List;

import org.bson.Document;

public interface MongoInterface {
    /**
     * @param collectionName
     * @return
     */
    List<String> findDocuments(String collectionName);
}
