package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlateUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int plateUnitId;

    private String screenType;

    private String plateSize;

    private String color;

    private int total;

    private String plateDamage;

    private int noOfPlateRemake;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
