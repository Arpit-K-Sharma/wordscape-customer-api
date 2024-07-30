package com.example.ERP_V2.DTO;

import com.example.ERP_V2.Model.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobCardDTO {

    private String job_card_id;

    private PrePressUnit prePressUnitList;

    @JsonProperty("deliveryDetail")
    private Delivery delivery;

    private PrePressData prePressData;

    private PaperDetailData paperDetailData;

    private PlateDetailData plateDetailData;

    private PaperData paperData;

    private PressUnitData pressUnitData;

    private CostCalculation costCalculation;

    @JsonProperty("binderyData")
    private BindingData bindingData;
}
