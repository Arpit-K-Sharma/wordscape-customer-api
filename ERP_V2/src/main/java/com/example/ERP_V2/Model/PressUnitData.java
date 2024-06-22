package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PressUnitData {
    private int pressUnitDataId;

    private String totalSet;
    private String forma;
    private String workAndTurn;

    private List<PressData> pressData = new ArrayList<>();
}
