package com.example.ERP_V2.Repository;

import com.example.ERP_V2.Model.Binding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface BindingRepo extends JpaRepository<Binding,Integer> {
    Optional<Binding> findByBindingType(String name);
}
