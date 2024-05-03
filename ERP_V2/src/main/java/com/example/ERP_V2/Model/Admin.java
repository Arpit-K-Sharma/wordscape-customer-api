package com.example.ERP_V2.Model;


import com.example.ERP_V2.enums.RoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int admin_id;

    @Column(length = 60)
    private String fullName;

    @Column(length = 60)
    private String email;

    @Column(length = 20)
    private String password;

    private final RoleEnum role = RoleEnum.ROLE_ADMIN;
}
