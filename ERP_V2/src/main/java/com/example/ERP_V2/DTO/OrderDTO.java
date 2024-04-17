package com.example.ERP_V2.DTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private int orderId;
    private Date date;
    private String paperSize;
    private int pages;
    private int quantity;
    private String bindingType;
    private String coverTreatmentType;
    private String innerPaperType;
    private int innerPaperThickness;
    private String outerPaperType;
    private int outerPaperThickness;
    private String laminationType;
    private String plateSize;
    private String inkType;
    private String remarks;
    private int customerId;
}
