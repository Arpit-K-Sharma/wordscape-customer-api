package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.LoginRequestDTO;
import com.example.ERP_V2.DTO.LoginResponseDTO;
import com.example.ERP_V2.DTO.NewPasswordDTO;
import com.example.ERP_V2.Model.OTP;
import com.example.ERP_V2.enums.RoleEnum;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthenticationService {
    LoginResponseDTO login(LoginRequestDTO authRequest);


    UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String id, RoleEnum role);

    void forgotPassword(LoginRequestDTO loginRequestDTO);

    void verifyAndChangePassword(NewPasswordDTO newPasswordDTO);

    OTP generateOTP(String email);

    OTP updateOTP(OTP otp);
}
