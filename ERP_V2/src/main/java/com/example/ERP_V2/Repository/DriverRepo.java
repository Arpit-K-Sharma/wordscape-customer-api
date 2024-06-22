package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepo extends MongoRepository<Driver,String> {
}
