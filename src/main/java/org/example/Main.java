package org.example;

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

        String[] nextLine = scanner.nextLine().split(" ");

        //TODO: create und edit einfügen
        switch (nextLine[0]) {
            case "getRecipients":
                System.out.println();
            case "getSuppliers":
                System.out.println();
            case "getPackages":
                System.out.println(db.findPackageDocuments("Package").toString());

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