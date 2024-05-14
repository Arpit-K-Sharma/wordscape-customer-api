package com.example.ERP_V2.DTO;

import com.example.ERP_V2.Model.*;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    private OrderDTO orderDTO;

    private String jobTitle;

    private String serviceRequired;

    private String type;

    private String size;

    private String printRun;

    private String noOfSides;

    private Date deadline;

    private List<PrePressUnit> prePressUnitList = new ArrayList<>();

    private List<PlateUnit> plateUnitList = new ArrayList<>();

    private  List<PaperUnit> paperunitList = new ArrayList<>();

    private List<BindingUnit> bindingUnitList = new ArrayList<>();

    private List<PressUnit> pressUnitList = new ArrayList<>();

    private List<ProjectDetails> projectDetailsList = new ArrayList<>();

    private List<PaperUsed> paperUsedList = new ArrayList<>();

    private List<ContractJob> contractJobList = new ArrayList<>();

    private List<CostCalculation> costCalculationList = new ArrayList<>();



}
