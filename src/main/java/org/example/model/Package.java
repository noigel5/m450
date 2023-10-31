package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Package {
    private String content;
    private int weight;
    private int length;
    private int depth;
    private int height;
    private Recipient recipient;
}
