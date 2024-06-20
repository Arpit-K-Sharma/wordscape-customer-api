package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OTPRepo extends JpaRepository<OTP, Integer> {
    Optional<OTP> findByEmail(String email);
}
