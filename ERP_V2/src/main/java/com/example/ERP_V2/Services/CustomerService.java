package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.CustomerDTO;
import com.example.ERP_V2.DTO.PaginatedResponse;
import com.example.ERP_V2.Model.CoverTreatment;
import com.example.ERP_V2.Model.Customer;

import java.util.List;

public interface CustomerService {

    PaginatedResponse<CustomerDTO> getAllCustomers(Integer pageNumber, Integer pageSize, String sortField, String sortDirection);

    public void registerAsCustomer(CustomerDTO customerDTO);

    void updateCustomer(int id, Customer updatedCustomer);

    public CustomerDTO getCustomer(int id);

    void deactivateCustomer(int id);

    void reactivateCustomer(int id);
}
