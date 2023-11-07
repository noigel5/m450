package org.example.server;

import org.example.model.Package;
import org.example.model.Recipient;
import org.example.model.Supplier;

import java.util.List;

/**
 * Mongo Server interface
 */
public interface MongoInterface {

    List<Package> findPackages();

    List<Recipient> findRecipients();

    List<Supplier> findSuppliers();

    void addPackage(Package aPackage);

    void addRecipient(Recipient recipient);

    void addSupplier(Supplier supplier);


    void editRecipient(Recipient recipient);

    void editSupplier(Supplier supplierEdit);

    void editPackage(Package aPackageEdit);


    void deleteRecipient(int id);

    void deleteSupplier(int id);

    void deletePackage(int id);
}
