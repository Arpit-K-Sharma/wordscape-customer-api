package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepo extends JpaRepository<Delivery,Integer> {
}
