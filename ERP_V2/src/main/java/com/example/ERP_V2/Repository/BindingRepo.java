package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.Binding;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BindingRepo extends MongoRepository<Binding,String> {
    Optional<Binding> findByBindingType(String name);
}