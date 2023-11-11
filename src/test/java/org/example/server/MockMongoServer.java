package org.example.server;

import org.example.model.Package;
import org.example.model.Recipient;
import org.example.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class MockMongoServer implements MongoInterface {
    private List<Package> packages = new ArrayList<>();
    private List<Recipient> recipients = new ArrayList<>();
    private List<Supplier> suppliers = new ArrayList<>();

    @Override
    public List<Package> findPackages() {
        return packages;
    }

    @Override
    public List<Recipient> findRecipients() {
        return recipients;
    }

    @Override
    public List<Supplier> findSuppliers() {
        return suppliers;
    }

    @Override
    public void addPackage(Package aPackage) {
        packages.add(aPackage);
    }

    @Override
    public void addRecipient(Recipient recipient) {
        recipients.add(recipient);
    }

    @Override
    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    @Override
    public void editRecipient(Recipient recipient) {
        for (int i = 0; i < recipients.size(); i++) {
            Recipient existingRecipient = recipients.get(i);

            // Assuming Recipient has an ID field (replace with actual ID field name)
            if (existingRecipient.getId() == recipient.getId()) {
                recipients.get(i).setFirstName(recipient.getFirstName());
                recipients.get(i).setLastName(recipient.getLastName());
                recipients.get(i).setAddress(recipient.getAddress());
                recipients.get(i).setPhoneNumber(recipient.getPhoneNumber());
                recipients.get(i).setEmail(recipient.getEmail());
            }
        }
    }

    @Override
    public void editSupplier(Supplier supplierEdit) {

        for (int i = 0; i < suppliers.size(); i++) {
            Supplier existingRecipient = suppliers.get(i);

            // Assuming Recipient has an ID field (replace with actual ID field name)
            if (existingRecipient.getId() == supplierEdit.getId()) {
                suppliers.get(i).setFirstName(supplierEdit.getFirstName());
                suppliers.get(i).setLastName(supplierEdit.getLastName());
                suppliers.get(i).setStoreLocation(supplierEdit.getStoreLocation());
            }
        }
    }

    @Override
    public void editPackage(Package aPackageEdit) {
        for (int i = 0; i < packages.size(); i++) {
            Package existingPackage = packages.get(i);

            // Assuming Recipient has an ID field (replace with actual ID field name)
            if (existingPackage.getId() == aPackageEdit.getId()) {
                packages.get(i).setContent(aPackageEdit.getContent());
                packages.get(i).setLength(aPackageEdit.getLength());
                packages.get(i).setHeight(aPackageEdit.getHeight());
                packages.get(i).setDepth(aPackageEdit.getDepth());
                packages.get(i).setWeight(aPackageEdit.getWeight());
                packages.get(i).setRecipientId(aPackageEdit.getRecipientId());
            }
        }
    }

    @Override
    public void deleteRecipient(int id) {
        recipients.remove(recipients.stream()
                .filter(recipient -> recipient.getId()==id)
                .findFirst()
                .orElse(null) );
    }

    @Override
    public void deleteSupplier(int id) {
        suppliers.remove(suppliers.stream()
                .filter(supplier -> supplier.getId()==id)
                .findFirst()
                .orElse(null) );
    }

    @Override
    public void deletePackage(int id) {
        packages.remove(packages.stream()
                .filter(aPackage -> aPackage.getId()==id)
                .findFirst()
                .orElse(null) );
    }
}
