package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Supplier {
    private String first_name;
    private String last_name;
    private String store_location;
}
