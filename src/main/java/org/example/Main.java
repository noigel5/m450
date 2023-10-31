package org.example;

import org.example.server.MongoInterface;
import org.example.server.MongoServer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MongoInterface db = new MongoServer();

        System.out.println("getRecipient -> gibt alle Recipients aus\ngetSupplier -> gibt alle Supplier aus\ngetPackage -> gibt alle Packages aus");

        //TODO: Datenbankeinträge ausgeben
        switch (scanner.nextLine()) {
            case "getRecipient":
                System.out.println(db.findPackageDocuments("Recipient"));
            case "getSupplier":
                System.out.println(db.findPackageDocuments("Supplier"));
            case "getPackage":
                System.out.println(db.findPackageDocuments("Package"));
        }
    }
}