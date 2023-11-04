package org.example.server;

import org.example.model.Package;
import org.example.model.Recipient;
import org.example.model.Supplier;

import java.util.List;

public interface MongoInterface {

    List<Package> findPackages();

    List<Recipient> findRecipients();

    List<Supplier> findSuppliers();

    void addPackage(Package aPackage);

    void addRecipient(Recipient recipient);

    void addSupplier(Supplier supplier);



}
