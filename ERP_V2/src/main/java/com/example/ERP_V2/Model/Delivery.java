package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    private String destination;

    private Date deliveryDate;

    private Time deliveryTime;

    //JOB CARD

    private String company;

    private String venue;

    private String contactPersonName;

    private String contactPersonNumber;

    @DBRef
    private Driver driver;
}

