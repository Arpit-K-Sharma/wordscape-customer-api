package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlateRepo extends JpaRepository<Plate,Integer> {
    Optional<Plate> findByPlateSize(String name);

}