package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.Customer;
import com.example.ERP_V2.Model.User;
import com.example.ERP_V2.Repository.UserRepo;
import com.example.ERP_V2.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository; // assuming UserRepository is a Spring Data JPA repository

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(int id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));

        // Update fields if they are not null in the updatedUser object
        if (updatedUser.getFullName() != null) {
            existingUser.setFullName(updatedUser.getFullName());
        }
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        // Update status only if it's not null in the updatedUser object
        Boolean updatedStatus = updatedUser.isStatus();
        if (updatedStatus != null) {
            existingUser.setStatus(updatedStatus);
        }

        // Save the updated user
        userRepository.save(existingUser);
    }

    @Override
    public void insertDummyData() {
        List<User> dummyUsers = Arrays.asList(
                new User("John Doe", "password123", "john.doe@example.com"),
                new User("Jane Smith", "password123", "jane.smith@example.com"),
                new User("Alice Johnson", "password123", "alice.johnson@example.com"),
                new User("Bob Brown", "password123", "bob.brown@example.com"),
                new User("Charlie Davis", "password123", "charlie.davis@example.com")
        );

        userRepository.saveAll(dummyUsers);
    }

    @Override
    public void deactivateUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found !!!"));
        user.setStatus(false);
        this.userRepository.save(user);
    }

    @Override
    public void reactivateUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found !!!"));
        user.setStatus(true);
        this.userRepository.save(user);
    }
}
