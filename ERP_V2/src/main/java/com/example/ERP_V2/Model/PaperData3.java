package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperData3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int paperData3_id;

    private String Type;
    private String paperType;
    private String gsm;
    private String printColor;
    private String lamination;

    @ManyToOne
    @JoinColumn(name = "paperData_id")
    private PaperData paperData;
}
