package com.example.ERP_V2.Model;

import com.example.ERP_V2.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId; // Use a meaningful name (could be id)

    @Column(length = 60)
    private String fullName;

    @Column(length = 20)
    private String password; // Hashed and secured password storage recommended

    @Column(length = 60)
    private String email;

    @Column
    private boolean status;

    private final RoleEnum role = RoleEnum.ROLE_USER;

}

