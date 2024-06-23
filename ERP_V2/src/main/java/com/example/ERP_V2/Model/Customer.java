//package com.example.ERP_V2.Model;
//
//import com.example.ERP_V2.enums.RoleEnum;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.util.Date;
//
//@Document(collection = "customer")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Customer {
//
//    @Id
//    private String customerId; // Use primitive int for id
//
//    private String fullName;
//
//    private Date created_at;
//
//    private String address;
//
//    private String email;
//
//    private String password; // Hashed and secured password storage recommended (important)
//
//    private String phoneNumber;
//
//    private String companyName;
//
//    private boolean status = true;
//
//    private RoleEnum role = RoleEnum.ROLE_CUSTOMER;
//
//    public Customer(String fullName, String address, String email, String password, String phoneNumber, String companyName, boolean status) {
//        this.fullName = fullName;
//        this.address = address;
//        this.email = email;
//        this.password = password;
//        this.phoneNumber = phoneNumber;
//        this.companyName = companyName;
//        this.status = status;
//    }
//}
//
