package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.*;
import com.example.ERP_V2.Model.OTP;
import com.example.ERP_V2.Model.User;
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

import java.util.*;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

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
        User user = this.userRepo.findByEmail(loginRequestDTO.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
        if (user.getRole().equals(RoleEnum.ROLE_ADMIN)) {
            AdminDTO adminDTO = this.modelMapper.map(user, AdminDTO.class);
            loginResponseDTO = this.authenticate(adminDTO, loginRequestDTO.getPassword(), adminDTO.getRole(), adminDTO.getUserId());
        } else if (user.getRole().equals(RoleEnum.ROLE_USER)) {
            UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
            loginResponseDTO = this.authenticate(userDTO,  loginRequestDTO.getPassword(), userDTO.getRole(), userDTO.getUserId());
        } else {
            CustomerDTO customerDTO = this.modelMapper.map(user, CustomerDTO.class);
            loginResponseDTO = this.authenticate(customerDTO, loginRequestDTO.getPassword(), customerDTO.getRole(), customerDTO.getUserId());
        }
        return loginResponseDTO;
    }

    private AdminDTO getAdminById(String id) {
        User admin = this.userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
        return modelMapper.map(admin, AdminDTO.class);
    }

//    private CustomerDTO getCustomerByEmailAuthentication(String email) {
//        Customer customer = this.customerRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
//        return new CustomerDTO(customer.getEmail(), customer.getPassword());
//    }

    private UserDTO getUserByIdAuthentication(String id) {
        User user = this.userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserDTO.builder().userId(user.getUserId()).password(user.getPassword()).build();
    }

    private CustomerDTO getCustomerByIdAuthentication(String id) {
        User customer = this.userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
        return new CustomerDTO(customer.getUserId(), customer.getPassword());
    }

    private LoginResponseDTO authenticate(PersonDTO personDTO, String rawPassword, RoleEnum role, String id) throws UsernameNotFoundException {
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
    public UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String id, RoleEnum role) {
        if (role.equals(RoleEnum.ROLE_USER)) {
            UserDTO userDTO = this.getUserByIdAuthentication(id);
            List<SimpleGrantedAuthority> authorities = this.addAuthority(userDTO.getRole());
            return new UsernamePasswordAuthenticationToken(userDTO.getUserId(), userDTO.getPassword(),
                    authorities);
        } else if (role.equals(RoleEnum.ROLE_CUSTOMER)) {
            CustomerDTO customerDTO = this.getCustomerByIdAuthentication(id);
            List<SimpleGrantedAuthority> authorities = this.addAuthority(customerDTO.getRole());
            return new UsernamePasswordAuthenticationToken(customerDTO.getUserId(), customerDTO.getPassword(),
                    authorities);
        } else {
            AdminDTO adminDTO = this.getAdminById(id);
            List<SimpleGrantedAuthority> authorities = this.addAuthority(adminDTO.getRole());
            return new UsernamePasswordAuthenticationToken(adminDTO.getUserId(), adminDTO.getPassword(),
                    authorities);
        }
    }

    @Override
    public void forgotPassword(LoginRequestDTO loginRequestDTO) {

        User user = this.userRepo.findByEmail(loginRequestDTO.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

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
            User user = this.userRepo.findByEmail(newPasswordDTO.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
            user.setPassword(passwordEncoder.encode(newPasswordDTO.getNewPassword()));
            this.userRepo.save(user);
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



    private CustomerDTO convertToCustomerDTO(User customer){
        if (customer == null) {
            return null;
        }

        // Manually mapping fields from Customer to CustomerDTO
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setUserId(customer.getUserId());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setCompanyName(customer.getCompanyName());
        customerDTO.setStatus(customer.isStatus());


        return customerDTO;
    }


}


