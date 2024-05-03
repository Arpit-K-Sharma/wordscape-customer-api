package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {


    Optional<Customer> findByPhoneNumber(String phone);

    Optional<Customer> findByEmail(String email);
}
