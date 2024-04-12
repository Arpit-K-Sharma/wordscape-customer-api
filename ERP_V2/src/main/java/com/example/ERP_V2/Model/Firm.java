package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Firm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int firmId;

    @Column(length = 60)
    private String firmName;

    @Column(length = 50)
    private String regNo;

    @Column(length = 10)
    private String PAN;

    @Column(length = 100)
    private String address;

    @Column(length = 50)
    private String phone;

    private boolean active;

}
