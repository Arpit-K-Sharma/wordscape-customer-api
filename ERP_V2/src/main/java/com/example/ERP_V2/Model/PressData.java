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
public class PressData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int pressDataId;

    private String paperType;
    private String size;
    private String signature;
    private String ordered;
    private String produced;

    @ManyToOne
    @JoinColumn(name = "pressUnitData_id")
    @JsonBackReference
    private PressUnitData pressUnitData;


}
