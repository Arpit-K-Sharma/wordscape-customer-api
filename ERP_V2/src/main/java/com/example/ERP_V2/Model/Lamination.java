package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
@Document(collection = "lamination")
@Data // Lombok for getters, setters, etc.
@AllArgsConstructor
@NoArgsConstructor
public class Lamination {

    @Id
    private String laminationId;

    private String laminationType;

    private BigDecimal rate;

    public Lamination(String laminationType, BigDecimal rate) {
        this.laminationType = laminationType;
        this.rate = rate;
    }
}