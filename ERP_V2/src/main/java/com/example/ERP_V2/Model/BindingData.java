package com.example.ERP_V2.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BindingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int bindingUnitId;

    private String bindingSelectedOption;

    private String filledInBy;

    private String approvedBy;

    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;
}
