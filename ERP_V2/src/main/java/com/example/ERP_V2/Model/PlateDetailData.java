package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlateDetailData {

    private int plateDetailDataId;

    private String screenType;

    private String plateDamage;

    private String plateRemake;

    private List<PlateData> plateData = new ArrayList<>();
}
