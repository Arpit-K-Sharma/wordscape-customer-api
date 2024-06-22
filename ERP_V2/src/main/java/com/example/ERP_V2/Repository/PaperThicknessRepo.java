package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.PaperThickness;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperThicknessRepo extends MongoRepository<PaperThickness,String> {
}