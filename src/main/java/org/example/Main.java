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
                getRecipients -> gibt alle Recipients aus
                getSuppliers -> gibt alle Supplier aus
                getPackages -> gibt alle Packages aus
                                
                createRecipient first_name last_name address phone_number email-> erstellt einen neuen Recipient
                createSupplier first_name last_name store_location-> erstellt einen neuen Supplier
                createPackage content weight length depth height recipient_id -> erstellt ein neues Paket
                                
                editRecipient recipient_id spaltenname neuer_Wet -> bearbeitet einen Wert von Recipient
                editSupplier supplier_id spaltenname neuer_Wet -> bearbeitet einen Wert von Supplier
                editPackage package_id spaltenname neuer_Wet -> bearbeitet einen Wert von Package
                """);
//        System.out.println(db.findRecipientDocuments().stream().map(recipient -> recipient.getFirstName()).collect(Collectors.joining(", ")));
        boolean exit = false;

        do {

            String[] nextLine = scanner.nextLine().split(" ");

            for (int i = 0; i < nextLine.length; i++) {
                switch (nextLine[i]) {
                    case "getRecipients":
                        db.findRecipients().forEach(Recipient::print);
                        break;
                    case "getSuppliers":
                        db.findSuppliers().forEach(Supplier::print);
                        break;
                    case "getPackages":
                        db.findPackages().forEach(Package::print);
                        break;
                    case "createRecipient":
                        Recipient recipient = new Recipient(db.findRecipients().size()+1, nextLine[i + 1], nextLine[i + 2], nextLine[i + 3], nextLine[i + 4], nextLine[i + 5]);
                        db.addRecipient(recipient);
                        recipient.print();
                        break;
                    case "createSupplier":
                        Supplier supplier = new Supplier( db.findSuppliers().size()+1,nextLine[i + 1], nextLine[i + 2], nextLine[i + 3]);
                        db.addSupplier(supplier);
                        supplier.print();
                        break;
                    case "createPackage":
                        Package aPackage = new Package(db.findPackages().size()+1,nextLine[i + 1],  Integer.valueOf(nextLine[i + 2]),  Integer.valueOf(nextLine[i + 3]),  Integer.valueOf(nextLine[i + 4]),  Integer.valueOf(nextLine[i + 5]),  Integer.valueOf(nextLine[i+6]));
                        db.addPackage(aPackage);
                        aPackage.print();
                        break;
                    case "editRecipient":
                        Recipient recipientEdit = new Recipient(Integer.valueOf(nextLine[i + 1]),  nextLine[i + 2],  nextLine[i + 3],  nextLine[i + 4],  nextLine[i + 5],  nextLine[i+6]);
                        db.editRecipient(recipientEdit);
                        recipientEdit.print();
                        break;
                    case "editSupplier":
                        Supplier supplierEdit = new Supplier(Integer.valueOf(nextLine[i + 1]), nextLine[i + 2],  nextLine[i + 3],  nextLine[i + 4]);
                        db.editSupplier(supplierEdit);
                        supplierEdit.print();
                        break;
                    case "editPackage":
                        Package aPackageEdit = new Package(Integer.valueOf(nextLine[i + 1]), nextLine[i + 2],  Integer.valueOf(nextLine[i + 3]),  Integer.valueOf(nextLine[i + 4]),  Integer.valueOf(nextLine[i + 5]),  Integer.valueOf(nextLine[i+6]),Integer.valueOf(nextLine[i + 6]));
                        db.editPackage(aPackageEdit);
                        aPackageEdit.print();
                        break;

                    case "exit":
                        exit = true;
                        System.out.println("Exit");
                        break;
                }
            }
        } while (!exit);
    }
}