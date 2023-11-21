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
                                
                editRecipient id neue_Werte -> bearbeitet einen Wert von Recipient
                editSupplier id neue_Werte -> bearbeitet einen Wert von Supplier
                editPackage id neue_Werte -> bearbeitet einen Wert von Package
                                
                editRecipient id neue_Werte -> bearbeitet einen Wert von Recipient
                editSupplier id neue_Werte -> bearbeitet einen Wert von Supplier
                editPackage id neue_Werte -> bearbeitet einen Wert von Package
                                
                """);
//        System.out.println(db.findRecipientDocuments().stream().map(recipient -> recipient.getFirstName()).collect(Collectors.joining(", ")));
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
                            if (validation.number(nextLine[i + 1], 1)&&validation.number(nextLine[i + 2], 2) && validation.number(nextLine[i + 3], 3) && validation.number(nextLine[i + 4], 4) && validation.number(nextLine[i + 5], 5) && validation.number(nextLine[i + 6], 6)) {
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


                    default:
                        exit = true;
                        System.out.println("Exit");
                        break;
                }
            }
        } while (!exit);
    }
}