package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperData2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int paperData2_id;

    private String type;
    private String cutSheetSize;
    private String wastage;
    private String totalCutSheet;

    @ManyToOne
    @JoinColumn(name = "paperData_id")
    private PaperData paperData;
}
