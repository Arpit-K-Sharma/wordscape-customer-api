package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "paperSize")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperSize {
    @Id
    private String paperSizeId;

    private String paperSize;

    private String dimensions;

    private double paperLength;

    private double paperBreadth;

    public PaperSize(String paperSize, String dimensions, double paperLength, double paperBreadth) {
        this.paperSize = paperSize;
        this.dimensions = dimensions;
        this.paperLength = paperLength;
        this.paperBreadth = paperBreadth;
    }
}