package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "otp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTP {

    @Id
    private String optId;

    private int otp;

    private String email;

    private Date updated_at;

}
