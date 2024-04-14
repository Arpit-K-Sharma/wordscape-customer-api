package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.SalesRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRecordRepo extends JpaRepository<SalesRecord,Integer> {
}
