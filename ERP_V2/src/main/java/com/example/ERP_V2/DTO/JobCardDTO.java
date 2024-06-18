package com.example.ERP_V2.DTO;

import com.example.ERP_V2.Model.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobCardDTO {

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
