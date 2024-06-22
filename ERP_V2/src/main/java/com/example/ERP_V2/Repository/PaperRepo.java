package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.Paper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaperRepo extends MongoRepository<Paper,String> {
    Optional<Paper> findByPaperType(String paperType);

}