package com.example.ERP_V2.Model;

import com.example.ERP_V2.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String  userId; // Use a meaningful name (could be id)

    private String fullName;

    private String password; // Hashed and secured password storage recommended

    private String email;

    private String address;

    private long phoneNumber;

    private boolean status = true;

    private RoleEnum role = RoleEnum.ROLE_USER;

    public User(String fullName, String password, String email) {
        this.fullName = fullName;
        this.password = password;
        this.email = email;
    }
}

