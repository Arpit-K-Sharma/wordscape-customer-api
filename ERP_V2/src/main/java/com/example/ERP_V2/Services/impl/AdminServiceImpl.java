package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.AdminDTO;
import com.example.ERP_V2.Model.Admin;
import com.example.ERP_V2.Repository.AdminRepo;
import com.example.ERP_V2.Services.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void createAdmin() {
        Admin admin = new Admin();
        admin.setFullName("Arpit Sharma");
        admin.setEmail("admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("1234"));
        this.adminRepo.save(admin);
    }

}
