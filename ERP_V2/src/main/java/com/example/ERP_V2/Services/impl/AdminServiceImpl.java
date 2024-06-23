package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.User;
import com.example.ERP_V2.Repository.UserRepo;
import com.example.ERP_V2.Services.AdminService;
import com.example.ERP_V2.enums.RoleEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void createAdmin() {
        // Check if admin table is empty
        if (userRepo.count() == 0) {
            User admin = new User();
            admin.setFullName("Arpit Sharma");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRole(RoleEnum.ROLE_ADMIN);
            userRepo.save(admin);
        }
    }

}
