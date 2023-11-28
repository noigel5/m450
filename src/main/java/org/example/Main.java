package org.example;

import org.example.model.Package;
import org.example.model.Recipient;
import org.example.model.Supplier;
import org.example.server.MongoInterface;
import org.example.server.MongoServer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MongoInterface db = new MongoServer();

        System.out.println("""
                getRecipients -> show all Recipients
                getSuppliers -> show all Suppliers
                getPackages -> show all Packages 
                                
                createRecipient first_name last_name address phone_number email-> create new Recipient
                createSupplier first_name last_name store_location-> create new Supplier
                createPackage content weight length depth height recipient_id -> create new Package
                                
                editRecipient id(old_value) first_name last_name address phone_number email-> edit values from Recipient
                editSupplier id(old_value) first_name last_name store_location-> edit values from Supplier
                editPackage id(old_value) content weight length depth height recipient_id -> edit values from Package
                                
                deleteRecipient id(existing_value)-> delete Recipient
                deleteSupplier id(existing_value)-> delete Supplier
                deletePackage id(existing_value)-> delete Package
                
                exit -> exit program                
                """);
        boolean exit = false;

        do {

            String[] nextLine = scanner.nextLine().split(" ");
            Validation validation = new Validation();

            for (int i = 0; i < nextLine.length; i++) {
                switch (nextLine[i]) {
                    case "getRecipients":
                        if (validation.inputAmount(nextLine.length, 1)){
                            db.findRecipients().forEach(Recipient::print);
                        }
                        break;
                    case "getSuppliers":
                        if (validation.inputAmount(nextLine.length, 1)){
                            db.findSuppliers().forEach(Supplier::print);
                        }
                        break;
                    case "getPackages":
                        if (validation.inputAmount(nextLine.length, 1)){
                            db.findPackages().forEach(Package::print);
                        }
                        break;
                    case "createRecipient":
                        if (validation.inputAmount(nextLine.length, 6)){
                            Recipient recipient = new Recipient(db.findRecipients().size() + 1, nextLine[i + 1], nextLine[i + 2], nextLine[i + 3], nextLine[i + 4], nextLine[i + 5]);
                            db.addRecipient(recipient);
                            recipient.print();
                        }
                        break;
                    case "createSupplier":
                        if (validation.inputAmount(nextLine.length, 4)){
                            Supplier supplier = new Supplier(db.findSuppliers().size() + 1, nextLine[i + 1], nextLine[i + 2], nextLine[i + 3]);
                            db.addSupplier(supplier);
                            supplier.print();
                        }
                        break;
                    case "createPackage":
                        if (validation.inputAmount(nextLine.length, 7)){
                            if (validation.number(nextLine[i + 2], 2) && validation.number(nextLine[i + 3], 3) && validation.number(nextLine[i + 4], 4) && validation.number(nextLine[i + 5], 5) && validation.number(nextLine[i + 6], 6)) {
                                Package aPackage = new Package(db.findPackages().size() + 1, nextLine[i + 1], Integer.parseInt(nextLine[i + 2]), Integer.parseInt(nextLine[i + 3]), Integer.parseInt(nextLine[i + 4]), Integer.parseInt(nextLine[i + 5]), Integer.parseInt(nextLine[i + 6]));
                                db.addPackage(aPackage);
                                aPackage.print();
                            }
                        }
                        break;
                    case "editRecipient":
                        if (validation.inputAmount(nextLine.length, 7)){
                            if (validation.number(nextLine[i + 1], 1)){
                                Recipient recipientEdit = new Recipient(Integer.parseInt(nextLine[i + 1]), nextLine[i + 2], nextLine[i + 3], nextLine[i + 4], nextLine[i + 5], nextLine[i + 6]);
                                db.editRecipient(recipientEdit);
                                recipientEdit.print();
                            }
                        }
                        break;
                    case "editSupplier":
                        if (validation.inputAmount(nextLine.length, 5)){
                            if (validation.number(nextLine[i + 1], 4)){
                                Supplier supplierEdit = new Supplier(Integer.parseInt(nextLine[i + 1]), nextLine[i + 2], nextLine[i + 3], nextLine[i + 4]);
                                db.editSupplier(supplierEdit);
                                supplierEdit.print();
                            }
                        }
                        break;
                    case "editPackage":
                        if (validation.inputAmount(nextLine.length, 7)){
                            if (validation.number(nextLine[i + 1], 1) && validation.number(nextLine[i + 3], 3) && validation.number(nextLine[i + 4], 4) && validation.number(nextLine[i + 5], 5) && validation.number(nextLine[i + 6], 6)) {
                                Package aPackageEdit = new Package(Integer.parseInt(nextLine[i + 1]), nextLine[i + 2], Integer.parseInt(nextLine[i + 3]), Integer.parseInt(nextLine[i + 4]), Integer.parseInt(nextLine[i + 5]), Integer.parseInt(nextLine[i + 6]), Integer.parseInt(nextLine[i + 6]));
                                db.editPackage(aPackageEdit);
                                aPackageEdit.print();
                            }
                        }
                        break;
                    case "deleteRecipient":
                        if (validation.inputAmount(nextLine.length, 2)){
                            if (validation.number(nextLine[i + 1], 1)) {
                                db.deleteRecipient(Integer.parseInt(nextLine[i + 1]));
                                db.findRecipients().forEach(Recipient::print);
                            }
                        }
                        break;
                    case "deleteSupplier":
                        if (validation.inputAmount(nextLine.length, 2)){
                            if (validation.number(nextLine[i + 1], 1)) {
                                db.deleteSupplier(Integer.parseInt(nextLine[i + 1]));
                                db.findSuppliers().forEach(Supplier::print);
                            }
                        }
                        break;
                    case "deletePackage":
                        if (validation.inputAmount(nextLine.length, 2)){
                            if (validation.number(nextLine[i + 1], 1)) {
                                db.deletePackage(Integer.parseInt(nextLine[i + 1]));
                                db.findPackages().forEach(Package::print);
                            }
                        }
                        break;
                    case "exit":
                        exit = true;
                        System.out.println("Exiting program...");
                        break;
                }
            }
        } while (!exit);
    }
}