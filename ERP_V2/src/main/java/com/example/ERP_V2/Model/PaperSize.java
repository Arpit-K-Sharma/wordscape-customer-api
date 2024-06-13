package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paperSizeId;

    @Column(length = 20)
    private String paperSize;

    @Column(length = 20)
    private String dimensions;

    public PaperSize(String paperSize, String dimensions) {
        this.paperSize = paperSize;
        this.dimensions = dimensions;
    }
}