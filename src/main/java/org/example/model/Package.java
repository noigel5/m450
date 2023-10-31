package org.example.model;

import lombok.Data;

@Data
public class Package {
    private String content;
    private int weight;
    private int length;
    private int depth;
    private int height;
}
