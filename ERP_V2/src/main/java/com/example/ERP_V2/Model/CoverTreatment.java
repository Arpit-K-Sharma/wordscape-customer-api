package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "coverTreatment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoverTreatment {

    @Id
    private String coverTreatmentId;

    private String coverTreatmentType;

    private BigDecimal rate;

    public CoverTreatment(String coverTreatmentType, BigDecimal rate) {
        this.coverTreatmentType = coverTreatmentType;
        this.rate = rate;
    }
}