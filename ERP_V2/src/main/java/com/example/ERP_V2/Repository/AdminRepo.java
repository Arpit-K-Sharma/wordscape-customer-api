package com.example.ERP_V2.Repository;
import com.example.ERP_V2.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer> {


    Optional<Admin> findByEmail(String email);
}