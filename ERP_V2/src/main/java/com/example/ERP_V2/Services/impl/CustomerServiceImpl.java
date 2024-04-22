package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.CustomerDTO;
import com.example.ERP_V2.Model.Customer;
import com.example.ERP_V2.Repository.CustomerRepo;
import com.example.ERP_V2.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;


    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer: customerList){
            customerDTOList.add(this.convertToDTO(customer));
        }
        return customerDTOList;

//        return customerRepo.findAll()
//                .stream()  // Convert the list to a stream
//                .map(this::convertToDTO)  // Convert each Customer to CustomerDTO
//                .collect(Collectors.toList());  // Collect the results into a list
    }

    @Override
    public void updateCustomer(int id, Customer updatedCustomer) {
        Customer existingCustomer = customerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + id + " not found"));

        // Update fields if they are not null in the updatedCustomer object
        if (updatedCustomer.getFullName() != null) {
            existingCustomer.setFullName(updatedCustomer.getFullName());
        }
        if (updatedCustomer.getAddress() != null) {
            existingCustomer.setAddress(updatedCustomer.getAddress());
        }
        if (updatedCustomer.getEmail() != null) {
            existingCustomer.setEmail(updatedCustomer.getEmail());
        }
        if (updatedCustomer.getPassword() != null) {
            existingCustomer.setPassword(updatedCustomer.getPassword());
        }
        if (updatedCustomer.getPhoneNumber() != null) {
            existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        }
        // Update status if it's not null in the updatedCustomer object
        Boolean updatedStatus = updatedCustomer.isStatus();
        if (updatedStatus != null) {
            existingCustomer.setStatus(updatedStatus);
        }

        // Save the updated customer
        customerRepo.save(existingCustomer);
    }

    private CustomerDTO convertToDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setFullName(customer.getFullName());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setCompanyName(customer.getCompanyName());
        customerDTO.setStatus(customer.isStatus());
        return customerDTO;
    }

}
