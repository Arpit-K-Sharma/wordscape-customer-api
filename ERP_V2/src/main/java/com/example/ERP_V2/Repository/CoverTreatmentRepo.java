package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.CoverTreatment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoverTreatmentRepo extends MongoRepository<CoverTreatment,String> {
    Optional<CoverTreatment> findByCoverTreatmentType(String coverTreatmentType);
}