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
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull private String content;
    @NonNull private int weight;
    @NonNull private int length;
    @NonNull private int depth;
    @NonNull private int height;
    @NonNull private int recipientId;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("content", content);
        map.put("weight", weight);
        map.put("length", length);
        map.put("depth", depth);
        map.put("height", height);
        map.put("recipientId", recipientId);
        return map;
    }

    public void print(){
        System.out.println("Package\n" +
                "id:"+id+
                ", content: " + content +
                ", weight: " + weight +
                ", length: " + length +
                ", depth: " + depth +
                ", height: " + height +
                ", recipientId: " + recipientId);
    }


}
