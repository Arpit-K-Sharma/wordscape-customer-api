package com.example.ERP_V2.DTO;

import com.example.ERP_V2.Model.SalesBook;
import com.example.ERP_V2.Model.SalesRecord;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// DTO for Customer entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

    private int customerId;

    private String fullName;

    private String address;

    private String email;

    private String phoneNumber;

    private String companyName;

    private boolean status;

    private List<OrderDTO> orderList;

    private List<SalesBook> salesBookList;

    private List<SalesRecord> salesRecordList;
}

