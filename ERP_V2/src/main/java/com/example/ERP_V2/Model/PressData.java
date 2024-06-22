package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PressData {
    private int pressDataId;

    private String paperType;
    private String size;
    private String signature;
    private String ordered;
    private String produced;
}
