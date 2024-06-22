package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.CustomerDTO;
import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.DTO.PaginatedResponse;
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
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
    public PaginatedResponse<CustomerDTO> getAllCustomers(Integer pageNumber, Integer pageSize, String sortField, String sortDirection) {

        checkValidSortFields(sortField);

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Customer> pagedResult = this.customerRepo.findAll(pageable);

        List<Customer> allCustomer = pagedResult.getContent();

        List<CustomerDTO> customers = pagedResult.hasContent() ?
                pagedResult.getContent().stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList()) : new ArrayList<>();

        long totalElements = pagedResult.getTotalElements();

        return new PaginatedResponse<>(customers, totalElements);
    }

    public void checkValidSortFields(String sortField){

        // List of valid sort fields
        List<String> validSortFields = Arrays.asList("created_at", "customerId");
        if (!validSortFields.contains(sortField)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortField);
        }
    }


    @Override
    public void updateCustomer(String id, Customer updatedCustomer) {
        Customer existingCustomer = customerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

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
    public CustomerDTO getCustomer(String id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found !!!"));
        return convertToDTO(customer);
    }

    @Override
    public void deactivateCustomer(String id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found !!!"));
        customer.setStatus(false);
        this.customerRepo.save(customer);
    }

    @Override
    public void reactivateCustomer(String id) {
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
