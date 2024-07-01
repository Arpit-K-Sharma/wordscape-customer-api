package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "paper")
@Data // Lombok for getters, setters, etc.
@AllArgsConstructor
@NoArgsConstructor
public class Paper {

    @Id
    private String paperId;

    private String paperType;

    private BigDecimal rate;

    private Integer minThickness;

    private Integer maxThickness;

    public Paper(String paperType, BigDecimal rate, int minThickness, int maxThickness) {
        this.paperType = paperType;
        this.rate = rate;
        this.minThickness = minThickness;
        this.maxThickness = maxThickness;
    }
}