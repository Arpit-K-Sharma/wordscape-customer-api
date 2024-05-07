package com.example.ERP_V2.Model;
import com.example.ERP_V2.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int admin_id;

    @Column(length = 60)
    private String fullName;

    @Column(length = 60)
    private String email;

    private String password;

    private final RoleEnum role = RoleEnum.ROLE_ADMIN;



}
