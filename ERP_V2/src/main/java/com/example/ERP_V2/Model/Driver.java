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
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int driverId;

    @Column(length = 60)
    private String fullName;

    @Column(length = 100)
    private String address;

    @Column(length = 60)
    private String email;

    @Column(length = 40)
    private String phoneNumber;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Delivery> deliveryList = new ArrayList<>();

}

