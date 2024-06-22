package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ink")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ink {
    @Id
    private String inkId;

    private String inkType;

    public Ink(String inkType) {
        this.inkType = inkType;
    }
}