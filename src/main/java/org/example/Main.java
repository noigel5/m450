package org.example;

import org.example.server.MongoInterface;
import org.example.server.MongoInterfaceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MongoInterface db = new MongoInterfaceImpl();

        System.out.println("getRecipient -> gibt alle Recipients aus\ngetSupplier -> gibt alle Supplier aus\ngetPackage -> gibt alle Packages aus");

        //TODO: Datenbankeinträge ausgeben
        switch (scanner.nextLine()) {
            case "getRecipient":
                System.out.println();
            case "getSupplier":
                System.out.println();
            case "getPackage":
                System.out.println();
        }
    }
}