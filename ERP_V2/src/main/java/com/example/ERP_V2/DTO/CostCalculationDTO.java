package com.example.ERP_V2.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CostCalculationDTO {

    private BigDecimal plates;
    private BigDecimal printing;
    private BigDecimal paper;
    private BigDecimal coverPaper;
    private BigDecimal innerPaper;
    private BigDecimal otherPaper;
    private BigDecimal lamination;
    private BigDecimal binding;
    private BigDecimal finishing;
    private BigDecimal extraCharges;
    private BigDecimal subTotal;
    private BigDecimal vat;
    private BigDecimal grandTotal;

    private String preparedBy;

    private String approvedBy;
}
