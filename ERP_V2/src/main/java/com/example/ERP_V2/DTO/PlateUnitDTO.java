package com.example.ERP_V2.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlateUnitDTO {

    private String screenType;

    private String plateSize;

    private String color;

    private int total;

    private String plateDamage;

    private int noOfPlateRemake;
}
