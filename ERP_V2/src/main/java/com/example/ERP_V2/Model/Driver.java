package com.example.ERP_V2.Model;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "driver")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    private String driverId;

    private String fullName;

    private String address;

    private String email;

    private String phoneNumber;

}

