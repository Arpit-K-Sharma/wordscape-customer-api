package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Document(collection = "plate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plate {

    @Id
    private String plateId;

    private String plateSize;

    private BigDecimal plateRate;

    private BigDecimal inkRate;

    public Plate(String plateSize, BigDecimal plateRate, BigDecimal inkRate) {
        this.plateSize = plateSize;
        this.plateRate = plateRate;
        this.inkRate = inkRate;
    }
}