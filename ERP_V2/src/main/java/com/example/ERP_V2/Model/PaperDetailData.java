package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperDetailData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int paperDetailDataId;

    private String paperSize;
    private String gutterSize;
    private String gripperSize;
    private String coverPaperSize;
    private String innerPaperSize;
    private String folderName;
    private String plateProcessBy;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
