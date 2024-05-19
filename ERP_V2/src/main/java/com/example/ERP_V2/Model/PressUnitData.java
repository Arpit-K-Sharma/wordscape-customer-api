package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PressUnitData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int pressUnitDataId;

    private String totalSet;
    private String forma;
    private String workAndTurn;

    @OneToMany(mappedBy = "pressUnitData", cascade = CascadeType.ALL)
    private List<PressData> pressData = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    
}
