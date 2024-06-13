package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.Lamination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaminationRepo extends JpaRepository<Lamination,Integer> {
    Optional<Lamination> findByLaminationType(String name);

}