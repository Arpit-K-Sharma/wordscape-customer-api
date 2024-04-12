package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceNumber;

    @Column(length = 70)
    private String productName;

    private int quantity;

    @Column(precision = 6, scale = 2)
    private BigDecimal Rate;

    private Date date;

    private Date deliveryDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
