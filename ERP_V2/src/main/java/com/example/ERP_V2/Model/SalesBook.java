package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "saleBook")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesBook {

    @Id
    private String invoiceNumber;

    private Date date;

    private Date deliveryDate;

    private int quantity;

    private Long amount;

    @DBRef
    private Customer customer;

    @DBRef
    private Order order;
}

