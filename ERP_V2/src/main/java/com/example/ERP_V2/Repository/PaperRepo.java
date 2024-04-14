package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperRepo extends JpaRepository<Paper,Integer> {
}
