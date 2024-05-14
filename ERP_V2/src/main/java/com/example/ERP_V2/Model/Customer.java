package com.example.ERP_V2.Model;

import com.example.ERP_V2.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId; // Use primitive int for id

    @Column(length = 60)
    private String fullName;

    @Column(length = 150)
    private String address;

    @Column(length = 60)
    private String email;

    private String password; // Hashed and secured password storage recommended (important)

    @Column(length = 40)
    private String phoneNumber;

    @Column(length = 150)
    private String companyName;

    @Column
    private boolean status;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<SalesBook> salesBookList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<SalesRecord> salesRecordList = new ArrayList<>();

    private final RoleEnum role = RoleEnum.ROLE_CUSTOMER;

    public Customer(String fullName, String address, String email, String password, String phoneNumber, String companyName, boolean status) {
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.status = status;
    }
}

