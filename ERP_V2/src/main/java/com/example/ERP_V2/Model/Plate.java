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
public class Plate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int plateId;

    @Column(length = 20)
    private String plateSize;

    @Column(precision = 6, scale = 2)
    private BigDecimal plateRate;

    @Column(precision = 6, scale = 2)
    private BigDecimal inkRate;

    public Plate(String plateSize, BigDecimal plateRate, BigDecimal inkRate) {
        this.plateSize = plateSize;
        this.plateRate = plateRate;
        this.inkRate = inkRate;
    }
}