package org.example.server;

import org.example.model.Package;
import org.example.model.Recipient;
import org.example.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MongoServerTest {

    private MongoServer mongoServer = new MongoServer();

    @BeforeEach
    void setUp() {
    }

    @Test
    void findPackages() {
        assertThat(mongoServer.findPackages()).hasSize(4);
    }

    @Test
    void findRecipients() {
        assertThat(mongoServer.findRecipients()).hasSize(2);
    }

    @Test
    void findSuppliers() {
        assertThat(mongoServer.findSuppliers()).hasSize(1);
    }

    @Test
    void addPackage() {
        mongoServer.addPackage(new Package("test", 1, 1, 1, 1, 2));

        assertThat(mongoServer.findPackages()).hasSize(5);
    }

    @Test
    void addRecipient() {
        mongoServer.addRecipient(new Recipient(3, "test", "test", "test", "test", "test"));

        assertThat(mongoServer.findRecipients()).hasSize(3);
    }

    @Test
    void addSupplier() {
        mongoServer.addSupplier(new Supplier(2, "test", "test", "test"));

        assertThat(mongoServer.findSuppliers()).hasSize(2);
    }

    @Test
    void editRecipient() {
        Recipient recipientEdit = new Recipient(1, "test", "test", "test", "test", "test");
        mongoServer.editRecipient(recipientEdit);
        assertThat(mongoServer.findRecipients().contains(recipientEdit));
    }

    @Test
    void editSupplier() {
        Supplier supplierEdit = new Supplier(1, "test", "test", "test");
        mongoServer.editSupplier(supplierEdit);
        assertThat(mongoServer.findSuppliers().contains(supplierEdit));
    }

    @Test
    void editPackage() {
        Package aPackageEdit = new Package(1, "test", 1, 1, 1,1,1);
        mongoServer.editPackage(aPackageEdit);
        assertThat(mongoServer.findPackages().contains(aPackageEdit));
    }
}