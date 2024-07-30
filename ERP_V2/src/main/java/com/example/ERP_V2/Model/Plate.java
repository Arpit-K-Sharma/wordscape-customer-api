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

    private Integer plateLength;

    private Integer plateBreadth;

    private BigDecimal plateRate;

    private BigDecimal reprint;

    private BigDecimal inkRate;

    public Plate(String plateSize, Integer plateLength, Integer plateBreadth, BigDecimal plateRate, BigDecimal reprint, BigDecimal inkRate) {
        this.plateSize = plateSize;
        this.plateLength = plateLength;
        this.plateBreadth = plateBreadth;
        this.plateRate = plateRate;
        this.reprint = reprint;
        this.inkRate = inkRate;
    }
}