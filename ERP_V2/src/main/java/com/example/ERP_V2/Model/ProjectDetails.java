package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int projectDetailId;

    private String plateSize;

    private String plateType;

    private int totalPlate;

    private String customerProvided;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
