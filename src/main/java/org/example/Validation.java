package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class Validation {

    public boolean number(String userInput, int inputPosition) {
        try {
            // Attempt to parse the string as an integer
            int number = Integer.parseInt(userInput);

            // If successful, print the result
            System.out.println("Parsed Integer: " + number);
            return true;
        } catch (NumberFormatException e) {
            // If parsing fails, catch the exception
            System.out.println("Invalid input. Please enter a valid integer in position "+inputPosition+"." );
            return false;
        }
    }
    public boolean existID(MongoCollection collection, int targetId){
        if (collection.find(Filters.eq("id", targetId)).first()!=null){
            System.out.println("Document with ID " + targetId + " exists.");
            return true;
        }
        System.out.println("Document with ID " + targetId + " does not exist.");
        return false;
    }
    public boolean inputAmount (int inputAmount, int inputAmountExpected){
        if (inputAmount==inputAmountExpected){
            System.out.println("Input amount is correct.");
            return true;
        }
        System.out.println("Input amount is wrong. Expected: "+inputAmountExpected);
        return false;
    }
}
