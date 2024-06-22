package com.example.ERP_V2.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "binding")
@Data
@NoArgsConstructor
public class Binding {
    @Id
    String bindingId;

    private String bindingType;

    private BigDecimal rate; // Use BigDecimal for monetary values

    public Binding(String bindingType, BigDecimal rate) {
        this.bindingType = bindingType;
        this.rate = rate;
    }
}