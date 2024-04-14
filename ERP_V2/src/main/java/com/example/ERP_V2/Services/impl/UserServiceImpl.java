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
}
