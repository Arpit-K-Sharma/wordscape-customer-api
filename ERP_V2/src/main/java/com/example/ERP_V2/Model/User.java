package com.example.ERP_V2.Model;

import com.example.ERP_V2.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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

    private String phoneNumber;

    private Date created_at;

    private String companyName;

    private boolean status = true;

    private RoleEnum role;

    public User(String fullName, String password, String email) {
        this.fullName = fullName;
        this.password = password;
        this.email = email;
    }

    public User(String fullName, String email, String address, String password, String phoneNumber, String companyName, boolean status) {
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.status = status;
        this.created_at = new Date(); // Assuming the creation date is set to the current date
    }

}

