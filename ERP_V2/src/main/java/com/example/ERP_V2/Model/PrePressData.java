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
public class PrePressData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int prePressDataId;

    private String paymentMethod;

    private String materialReceived;

    private String flapSize;

    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

}
