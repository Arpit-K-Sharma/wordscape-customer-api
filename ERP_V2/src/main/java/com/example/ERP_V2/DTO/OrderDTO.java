package com.example.ERP_V2.DTO;


import com.example.ERP_V2.Model.CostCalculation;
import com.example.ERP_V2.Model.Delivery;
import com.example.ERP_V2.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;
    private Date date;
    private Date deadline;
    private String paperSize;
    private int pages;
    private int quantity;
    private List<String> bindingType;
    private String coverTreatmentType;
    private String innerPaperType;
    private int innerPaperThickness;
    private String outerPaperType;
    private int outerPaperThickness;
    private String innerLamination;
    private String outerLamination;
    private String plateSize;
    private String inkType;
    private String deliveryOption;
    private String remarks;
    private String companyName;
    private String address;
    private OrderStatus status;
    private String customer;

    private String pdfFile;

    private BigDecimal bindingRate;

    private BigDecimal innerPaperRate;

    private BigDecimal outerPaperRate;

    private BigDecimal innerLaminationRate;

    private BigDecimal outerLaminationRate;

    private BigDecimal plateRate;

    private int estimatedAmount;

    private Delivery delivery;

    private CostCalculation costCalculation;
}
