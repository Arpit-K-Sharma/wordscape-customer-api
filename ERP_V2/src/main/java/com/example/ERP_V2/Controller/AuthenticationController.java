package com.example.ERP_V2.Controller;

import com.example.ERP_V2.DTO.LoginRequestDTO;
import com.example.ERP_V2.DTO.LoginResponseDTO;
import com.example.ERP_V2.Services.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("home")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        log.info("\nENDPOINT CALLED: /home/login\n");
        LoginResponseDTO response = this.authenticationService.login(loginRequestDTO);
        log.info("RESPONSE: {}", response);
        return ResponseEntity.ok().body(response);
    }
}
