package org.example.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String storeLocation;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("storeLocation", storeLocation);
        return map;
    }
    public void print(){
        System.out.println("Supplier\n" +
                "firstName: " + firstName +
                ", lastName: " + lastName +
                ", storeLocation: " + storeLocation );
    }
}
