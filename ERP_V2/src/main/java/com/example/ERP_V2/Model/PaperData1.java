package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperData1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int paperData1_id;

    private String type;
    private String fullSheetSize;
    private String weight;
    private String paperType;
    private String totalSheets;

    @ManyToOne
    @JoinColumn(name = "paperData_id")
    private PaperData paperData;
}
