package org.example.server;

import org.jsmart.zerocode.core.domain.LoadWith;
import org.jsmart.zerocode.core.domain.TestMapping;
import org.jsmart.zerocode.core.domain.TestMappings;
import org.jsmart.zerocode.jupiter.extension.ParallelLoadExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@LoadWith("load_config.properties")
@ExtendWith({ParallelLoadExtension.class})
public class LoadTest {
    @Test
    @TestMappings({
            @TestMapping(testClass = MongoServerTest.class, testMethod = "findPackages"),
            @TestMapping(testClass = MongoServerTest.class, testMethod = "findRecipients"),
            @TestMapping(testClass = MongoServerTest.class, testMethod = "findSuppliers"),
            @TestMapping(testClass = MongoServerTest.class, testMethod = "addPackages"),
            @TestMapping(testClass = MongoServerTest.class, testMethod = "addRecipients"),
            @TestMapping(testClass = MongoServerTest.class, testMethod = "addSuppliers"),
            @TestMapping(testClass = MongoServerTest.class, testMethod = "editPackages"),
            @TestMapping(testClass = MongoServerTest.class, testMethod = "editRecipients"),
            @TestMapping(testClass = MongoServerTest.class, testMethod = "editSuppliers"),
            @TestMapping(testClass = MongoServerTest.class, testMethod = "deletePackages"),
            @TestMapping(testClass = MongoServerTest.class, testMethod = "deleteRecipients"),
            @TestMapping(testClass = MongoServerTest.class, testMethod = "deleteSuppliers")
    })
    public void mongoLoadTests() {
        // This space remains empty
    }
}