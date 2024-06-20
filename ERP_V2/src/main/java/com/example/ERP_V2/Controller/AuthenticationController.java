package com.example.ERP_V2.Controller;

import com.example.ERP_V2.DTO.LoginRequestDTO;
import com.example.ERP_V2.DTO.LoginResponseDTO;
import com.example.ERP_V2.DTO.NewPasswordDTO;
import com.example.ERP_V2.Services.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("forgot")
    public ResponseEntity<String> forgotPassword(@RequestBody LoginRequestDTO loginRequestDTO){
        this.authenticationService.forgotPassword(loginRequestDTO);
        return ResponseEntity.ok("OTP is being sent");
    }

    @PostMapping("/newPassword")
    public ResponseEntity<String> changePassword(@RequestBody NewPasswordDTO newPasswordDTO){
        this.authenticationService.verifyAndChangePassword(newPasswordDTO);
        return ResponseEntity.ok("Your password has been changed");
    }
}
