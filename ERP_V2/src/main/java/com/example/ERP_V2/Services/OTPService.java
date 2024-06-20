package com.example.ERP_V2.Services;

import com.example.ERP_V2.Model.OTP;

public interface OTPService {
    void addOtp(OTP otp);
    void updateOtp(OTP otp);
    void deleteOtp(int otpId);
}
