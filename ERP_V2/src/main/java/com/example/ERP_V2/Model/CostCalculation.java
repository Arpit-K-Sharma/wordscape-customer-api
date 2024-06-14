package com.example.ERP_V2.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int costCalculationId;

    @Column(precision = 6, scale = 2)
    private BigDecimal plates;

    @Column(precision = 6, scale = 2)
    private BigDecimal printing;

    @Column(precision = 6, scale = 2)
    private BigDecimal paper;

    @Column(precision = 6, scale = 2)
    private BigDecimal coverPaper;

    @Column(precision = 6, scale = 2)
    private BigDecimal innerPaper;

    @Column(precision = 6, scale = 2)
    private BigDecimal otherPaper;

    @Column(precision = 6, scale = 2)
    private BigDecimal lamination;

    @Column(precision = 6, scale = 2)
    private BigDecimal binding;

    @Column(precision = 6, scale = 2)
    private BigDecimal finishing;

    @Column(precision = 6, scale = 2)
    private BigDecimal extraCharges;

    @Column(precision = 10, scale = 2)
    private BigDecimal subTotal;

    @Column(precision = 6, scale = 2)
    private BigDecimal vat;

    @Column(precision = 10, scale = 2)
    private BigDecimal grandTotal;

    private String preparedBy;

    private String approvedBy;

    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

}
