package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {

    @Query(value = "SELECT * FROM ORDERS WHERE CUSTOMER_ID = :CUSTOMER_ID", nativeQuery = true)
    List<Order> findAllByCustomerId(@Param("CUSTOMER_ID") int customerId);
}
