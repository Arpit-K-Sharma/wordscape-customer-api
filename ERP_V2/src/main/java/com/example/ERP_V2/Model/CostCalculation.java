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
public class CostCalculation {

    @Id
    private String costCalculationId;

    private BigDecimal plates;
    private BigDecimal printing;
    private BigDecimal paper;
    private BigDecimal coverPaper;
    private BigDecimal innerPaper;
    private BigDecimal otherPaper;
    private BigDecimal lamination;
    private BigDecimal binding;
    private BigDecimal finishing;
    private BigDecimal extraCharges;
    private BigDecimal subTotal;
    private BigDecimal vat;
    private BigDecimal grandTotal;

    private String preparedBy;

    private String approvedBy;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
