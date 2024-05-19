package com.example.ERP_V2.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class PlateDetailData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int plateDetailDataId;

    private String screenType;

    private String plateDamage;

    private String plateRemake;

    @OneToMany(mappedBy = "plateDetailData", cascade = CascadeType.ALL)
    private List<PlateData> plateData = new ArrayList<>();


    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

}
