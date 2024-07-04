package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.CustomerDTO;
import com.example.ERP_V2.DTO.PaginatedResponse;
import com.example.ERP_V2.DTO.VerificationDTO;
import com.example.ERP_V2.Model.OTP;
import com.example.ERP_V2.Model.User;
import com.example.ERP_V2.Repository.OTPRepo;
import com.example.ERP_V2.Repository.UserRepo;
import com.example.ERP_V2.Services.AuthenticationService;
import com.example.ERP_V2.Services.CustomerService;
import com.example.ERP_V2.Services.EmailService;
import com.example.ERP_V2.enums.RoleEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OTPRepo otpRepo;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void registerAsCustomer(CustomerDTO customerDTO) {
        User customer = convertToCustomer(customerDTO);
        if (this.emailExists(customer.getEmail())) {
            throw new RuntimeException("Email already taken");
        }
        if(this.phoneExists(customer.getPhoneNumber())){
            throw new RuntimeException("Phone number already taken");
        }
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        this.userRepo.save(customer);

        OTP otp = generateOTP(customerDTO.getEmail());
        this.otpRepo.save(otp);
        this.sendEmail(customerDTO.getEmail(), otp.getOtp());
    }

    public OTP generateOTP(String email){
        OTP otp = new OTP();
        otp.setOtp((int) (Math.random() * 1000000));
        otp.setUpdated_at(new Date());
        otp.setEmail(email);

        return otp;
    }

    @Async("taskExecutor")
    public CompletableFuture<Void> sendEmail(String to, int otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("luciferdynamic598@gmail.com");
        message.setTo(to);
        message.setSubject("OTP");
        message.setText("Your OTP is: " + otp);
        emailSender.send(message);
        return CompletableFuture.completedFuture(null);
    }

    private Boolean emailExists(String email) {
        Optional<User> savedUser = this.userRepo.findByEmail(email);
        return savedUser.isPresent();
    }

    private Boolean phoneExists(String phone){
        Optional<User> savedUser = this.userRepo.findByPhoneNumber(phone);
        return savedUser.isPresent();
    }


    @Override
    public PaginatedResponse<CustomerDTO> getAllCustomers(Integer pageNumber, Integer pageSize, String sortField, String sortDirection) {

        checkValidSortFields(sortField);

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> pagedResult = this.userRepo.findAllByRole(RoleEnum.ROLE_CUSTOMER,pageable);

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
    public void updateCustomer(String id, User updatedCustomer) {
        User existingCustomer = userRepo.findById(id)
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
        userRepo.save(existingCustomer);
    }

    @Override
    public CustomerDTO getCustomer(String id) {
        User customer = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found !!!"));
        return convertToDTO(customer);
    }

    @Override
    public void deactivateCustomer(String id) {
        User customer = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found !!!"));
        customer.setStatus(false);
        this.userRepo.save(customer);
    }

    @Override
    public void reactivateCustomer(String id) {
        User customer = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found !!!"));
        customer.setStatus(true);
        this.userRepo.save(customer);
    }

    @Override
    public void verifyCustomer(VerificationDTO verificationDTO) {
        OTP otp = this.otpRepo.findByEmail(verificationDTO.getEmail()).orElseThrow(() -> new RuntimeException("OTP for email not found"));
        if(otp.getOtp() == verificationDTO.getOtp()){
            User user = this.userRepo.findByEmail(verificationDTO.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
            user.setStatus(true);
            this.userRepo.save(user);
        }
        else{
            throw new RuntimeException("OTP does not match");
        }

    }

    private CustomerDTO convertToDTO(User customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setUserId(customer.getUserId());
        customerDTO.setFullName(customer.getFullName());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setCompanyName(customer.getCompanyName());
        customerDTO.setStatus(customer.isStatus());
        return customerDTO;
    }

    private User convertToCustomer(CustomerDTO customerDTO){
        User user = new User();
        user.setUserId(customerDTO.getUserId());
        user.setFullName(customerDTO.getFullName());
        user.setAddress(customerDTO.getAddress());
        user.setEmail(customerDTO.getEmail());
        user.setPhoneNumber(customerDTO.getPhoneNumber());
        user.setCompanyName(customerDTO.getCompanyName());
        user.setStatus(customerDTO.isStatus());
        user.setRole(RoleEnum.ROLE_CUSTOMER);
        return user;
    }

}
