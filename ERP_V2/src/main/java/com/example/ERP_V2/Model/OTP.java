package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTP {

    private int otp;

    private String email;

    private Date updated_at;

}
