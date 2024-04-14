package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.Customer;
import com.example.ERP_V2.Repository.CustomerRepo;
import com.example.ERP_V2.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;


    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }
}
