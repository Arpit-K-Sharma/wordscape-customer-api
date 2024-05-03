package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.AuthRequest;
import com.example.ERP_V2.DTO.LoginResponse;
import com.example.ERP_V2.DTO.UserDTO;
import com.example.ERP_V2.enums.RoleEnum;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthenticationService {
    LoginResponse login(AuthRequest authRequest);


    UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String username, RoleEnum role);
}
