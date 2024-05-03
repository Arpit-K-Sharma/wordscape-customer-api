package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.CustomerDTO;
import com.example.ERP_V2.Model.CoverTreatment;
import com.example.ERP_V2.Model.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    public void registerAsCustomer(CustomerDTO customerDTO);

    void updateCustomer(int id, Customer updatedCustomer);
}
