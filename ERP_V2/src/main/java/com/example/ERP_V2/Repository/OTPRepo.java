package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPRepo extends MongoRepository<OTP, String> {
    Optional<OTP> findByEmail(String email);
}
