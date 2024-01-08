package org.example.server;

import org.example.model.Package;
import org.example.model.Recipient;
import org.example.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MongoServerTestWithoutMock {
    private final MongoServer mongoServer = new MongoServer();
    List<Package> packageList = new ArrayList<>();
    List<Recipient> recipientList = new ArrayList<>();
    List<Supplier> supplierList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        mongoServer.clean();
        packageList.add(new Package(1, "testP", 5, 2, 3, 4, 1));
        packageList.add(new Package(2, "testP2", 25, 22, 23, 24, 2));
        recipientList.add(new Recipient(1, "firstNameR", "lastNameR", "addressR", "phoneNumberR", "emailR"));
        recipientList.add(new Recipient(2, "firstNameR2", "lastNameR2", "addressR2", "phoneNumberR2", "emailR2"));
        supplierList.add(new Supplier(1, "firstNameS", "lastNameS", "storeLocationS"));
        supplierList.add(new Supplier(2, "firstNameS2", "lastNameS2", "storeLocationS2"));
    }

    @Test
    public void findPackages() {
        assertThat(mongoServer.findPackages()).hasSize(0);
        mongoServer.addPackage(packageList.get(0));
        mongoServer.addPackage(packageList.get(1));
        assertThat(mongoServer.findPackages()).hasSize(2);
        assertThat(mongoServer.findPackages().get(0)).isEqualTo(packageList.get(0));
        assertThat(mongoServer.findPackages().get(1)).isEqualTo(packageList.get(1));
    }

    @Test
    public void findRecipients() {
        assertThat(mongoServer.findRecipients()).hasSize(0);
        mongoServer.addRecipient(recipientList.get(0));
        mongoServer.addRecipient(recipientList.get(1));
        assertThat(mongoServer.findRecipients()).hasSize(2);
        assertThat(mongoServer.findRecipients().get(0)).isEqualTo(recipientList.get(0));
        assertThat(mongoServer.findRecipients().get(1)).isEqualTo(recipientList.get(1));
    }

    @Test
    public void findSuppliers() {
        assertThat(mongoServer.findSuppliers()).hasSize(0);
        mongoServer.addSupplier(supplierList.get(0));
        mongoServer.addSupplier(supplierList.get(1));
        assertThat(mongoServer.findSuppliers()).hasSize(2);
        assertThat(mongoServer.findSuppliers().get(0)).isEqualTo(supplierList.get(0));
        assertThat(mongoServer.findSuppliers().get(1)).isEqualTo(supplierList.get(1));
    }

    @Test
    void addPackage() {
        assertThat(mongoServer.findPackages()).hasSize(0);
        mongoServer.addPackage(packageList.get(0));
        assertThat(mongoServer.findPackages()).hasSize(1);
        assertThat(mongoServer.findPackages().get(0)).isEqualTo(packageList.get(0));
        assertThrows(NullPointerException.class, () -> mongoServer.addPackage(new Package(0, null, 0, 0, 0, 0, 0)));
    }

    @Test
    void addRecipient() {
        assertThat(mongoServer.findRecipients()).hasSize(0);
        mongoServer.addRecipient(recipientList.get(0));
        assertThat(mongoServer.findRecipients()).hasSize(1);
        assertThat(mongoServer.findRecipients().get(0)).isEqualTo(recipientList.get(0));
        assertThrows(NullPointerException.class, () -> mongoServer.addRecipient(new Recipient(0, null, null, null, null, null)));
    }

    @Test
    void addSupplier() {
        assertThat(mongoServer.findSuppliers()).hasSize(0);
        mongoServer.addSupplier(supplierList.get(0));
        assertThat(mongoServer.findSuppliers()).hasSize(1);
        assertThat(mongoServer.findSuppliers().get(0)).isEqualTo(supplierList.get(0));
        assertThrows(NullPointerException.class, () -> mongoServer.addSupplier(new Supplier(0, null, null, null)));
    }

    @Test
    void editRecipient() {
        mongoServer.addRecipient(recipientList.get(0));
        Recipient recipientEdit = new Recipient(1, "testEdit1", "testEdit2", "testEdit3", "testEdit4", "testEdit5");
        mongoServer.editRecipient(recipientEdit);
        assertThat(mongoServer.findRecipients()).hasSize(1);
        assertThat(mongoServer.findRecipients().get(0).getId()).isEqualTo(recipientEdit.getId());
        assertThat(mongoServer.findRecipients().get(0)).isEqualTo(recipientEdit);
        assertThrows(NullPointerException.class, () -> mongoServer.editRecipient(new Recipient(0, null, null, null, null, null)));
    }

    @Test
    void editSupplier() {
        mongoServer.addSupplier(supplierList.get(0));
        Supplier supplierEdit = new Supplier(1, "testEdit1", "testEdit2", "testEdit3");
        mongoServer.editSupplier(supplierEdit);
        assertThat(mongoServer.findSuppliers()).hasSize(1);
        assertThat(mongoServer.findSuppliers().get(0).getId()).isEqualTo(supplierEdit.getId());
        assertThat(mongoServer.findSuppliers().get(0)).isEqualTo(supplierEdit);
        assertThrows(NullPointerException.class, () -> mongoServer.editSupplier(new Supplier(0, null, null, null)));
    }

    @Test
    void editPackage() {
        mongoServer.addPackage(packageList.get(0));
        Package aPackageEdit = new Package(1, "testEdit1", 2, 3, 4, 5, 1);
        mongoServer.editPackage(aPackageEdit);
        assertThat(mongoServer.findPackages()).hasSize(1);
        assertThat(mongoServer.findPackages().get(0).getId()).isEqualTo(packageList.get(0).getId());
        assertThat(mongoServer.findPackages().get(0)).isEqualTo(aPackageEdit);
        assertThrows(NullPointerException.class, () -> mongoServer.editPackage(new Package(0, null, 0, 0, 0, 0, 0)));
    }

    @Test
    void toMapPackage() {
        Package aPackage = new Package(1, "testP", 5, 2, 3, 4, 1);
        assertThat(aPackage.toMap()).isEqualTo(packageList.get(0).toMap());
        assertThat(aPackage.toMap()).isNotIn(packageList.get(1).toMap());
        assertThrows(NullPointerException.class, () -> new Package(0, null, 0, 0, 0, 0, 0).toMap());
    }

    @Test
    void toMapRecipient() {
        Recipient recipient = new Recipient(1, "firstNameR", "lastNameR", "addressR", "phoneNumberR", "emailR");
        assertThat(recipient.toMap()).isEqualTo(recipientList.get(0).toMap());
        assertThat(recipient.toMap()).isNotIn(recipientList.get(1).toMap());
        assertThrows(NullPointerException.class, () -> new Recipient(0, null, null, null, null, null).toMap());
    }

    @Test
    void toMapSupplier() {
        Supplier supplier = new Supplier(2, "firstNameS2", "lastNameS2", "storeLocationS2");
        assertThat(supplier.toMap()).isEqualTo(supplierList.get(1).toMap());
        assertThat(supplier.toMap()).isNotIn(supplierList.get(0).toMap());
        assertThrows(NullPointerException.class, () -> new Supplier(0, null, null, null).toMap());
    }

    @Test
    void deletePackage() {
        mongoServer.addPackage(packageList.get(0));
        assertThat(mongoServer.findPackages()).hasSize(1);
        mongoServer.deletePackage(1);
        assertThat(mongoServer.findPackages()).hasSize(0);
        assertThrows(NoSuchElementException.class, () -> mongoServer.deletePackage(0));
    }

    @Test
    void deleteRecipient() {
        mongoServer.addRecipient(recipientList.get(0));
        assertThat(mongoServer.findRecipients()).hasSize(1);
        mongoServer.deleteRecipient(1);
        assertThat(mongoServer.findRecipients()).hasSize(0);
        assertThrows(NoSuchElementException.class, () -> mongoServer.deleteRecipient(0));
    }

    @Test
    void deleteSupplier() {
        mongoServer.addSupplier(supplierList.get(0));
        assertThat(mongoServer.findSuppliers()).hasSize(1);
        mongoServer.deleteSupplier(1);
        assertThat(mongoServer.findSuppliers()).hasSize(0);
        assertThrows(NoSuchElementException.class, () -> mongoServer.deleteSupplier(0));
    }
}