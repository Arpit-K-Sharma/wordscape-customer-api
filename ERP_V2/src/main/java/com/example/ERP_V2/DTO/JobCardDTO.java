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

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PrePressUnit prePressUnitList;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonProperty("deliveryDetail")
    private Delivery delivery;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PrePressData prePressData;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PaperDetailData paperDetailData;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PlateDetailData plateDetailData;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PaperData paperData;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PressUnitData pressUnitData;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonProperty("binderyData")
    private BindingData bindingData;
}
