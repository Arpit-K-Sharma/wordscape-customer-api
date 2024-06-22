package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlateData {
    private int plateDataId;

    private String size;
    private String colour1;
    private String colour2;
    private String colour3;
    private String colour4;
    private String special;
    private String total;
}
