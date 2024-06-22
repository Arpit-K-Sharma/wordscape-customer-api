package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.Plate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlateRepo extends MongoRepository<Plate,String > {
    Optional<Plate> findByPlateSize(String name);

}