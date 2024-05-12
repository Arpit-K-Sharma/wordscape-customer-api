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
public class PaperUsed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int paperUsedId;

    private String paperCategory;

    private String fullSheetSize;

    private BigDecimal weight;

    private String paperType;

    private int totalSheets;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
