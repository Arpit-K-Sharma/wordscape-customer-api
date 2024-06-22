package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.Lamination;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaminationRepo extends MongoRepository<Lamination,String> {
    Optional<Lamination> findByLaminationType(String name);

}