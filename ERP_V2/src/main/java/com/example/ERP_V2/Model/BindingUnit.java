package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BindingUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int bindingUnitId;

    private String bindingType;

    private String filledInBy;

    private String approvedBy;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
