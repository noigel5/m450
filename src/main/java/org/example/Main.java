package org.example;

import org.example.server.MongoInterface;
import org.example.server.MongoServer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MongoInterface db = new MongoServer();

        System.out.println("""
                getRecipient -> gibt alle Recipients aus
                getSupplier -> gibt alle Supplier aus
                getPackage -> gibt alle Packages aus
                                
                createRecipient -> erstellt einen neuen Recipient
                createSupplier -> erstellt einen neuen Supplier
                createPackage -> erstellt ein neues Paket
                                
                editRecipient recipient_id spaltenname neuer_Wet -> bearbeitet einen Wert von Recipient
                editSupplier supplier_id spaltenname neuer_Wet -> bearbeitet einen Wert von Supplier
                editPackage package_id spaltenname neuer_Wet -> bearbeitet einen Wert von Package
                """);

        //TODO: create und edit einfügen
        switch (scanner.nextLine()) {
            case "getRecipient":
                System.out.println(db.findRecipientDocuments("Recipient"));
            case "getSupplier":
                System.out.println(db.findSupplierDocuments("Supplier"));
            case "getPackage":
                System.out.println(db.findPackageDocuments("Package"));

            case "createRecipient":
                System.out.println();
            case "createSupplier":
                System.out.println();
            case "createPackage":
                System.out.println();

            case "editRecipient":
                System.out.println();
            case "editSupplier":
                System.out.println();
            case "editPackage":
                System.out.println();
        }
    }
}