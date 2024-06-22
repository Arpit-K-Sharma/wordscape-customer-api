package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "firm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Firm {

    @Id
    private String firmId;

    private String firmName;

    private String regNo;

    private String PAN;

    private String address;

    private String phone;

    private boolean active;

}
