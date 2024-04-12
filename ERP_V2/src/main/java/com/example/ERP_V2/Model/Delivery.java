package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int deliveryId;

    @Column(length = 100, nullable = false)
    private String destination;

    private Date deliveryDate;

    private Time deliveryTime;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}

