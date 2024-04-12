package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    @Column(length = 20)
    private String password; // Hashed and secured password storage recommended (important)

    @Column(length = 40)
    private String phoneNumber;

    @Column
    private boolean status;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<SalesBook> salesBookList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<SalesRecord> salesRecordList = new ArrayList<>();

}

