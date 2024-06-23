package com.example.ERP_V2.Model;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "salesRecord")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesRecord {

    @Id
    @Field("invoiceNumber")
    private String invoiceNumber;

    private String productName;

    private int quantity;

    private BigDecimal Rate;

    private Date date;

    private Date deliveryDate;

    @DBRef
    private User user;

    @DBRef
    private Order order;

}
