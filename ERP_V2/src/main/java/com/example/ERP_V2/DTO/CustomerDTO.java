package com.example.ERP_V2.DTO;

import com.example.ERP_V2.Model.SalesBook;
import com.example.ERP_V2.Model.SalesRecord;
import com.example.ERP_V2.enums.RoleEnum;
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
public class CustomerDTO extends PersonDTO {

    private String userId;

    private String address;

    private String phoneNumber;

    private String companyName;

    private boolean status = true;

    private List<OrderDTO> orderList;

    private List<SalesBook> salesBookList;

    private List<SalesRecord> salesRecordList;

    private final RoleEnum role = RoleEnum.ROLE_CUSTOMER;


    public CustomerDTO(String id, String password) {
        this.userId = id;
        this.password = password;
    }
}

