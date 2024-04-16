package com.example.ERP_V2.Services;

import com.example.ERP_V2.Model.CoverTreatment;
import com.example.ERP_V2.Model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    void updateCustomer(int id, Customer updatedCustomer);
}
