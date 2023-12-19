package org.example.server;

import org.jsmart.zerocode.core.domain.LoadWith;
import org.jsmart.zerocode.core.domain.TestMapping;
import org.jsmart.zerocode.core.domain.TestMappings;
import org.jsmart.zerocode.jupiter.extension.ParallelLoadExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@LoadWith("load_config.properties")
@ExtendWith({ParallelLoadExtension.class})
public class LoadTest {
    @Test
    @DisplayName("parallel load for MongoServerTest scenarios")
    @TestMappings({
            @TestMapping(testClass = MongoServerTest.class, testMethod = "findPackages"),
            @TestMapping(testClass = MongoServerTest.class, testMethod = "findRecipients"),
            @TestMapping(testClass = MongoServerTest.class, testMethod = "findSuppliers")
    })
    public void mongoLoadTests() {
        // This space remains empty
    }
}