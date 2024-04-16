package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.User;
import com.example.ERP_V2.Repository.UserRepo;
import com.example.ERP_V2.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository; // assuming UserRepository is a Spring Data JPA repository

    @Override
    public void createUser(User user) {
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
        if (updatedUser.getUsername() != null) {
            existingUser.setUsername(updatedUser.getUsername());
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
}
