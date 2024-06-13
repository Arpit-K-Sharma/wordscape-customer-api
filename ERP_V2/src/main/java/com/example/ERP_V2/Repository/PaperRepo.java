package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaperRepo extends JpaRepository<Paper,Integer> {
    Optional<Paper> findByPaperType(String paperType);

}