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
public class Lamination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int laminationId;

    @Column(length = 20)
    private String laminationType;

    @Column(precision = 6, scale = 2)
    private BigDecimal rate;

    public Lamination(String laminationType, BigDecimal rate) {
        this.laminationType = laminationType;
        this.rate = rate;
    }
}


