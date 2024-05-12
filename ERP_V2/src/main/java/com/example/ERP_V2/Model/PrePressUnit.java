package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrePressUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int prePressUnitId;

    private String materialReceived;

    private String imposition;

    private int flapSize;

    private String paperSize;

    private String gutterSize;

    private String gripperSize;

    private String coverPaperSize;

    private String innerPaperSize;

    private String folderName;

    private String plateProcessBy;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
