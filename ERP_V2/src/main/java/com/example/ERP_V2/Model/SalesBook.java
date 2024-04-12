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
public class SalesBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceNumber;

    private Date date;

    private Date deliveryDate;

    private int quantity;

    @Column(precision = 6, scale = 2)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}

