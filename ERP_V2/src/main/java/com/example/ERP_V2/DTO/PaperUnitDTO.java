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
public class PaperUnitDTO {

    private String paperCategory;

    private String fullSheetSize;

    private BigDecimal weight;

    private String paperType;

    private int totalSheets;

    private String cutSheetSize;

    private String wastage;

    private int totalCutSheets;

    private int gsm;

    private String printColor;

    private String lamination;
}
