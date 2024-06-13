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
public class CoverTreatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int coverTreatmentId;

    @Column(length = 30)
    private String coverTreatmentType;

    @Column(precision = 6, scale = 2)
    private BigDecimal rate;

    public CoverTreatment(String coverTreatmentType, BigDecimal rate) {
        this.coverTreatmentType = coverTreatmentType;
        this.rate = rate;
    }
}