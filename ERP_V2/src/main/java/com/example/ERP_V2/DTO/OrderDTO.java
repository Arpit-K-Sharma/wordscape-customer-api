package com.example.ERP_V2.DTO;


import com.example.ERP_V2.Model.Customer;
import com.example.ERP_V2.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private int orderId;
    private Date date;
    private Date deadline;
    private String paperSize;
    private int pages;
    private int quantity;
    private String bindingType;
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

    private BigDecimal laminationRate;

    private BigDecimal outerPaperRate;

    private BigDecimal plateRate;

    private int estimatedAmount;
}
