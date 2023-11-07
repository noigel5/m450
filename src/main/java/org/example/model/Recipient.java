package org.example.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private String address;
    @NonNull private String phoneNumber;
    @NonNull private String email;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("address", address);
        map.put("phoneNumber", phoneNumber);
        map.put("email", email);
        return map;
    }
    public void print(){
        System.out.println("Recipient\n" +
                "id: " + id +
                ", firstName: " + firstName +
                ", lastName: " + lastName +
                ", address: " + address +
                ", phoneNumber: " + phoneNumber +
                ", email: " + email);
    }
}
