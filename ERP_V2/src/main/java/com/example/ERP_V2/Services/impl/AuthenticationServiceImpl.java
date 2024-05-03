package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.AuthRequest;
import com.example.ERP_V2.DTO.LoginResponse;
import com.example.ERP_V2.Services.AuthenticationService;
import com.example.ERP_V2.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public LoginResponse login(AuthRequest authRequest) {
        return null;
    }

    @Override
    public void createAdmin() {

    }

    @Override
    public UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String username, RoleEnum role) {
        return null;
    }
}
