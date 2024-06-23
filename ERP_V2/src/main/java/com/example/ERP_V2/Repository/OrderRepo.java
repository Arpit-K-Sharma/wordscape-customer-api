package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends MongoRepository<Order,String> {
    List<Order> findByCustomerUserId(String customerId);
}
