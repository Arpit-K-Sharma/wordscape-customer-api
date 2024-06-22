package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.CustomerDTO;
import com.example.ERP_V2.DTO.PaginatedResponse;
import com.example.ERP_V2.Model.CoverTreatment;
import com.example.ERP_V2.Model.Customer;

import java.util.List;

public interface CustomerService {

    PaginatedResponse<CustomerDTO> getAllCustomers(Integer pageNumber, Integer pageSize, String sortField, String sortDirection);

    public void registerAsCustomer(CustomerDTO customerDTO);

    void updateCustomer(String id, Customer updatedCustomer);

    public CustomerDTO getCustomer(String id);

    void deactivateCustomer(String id);

    void reactivateCustomer(String id);
}
