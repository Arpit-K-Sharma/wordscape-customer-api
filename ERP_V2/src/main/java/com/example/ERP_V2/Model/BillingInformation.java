package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingInformation {
    private Date invoiceIssueDate;
    private String invoiceNo;
    private String customerName;
    private String issuedBy;
    private String approvalStatus;
}
