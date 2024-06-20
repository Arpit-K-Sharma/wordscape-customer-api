package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.*;
import com.example.ERP_V2.Model.*;
import com.example.ERP_V2.Repository.AdminRepo;
import com.example.ERP_V2.Repository.CustomerRepo;
import com.example.ERP_V2.Repository.OTPRepo;
import com.example.ERP_V2.Repository.UserRepo;
import com.example.ERP_V2.Services.AuthenticationService;
import com.example.ERP_V2.Services.EmailService;
import com.example.ERP_V2.Services.OTPService;
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
import java.util.*;
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
    private EmailService emailService;

    @Autowired
    private OTPService otpService;

    @Autowired
    private OTPRepo otpRepo;

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

    private AdminDTO getAdminById(int id) {
        Admin admin = this.adminRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
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

//    private CustomerDTO getCustomerByEmailAuthentication(String email) {
//        Customer customer = this.customerRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
//        return new CustomerDTO(customer.getEmail(), customer.getPassword());
//    }

    private UserDTO getUserByIdAuthentication(int id) {
        User user = this.userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDTO(user.getEmail(), user.getPassword());
    }

    private CustomerDTO getCustomerByIdAuthentication(int id) {
        Customer customer = this.customerRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
        return new CustomerDTO(customer.getCustomerId(), customer.getPassword());
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
    public UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(int id, RoleEnum role) {
        if (role.equals(RoleEnum.ROLE_USER)) {
            UserDTO userDTO = this.getUserByIdAuthentication(id);
            List<SimpleGrantedAuthority> authorities = this.addAuthority(userDTO.getRole());
            return new UsernamePasswordAuthenticationToken(userDTO.getUserId(), userDTO.getPassword(),
                    authorities);
        } else if (role.equals(RoleEnum.ROLE_CUSTOMER)) {
            CustomerDTO customerDTO = this.getCustomerByIdAuthentication(id);
            List<SimpleGrantedAuthority> authorities = this.addAuthority(customerDTO.getRole());
            return new UsernamePasswordAuthenticationToken(customerDTO.getCustomerId(), customerDTO.getPassword(),
                    authorities);
        } else {
            AdminDTO adminDTO = this.getAdminById(id);
            List<SimpleGrantedAuthority> authorities = this.addAuthority(adminDTO.getRole());
            return new UsernamePasswordAuthenticationToken(adminDTO.getAdmin_id(), adminDTO.getPassword(),
                    authorities);
        }
    }

    @Override
    public void forgotPassword(LoginRequestDTO loginRequestDTO) {
        // Check if an OTP already exists for the given email
        Optional<OTP> existingOTP = otpRepo.findByEmail(loginRequestDTO.getEmail());

        OTP otp;
        otp = existingOTP.map(this::updateOTP).orElseGet(() -> this.generateOTP(loginRequestDTO));
        this.otpService.addOtp(otp);
        this.emailService.sendEmail(loginRequestDTO.getEmail(), otp.getOtp());
    }

    @Override
    public void verifyAndChangePassword(NewPasswordDTO newPasswordDTO) {
        OTP otp = this.otpRepo.findByEmail(newPasswordDTO.getEmail()).orElseThrow(() -> new RuntimeException("OTP for email not found"));
        if(otp.getOtp() == newPasswordDTO.getOtp()){
            if (newPasswordDTO.getRole().equals(RoleEnum.ROLE_CUSTOMER)){
                Customer customer = this.customerRepo.findByEmail(newPasswordDTO.getEmail()).orElseThrow(() -> new RuntimeException("Customer nor found"));
                customer.setPassword(passwordEncoder.encode(newPasswordDTO.getNewPassword()));
                this.customerRepo.save(customer);
            }
            else if(newPasswordDTO.getRole().equals(RoleEnum.ROLE_USER)){
                User user = this.userRepo.findByEmail(newPasswordDTO.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
                user.setPassword(passwordEncoder.encode(newPasswordDTO.getNewPassword()));
                this.userRepo.save(user);
            }
            else{
                Admin admin = this.adminRepo.findByEmail(newPasswordDTO.getEmail()).orElseThrow(() -> new RuntimeException("Admin not found"));
                admin.setPassword(passwordEncoder.encode(newPasswordDTO.getNewPassword()));
                this.adminRepo.save(admin);
            }

        }
        else{
            throw new RuntimeException("OTP does not match");
        }

    }

    private OTP generateOTP(LoginRequestDTO loginRequestDTO){
        OTP otp = new OTP();
        otp.setOtp((int) (Math.random() * 1000000));
        otp.setUpdated_at(new Date());
        otp.setEmail(loginRequestDTO.getEmail());

        return otp;
    }

    private OTP updateOTP(OTP otp){
        otp.setOtp((int) (Math.random() * 1000000));
        otp.setUpdated_at(new Date());
        return otp;
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


