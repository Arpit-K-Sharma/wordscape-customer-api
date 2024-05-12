package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PressUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int pressUnitId;

    private String size;

    private String signature;

    private boolean ordered;

    private boolean produced;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
