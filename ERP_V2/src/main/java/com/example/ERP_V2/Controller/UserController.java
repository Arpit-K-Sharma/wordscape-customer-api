package com.example.ERP_V2.Controller;

import com.example.ERP_V2.DTO.UserDTO;
import com.example.ERP_V2.Model.User;
import com.example.ERP_V2.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        log.info("ENDPOINT CALLED: /users (POST)");
        log.info("REQUEST BODY: {}", user);
        userService.createUser(user);
        log.info("New User created successfully");
        return ResponseEntity.ok("New User Added !!!");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        log.info("ENDPOINT CALLED: /users (GET)");
        log.info("pageNumber: {}, pageSize: {}", pageNumber, pageSize);
        List<UserDTO> users = userService.getAllUsers(pageNumber, pageSize);
        log.info("RESPONSE: {} users returned", users.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        log.info("ENDPOINT CALLED: /users/{} (PUT)", id);
        log.info("REQUEST BODY: {}", updatedUser);
        userService.updateUser(id, updatedUser);
        log.info("User with ID {} updated successfully", id);
        return ResponseEntity.ok("User updated !!!");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("deactivate/{id}")
    public ResponseEntity<String> deactivateCustomer(@PathVariable int id){
        log.info("ENDPOINT CALLED: /users/deactivate/{} (PUT)", id);
        userService.deactivateUser(id);
        log.info("User with ID {} deactivated successfully", id);
        return ResponseEntity.ok("User Deactivated !!!");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("reactivate/{id}")
    public ResponseEntity<String> reactivateCustomer(@PathVariable int id){
        log.info("ENDPOINT CALLED: /users/reactivate/{} (PUT)", id);
        userService.reactivateUser(id);
        log.info("User with ID {} reactivated successfully", id);
        return ResponseEntity.ok("User Reactivated !!!");
    }
}
