package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.PaperSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperSizeRepo extends JpaRepository<PaperSize,Integer> {
}
