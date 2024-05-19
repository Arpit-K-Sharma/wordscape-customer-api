package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperData0 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int paperData0_id;

    private String readyBy;
    private String date;
    private String time;
    private String type;
    private String size;
    private String numberOfPages;
    private String printrun;
    private String side;

    @OneToOne
    @JoinColumn(name = "paperData_id")
    private PaperData paperData;

}
