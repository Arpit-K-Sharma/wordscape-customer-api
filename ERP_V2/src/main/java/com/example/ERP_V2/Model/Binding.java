package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Binding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int bindingId;

    @Column(length = 20)
    private String bindingType;

    @Column(precision = 6, scale = 2)
    private BigDecimal rate; // Use BigDecimal for monetary values

    public Binding(String bindingType, BigDecimal rate) {
        this.bindingType = bindingType;
        this.rate = rate;
    }
}