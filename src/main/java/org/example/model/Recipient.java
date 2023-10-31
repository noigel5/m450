package org.example.model;

import lombok.Data;

@Data
public class Recipient {
    private String first_name;
    private String last_name;
    private String address;
    private int phone_number;
    private String email;
}
