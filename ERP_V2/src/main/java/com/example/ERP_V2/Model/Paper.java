package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data // Lombok for getters, setters, etc.
@AllArgsConstructor
@NoArgsConstructor
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int paperId;

    @Column(length = 30)
    private String paperType;

    @Column(precision = 6, scale = 2)
    private BigDecimal rate;

    public Paper(String paperType, BigDecimal rate) {
        this.paperType = paperType;
        this.rate = rate;
    }
}