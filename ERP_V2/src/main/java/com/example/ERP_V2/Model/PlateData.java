package com.example.ERP_V2.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlateData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int plateDataId;

    private String size;
    private String colour1;
    private String colour2;
    private String colour3;
    private String colour4;
    private String special;
    private String total;

    @ManyToOne
    @JoinColumn(name = "plateDetailData_id")
    @JsonBackReference
    private PlateDetailData plateDetailData;
}
