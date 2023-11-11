package org.example.server;

import org.example.model.Package;
import org.example.model.Recipient;
import org.example.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MongoServerTest {
    private final MockMongoServer mongoServerMock = new MockMongoServer();
    List<Package> packageList = new ArrayList<>();
    List<Recipient> recipientList = new ArrayList<>();
    List<Supplier> supplierList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        packageList.add(new Package(1, "testP", 5, 2, 3, 4, 1));
        packageList.add(new Package(2, "testP2", 25, 22, 23, 24, 2));
        recipientList.add(new Recipient(1, "firstNameR", "lastNameR", "addressR", "phoneNumberR", "emailR"));
        recipientList.add(new Recipient(2, "firstNameR2", "lastNameR2", "addressR2", "phoneNumberR2", "emailR2"));
//        recipientList.add();
        supplierList.add(new Supplier(1, "firstNameS", "lastNameS", "storeLocationS"));
        supplierList.add(new Supplier(2, "firstNameS2", "lastNameS2", "storeLocationS2"));
//        supplierList.add(new Supplier(0, null, null, null));
    }

    @Test
    void findPackages() {
        assertThat(mongoServerMock.findPackages()).hasSize(0);
        mongoServerMock.addPackage(packageList.get(0));
        mongoServerMock.addPackage(packageList.get(1));
        assertThat(mongoServerMock.findPackages()).hasSize(2);
        assertThat(mongoServerMock.findPackages().get(0)).isEqualTo(packageList.get(0));
        assertThat(mongoServerMock.findPackages().get(1)).isEqualTo(packageList.get(1));
    }

    @Test
    void findRecipients() {
        assertThat(mongoServerMock.findRecipients()).hasSize(0);
        mongoServerMock.addRecipient(recipientList.get(0));
        mongoServerMock.addRecipient(recipientList.get(1));
        assertThat(mongoServerMock.findRecipients()).hasSize(2);
        assertThat(mongoServerMock.findRecipients().get(0)).isEqualTo(recipientList.get(0));
        assertThat(mongoServerMock.findRecipients().get(1)).isEqualTo(recipientList.get(1));
    }

    @Test
    void findSuppliers() {
        assertThat(mongoServerMock.findSuppliers()).hasSize(0);
        mongoServerMock.addSupplier(supplierList.get(0));
        mongoServerMock.addSupplier(supplierList.get(1));
        assertThat(mongoServerMock.findSuppliers()).hasSize(2);
        assertThat(mongoServerMock.findSuppliers().get(0)).isEqualTo(supplierList.get(0));
        assertThat(mongoServerMock.findSuppliers().get(1)).isEqualTo(supplierList.get(1));
    }

    @Test
    void addPackage() {
        assertThat(mongoServerMock.findPackages()).hasSize(0);
        mongoServerMock.addPackage(packageList.get(0));
        assertThat(mongoServerMock.findPackages()).hasSize(1);
        assertThat(mongoServerMock.findPackages().get(0)).isEqualTo(packageList.get(0));
        assertThrows(NullPointerException.class, () -> mongoServerMock.addPackage(new Package(0,null, 0,0,0,0,0)));
        //assertThat(mongoServerMock.findPackages().get(1)).isEqualTo(packageList.get(2));
    }

    @Test
    void addRecipient() {
        assertThat(mongoServerMock.findRecipients()).hasSize(0);
        mongoServerMock.addRecipient(recipientList.get(0));
        assertThat(mongoServerMock.findRecipients()).hasSize(1);
        assertThat(mongoServerMock.findRecipients().get(0)).isEqualTo(recipientList.get(0));
        assertThrows(NullPointerException.class, () -> mongoServerMock.addRecipient(new Recipient(0, null, null, null, null, null)));
    }

    @Test
    void addSupplier() {
        assertThat(mongoServerMock.findSuppliers()).hasSize(0);
        mongoServerMock.addSupplier(supplierList.get(0));
        assertThat(mongoServerMock.findSuppliers()).hasSize(1);
        assertThat(mongoServerMock.findSuppliers().get(0)).isEqualTo(supplierList.get(0));
        assertThrows(NullPointerException.class, () -> mongoServerMock.addSupplier(new Supplier(0, null, null, null)));
    }

    @Test
    void editRecipient() {
        mongoServerMock.addRecipient(recipientList.get(0));
        Recipient recipientEdit = new Recipient(1, "testEdit1", "testEdit2", "testEdit3", "testEdit4", "testEdit5");
        mongoServerMock.editRecipient(recipientEdit);
        assertThat(mongoServerMock.findRecipients()).hasSize(1);
        assertThat(mongoServerMock.findRecipients().get(0).getId()).isEqualTo(recipientEdit.getId());
        assertThat(mongoServerMock.findRecipients().get(0)).isEqualTo(recipientEdit);
        assertThrows(NullPointerException.class, () -> mongoServerMock.editRecipient(new Recipient(0, null, null, null, null, null)));

    }

    @Test
    void editSupplier() {
        mongoServerMock.addSupplier(supplierList.get(0));
        Supplier supplierEdit = new Supplier(1, "testEdit1", "testEdit2", "testEdit3");
        mongoServerMock.editSupplier(supplierEdit);
        assertThat(mongoServerMock.findSuppliers()).hasSize(1);
        assertThat(mongoServerMock.findSuppliers().get(0).getId()).isEqualTo(supplierEdit.getId());
        assertThat(mongoServerMock.findSuppliers().get(0)).isEqualTo(supplierEdit);
        assertThrows(NullPointerException.class, () -> mongoServerMock.editSupplier(new Supplier(0, null, null, null)));

    }

    @Test
    void editPackage() {
        mongoServerMock.addPackage(packageList.get(0));
        Package aPackageEdit = new Package(1, "testEdit1", 2, 3, 4, 5, 1);
        mongoServerMock.editPackage(aPackageEdit);
        assertThat(mongoServerMock.findPackages()).hasSize(1);
        assertThat(mongoServerMock.findPackages().get(0).getId()).isEqualTo(packageList.get(0).getId());
        assertThat(mongoServerMock.findPackages().get(0)).isEqualTo(aPackageEdit);
        assertThrows(NullPointerException.class, () -> mongoServerMock.editPackage(new Package(0,null, 0,0,0,0,0)));

    }

    @Test
    void toMapPackage(){
        Package aPackage=new Package(1, "testP", 5, 2, 3, 4, 1);
        assertThat(aPackage.toMap()).isEqualTo(packageList.get(0).toMap());
    }

    @Test
    void toMapRecipient(){
        Recipient recipient=new Recipient(1, "firstNameR", "lastNameR", "addressR", "phoneNumberR", "emailR");
        assertThat(recipient.toMap()).isEqualTo(recipientList.get(0).toMap());

    }

    @Test
    void toMapSupplier(){
        Supplier supplier=new Supplier(2, "firstNameS2", "lastNameS2", "storeLocationS2");
        assertThat(supplier.toMap()).isEqualTo(supplierList.get(1).toMap());
    }

    @Test
    void deletePackage(){
       mongoServerMock.deletePackage(1);
       assertThat(mongoServerMock.findPackages()).hasSize(1);
    }

    @Test
    void deleteRecipient(){
        mongoServerMock.deleteRecipient(1);
        assertThat(mongoServerMock.findRecipients()).hasSize(1);

    }

    @Test
    void deleteSupplier(){
        mongoServerMock.deleteSupplier(1);
        assertThat(mongoServerMock.findSuppliers()).hasSize(1);
    }
}