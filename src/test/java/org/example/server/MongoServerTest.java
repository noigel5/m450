package org.example.server;

import org.example.model.Package;
import org.example.model.Recipient;
import org.example.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MongoServerTest {
    private MockMongoServer mongoServerMock = new MockMongoServer();
    List<Package> packageList = new ArrayList<>();
    List<Recipient> recipientList = new ArrayList<>();
    List<Supplier> supplierList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        packageList.add(new Package(1, "test", 1, 1, 1, 1, 2));
        recipientList.add(new Recipient(1, "test", "test", "test", "test", "test"));
        supplierList.add(new Supplier(1, "test", "test", "test"));
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
        mongoServerMock.addPackage(packageList.get(0));

        assertThat(mongoServerMock.findPackages()).hasSize(1);
    }

    @Test
    void addRecipient() {
        mongoServerMock.addRecipient(recipientList.get(0));

        assertThat(mongoServerMock.findRecipients()).hasSize(1);
    }

    @Test
    void addSupplier() {
        mongoServerMock.addSupplier(supplierList.get(0));

        assertThat(mongoServerMock.findSuppliers()).hasSize(1);
    }

    @Test
    void editRecipient() {
        mongoServerMock.addRecipient(recipientList.get(0));
        Recipient recipientEdit = new Recipient(1, "testEdit", "testEdit", "testEdit", "testEdit", "testEdit");
        mongoServerMock.editRecipient(recipientEdit);
        assertThat(mongoServerMock.findRecipients()).hasSize(1);
        assertThat(mongoServerMock.findRecipients().get(0)).isEqualTo(recipientEdit);
    }

    @Test
    void editSupplier() {
        mongoServerMock.addSupplier(supplierList.get(0));
        Supplier supplierEdit = new Supplier(1, "testEdit", "testEdit", "testEdit");
        mongoServerMock.editSupplier(supplierEdit);
        assertThat(mongoServerMock.findSuppliers()).hasSize(1);
        assertThat(mongoServerMock.findSuppliers().get(0)).isEqualTo(supplierEdit);
    }

    @Test
    void editPackage() {
        mongoServerMock.addPackage(packageList.get(0));
        Package aPackageEdit = new Package(1, "testEdit", 1, 1, 1, 1, 1);
        mongoServerMock.editPackage(aPackageEdit);
        assertThat(mongoServerMock.findPackages()).hasSize(1);
        assertThat(mongoServerMock.findPackages().get(0)).isEqualTo(aPackageEdit);
    }
}