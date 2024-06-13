package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inkId;

    @Column(length = 30)
    private String inkType;

    public Ink(String inkType) {
        this.inkType = inkType;
    }
}