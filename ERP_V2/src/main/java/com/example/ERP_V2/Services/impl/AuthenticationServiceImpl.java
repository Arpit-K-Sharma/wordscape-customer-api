package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.*;
import com.example.ERP_V2.Model.Admin;
import com.example.ERP_V2.Model.Customer;
import com.example.ERP_V2.Model.Order;
import com.example.ERP_V2.Model.User;
import com.example.ERP_V2.Repository.AdminRepo;
import com.example.ERP_V2.Repository.CustomerRepo;
import com.example.ERP_V2.Repository.UserRepo;
import com.example.ERP_V2.Services.AuthenticationService;
import com.example.ERP_V2.enums.RoleEnum;
import com.example.ERP_V2.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO;
        if (loginRequestDTO.getRole().equals(RoleEnum.ROLE_ADMIN)) {
            loginResponseDTO = this.loginAsAdmin(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        } else if (loginRequestDTO.getRole().equals(RoleEnum.ROLE_USER)) {
            loginResponseDTO = this.loginAsUser(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        } else {
            loginResponseDTO = this.loginAsCustomer(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        }
        return loginResponseDTO;
    }

    private LoginResponseDTO loginAsAdmin(String email, String password) {
        AdminDTO adminDTO = this.getAdminByEmail(email);
        return this.authenticate(adminDTO, password, adminDTO.getRole(), adminDTO.getAdmin_id());
    }

    private LoginResponseDTO loginAsCustomer(String email, String password) {
        CustomerDTO customerDTO = this.getCustomerByEmail(email);
        return this.authenticate(customerDTO, password, customerDTO.getRole(), customerDTO.getCustomerId());
    }

    private LoginResponseDTO loginAsUser(String email, String password) {
        UserDTO userDTO = this.getUserByEmail(email);
        return this.authenticate(userDTO, password, userDTO.getRole(), userDTO.getUserId());
    }

    private AdminDTO getAdminByEmail(String email) {
        Admin admin = this.adminRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
        return modelMapper.map(admin, AdminDTO.class);
    }

    private CustomerDTO getCustomerByEmail(String email) {
        Customer customer = this.customerRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
        if (!customer.isStatus()){
            throw new RuntimeException("Customer is deactivated");
        }
//        return modelMapper.map(customer, CustomerDTO.class);
        return convertToCustomerDTO(customer);
    }

    private UserDTO getUserByEmail(String email) {
        User user = this.userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!user.isStatus()){
            throw new RuntimeException("User is Deactivated");
        }
        return modelMapper.map(user, UserDTO.class);
    }

    private UserDTO getUserByEmailAuthentication(String email) {
        User user = this.userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDTO(user.getEmail(), user.getPassword());
    }

    private CustomerDTO getCustomerByEmailAuthentication(String email) {
        Customer customer = this.customerRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
        return new CustomerDTO(customer.getEmail(), customer.getPassword());
    }

    private LoginResponseDTO authenticate(PersonDTO personDTO, String rawPassword, RoleEnum role, int id) throws UsernameNotFoundException {
        this.checkPassword(rawPassword, personDTO.getPassword());
        String accessToken = this.jwtUtil.generateToken(
                personDTO.getEmail(),
                new ArrayList<>(List.of(role.toString())),
                id
        );
        return new LoginResponseDTO(accessToken);
    }

    private void checkPassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword))
            throw new BadCredentialsException("Password Incorrect");
    }

    private List<SimpleGrantedAuthority> addAuthority(RoleEnum roleEnum) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (Objects.nonNull(roleEnum)) {
            authorities.add(new SimpleGrantedAuthority(roleEnum.toString()));
        }
        return authorities;
    }


    @Override
    public UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String email, RoleEnum role) {
        if (role.equals(RoleEnum.ROLE_USER)) {
            UserDTO userDTO = this.getUserByEmailAuthentication(email);
            List<SimpleGrantedAuthority> authorities = this.addAuthority(userDTO.getRole());
            return new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword(),
                    authorities);
        } else if (role.equals(RoleEnum.ROLE_CUSTOMER)) {
            CustomerDTO customerDTO = this.getCustomerByEmailAuthentication(email);
            List<SimpleGrantedAuthority> authorities = this.addAuthority(customerDTO.getRole());
            return new UsernamePasswordAuthenticationToken(customerDTO.getEmail(), customerDTO.getPassword(),
                    authorities);
        } else {
            AdminDTO adminDTO = this.getAdminByEmail(email);
            List<SimpleGrantedAuthority> authorities = this.addAuthority(adminDTO.getRole());
            return new UsernamePasswordAuthenticationToken(adminDTO.getEmail(), adminDTO.getPassword(),
                    authorities);
        }
    }

    private CustomerDTO convertToCustomerDTO(Customer customer){
        if (customer == null) {
            return null;
        }

        // Manually mapping fields from Customer to CustomerDTO
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setCompanyName(customer.getCompanyName());
        customerDTO.setStatus(customer.isStatus());


        return customerDTO;
    }






}


