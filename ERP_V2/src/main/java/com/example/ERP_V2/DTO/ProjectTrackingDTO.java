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
public class ProjectTrackingDTO {


    private int projectTrackingId;

    private boolean orderSlip = false;


    private boolean jobCard = false;

    private boolean paperCutting = false;

    private boolean platePreparation = false;

    private boolean printing = false;

    private boolean postPress = false;

    private boolean delivery = false;

    private boolean invoice = false;

    private boolean end = false;
}
