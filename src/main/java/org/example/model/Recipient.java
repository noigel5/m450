package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Recipient {
    private String first_name;
    private String last_name;
    private String address;
    private String phone_number;
    private String email;
}
