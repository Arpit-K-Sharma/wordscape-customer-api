package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);
}
