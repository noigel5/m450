package org.example.server;

import org.example.model.Package;
import org.example.model.Recipient;
import org.example.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;


class MongoServerTest {
    private MongoServer mongoServerMock = mock(MongoServer.class);
    List<Package> packageList = new ArrayList<>();
    List<Recipient> recipientList = new ArrayList<>();
    List<Supplier> supplierList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        packageList.add(new Package("test", 1, 1, 1, 1, 2));
        recipientList.add(new Recipient(3, "test", "test", "test", "test", "test"));
        supplierList.add(new Supplier(2, "test", "test", "test"));
    }

    @Test
    void findPackages() {
        assertThat(mongoServerMock.findPackages()).hasSize(0);
    }

    @Test
    void findRecipients() {
        assertThat(mongoServerMock.findRecipients()).hasSize(0);
    }

    @Test
    void findSuppliers() {
        assertThat(mongoServerMock.findSuppliers()).hasSize(0);
    }

    @Test
    void addPackage() {

    }

    @Test
    void addRecipient() {

    }

    @Test
    void addSupplier() {

    }

    @Test
    void editRecipient() {
        Recipient recipientEdit = new Recipient(1, "test", "test", "test", "test", "test");
        mongoServerMock.editRecipient(recipientEdit);
        boolean containsRecipient = mongoServerMock.findRecipients().contains(recipientEdit);
        assert containsRecipient : "Recipient not found in the list";
    }

    @Test
    void editSupplier() {
        Supplier supplierEdit = new Supplier(1, "test", "test", "test");
        mongoServerMock.editSupplier(supplierEdit);
        boolean containsSupplier = mongoServerMock.findSuppliers().contains(supplierEdit);
        assert containsSupplier : "Supplier not found in the list";
    }

    @Test
    void editPackage() {
        Package aPackageEdit = new Package(1, "test", 1, 1, 1, 1, 1);
        mongoServerMock.editPackage(aPackageEdit);
        boolean containsPackage = mongoServerMock.findPackages().contains(aPackageEdit);
        assert containsPackage : "Package not found in the list";
    }
}