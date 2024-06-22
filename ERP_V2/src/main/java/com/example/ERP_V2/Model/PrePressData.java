package com.example.ERP_V2.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrePressData {
    private int prePressDataId;

    private String paymentMethod;

    private String materialReceived;

    private String flapSize;

}
