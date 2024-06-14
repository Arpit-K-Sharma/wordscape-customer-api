package com.example.ERP_V2.Controller;

import com.example.ERP_V2.DTO.UserDTO;
import com.example.ERP_V2.Model.User;
import com.example.ERP_V2.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok("New User Added !!!");
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        List<UserDTO> users = userService.getAllUsers(pageNumber, pageSize);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        userService.updateUser(id, updatedUser);
        return ResponseEntity.ok("User updated !!!");
    }

    @PutMapping("deactivate/{id}")
    public ResponseEntity<String> deactivateCustomer(@PathVariable int id){
        userService.deactivateUser(id);
        return ResponseEntity.ok("User Deactivated !!!");
    }

    @PutMapping("reactivate/{id}")
    public ResponseEntity<String> reactivateCustomer(@PathVariable int id){
        userService.reactivateUser(id);
        return ResponseEntity.ok("User Reactivated !!!");
    }
}

