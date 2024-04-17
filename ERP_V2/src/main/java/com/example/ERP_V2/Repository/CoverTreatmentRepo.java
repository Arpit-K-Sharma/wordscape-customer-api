package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.CoverTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoverTreatmentRepo extends JpaRepository<CoverTreatment,Integer> {
    Optional<CoverTreatment> findByCoverTreatmentType(String coverTreatmentType);
}
