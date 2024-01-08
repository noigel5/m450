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
            @TestMapping(testClass = MongoServerTestWithoutMock.class, testMethod = "findPackages"),
            @TestMapping(testClass = MongoServerTestWithoutMock.class, testMethod = "findRecipients"),
            @TestMapping(testClass = MongoServerTestWithoutMock.class, testMethod = "findSuppliers"),
            @TestMapping(testClass = MongoServerTestWithoutMock.class, testMethod = "addPackages"),
            @TestMapping(testClass = MongoServerTestWithoutMock.class, testMethod = "addRecipients"),
            @TestMapping(testClass = MongoServerTestWithoutMock.class, testMethod = "addSuppliers"),
            @TestMapping(testClass = MongoServerTestWithoutMock.class, testMethod = "editPackages"),
            @TestMapping(testClass = MongoServerTestWithoutMock.class, testMethod = "editRecipients"),
            @TestMapping(testClass = MongoServerTestWithoutMock.class, testMethod = "editSuppliers"),
            @TestMapping(testClass = MongoServerTestWithoutMock.class, testMethod = "deletePackages"),
            @TestMapping(testClass = MongoServerTestWithoutMock.class, testMethod = "deleteRecipients"),
            @TestMapping(testClass = MongoServerTestWithoutMock.class, testMethod = "deleteSuppliers")
    })
    public void mongoLoadTests() {
        // This space remains empty
    }
}