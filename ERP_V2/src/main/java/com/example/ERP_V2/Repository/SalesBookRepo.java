package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.SalesBook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesBookRepo extends MongoRepository<SalesBook,String> {
}
