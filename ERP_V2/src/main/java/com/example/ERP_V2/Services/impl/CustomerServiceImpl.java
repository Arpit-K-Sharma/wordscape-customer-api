package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.CustomerDTO;
import com.example.ERP_V2.DTO.UserDTO;
import com.example.ERP_V2.Model.Admin;
import com.example.ERP_V2.Model.Customer;
import com.example.ERP_V2.Model.Order;
import com.example.ERP_V2.Model.User;
import com.example.ERP_V2.Repository.AdminRepo;
import com.example.ERP_V2.Repository.CustomerRepo;
import com.example.ERP_V2.Repository.UserRepo;
import com.example.ERP_V2.Services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public void registerAsCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToCustomer(customerDTO);
        if (this.emailExists(customer.getEmail())) {
            throw new RuntimeException("Email already taken");
        }
        if(this.phoneExists(customer.getPhoneNumber())){
            throw new RuntimeException("Phone number already taken");
        }
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        this.customerRepo.save(customer);
    }

    private Boolean emailExists(String email) {
        Optional<Admin> savedAdmin = this.adminRepo.findByEmail(email);
        Optional<User> savedUser = this.userRepo.findByEmail(email);
        Optional<Customer> savedCustomer = this.customerRepo.findByEmail(email);
        return savedAdmin.isPresent() || savedUser.isPresent() || savedCustomer.isPresent();
    }

    private Boolean phoneExists(String phone){
        Optional<Customer> savedUser = this.customerRepo.findByPhoneNumber(phone);
        return savedUser.isPresent();
    }


    @Override
    public List<CustomerDTO> getAllCustomers(Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Customer> pagedResult = this.customerRepo.findAll(pageable);

        List<Customer> allCustomer = pagedResult.getContent();

        return allCustomer.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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
        if (updatedCustomer.getCompanyName() != null){
            existingCustomer.setCompanyName(updatedCustomer.getCompanyName());
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

    @Override
    public CustomerDTO getCustomer(int id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found !!!"));
        return convertToDTO(customer);
    }

    @Override
    public void deactivateCustomer(int id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found !!!"));
        customer.setStatus(false);
        this.customerRepo.save(customer);
    }

    @Override
    public void reactivateCustomer(int id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found !!!"));
        customer.setStatus(true);
        this.customerRepo.save(customer);
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

    private Customer convertToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setFullName(customerDTO.getFullName());
        customer.setAddress(customerDTO.getAddress());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setCompanyName(customerDTO.getCompanyName());
        customer.setStatus(customerDTO.isStatus());
        return customer;
    }

}
