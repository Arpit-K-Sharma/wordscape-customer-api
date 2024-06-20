package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.OTP;
import com.example.ERP_V2.Repository.OTPRepo;
import com.example.ERP_V2.Services.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPServiceImpl implements OTPService {

    @Autowired
    private OTPRepo otpRepository;

    @Override
    public void addOtp(OTP otp) {
        otpRepository.save(otp);
    }

    @Override
    public void updateOtp(OTP otp) {
        otpRepository.save(otp);
    }

    @Override
    public void deleteOtp(int otpId) {
        otpRepository.deleteById(otpId);
    }
}