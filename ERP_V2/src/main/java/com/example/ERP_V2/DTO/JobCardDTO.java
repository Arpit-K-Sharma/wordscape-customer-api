package com.example.ERP_V2.DTO;

import com.example.ERP_V2.Model.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
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

    private String jobTitle;

    private String serviceRequired;

    private String type;

    private String size;

    private String printRun;

    private String noOfSides;

    private Date deadline;

    private List<PrePressUnitDTO> prePressUnitDTOList = new ArrayList<>();

    private List<PlateUnitDTO> plateUnitList = new ArrayList<>();

    private  List<PaperUnitDTO> paperunitList = new ArrayList<>();

    private List<BindingUnitDTO> bindingUnitList = new ArrayList<>();

    private List<PressUnitDTO> pressUnitList = new ArrayList<>();

    private List<ProjectDetailsDTO> projectDetailsList = new ArrayList<>();

    private List<PaperUsedDTO> paperUsedList = new ArrayList<>();

    private List<ContractJobDTO> contractJobList = new ArrayList<>();

    private List<CostCalculationDTO> costCalculationList = new ArrayList<>();



}
