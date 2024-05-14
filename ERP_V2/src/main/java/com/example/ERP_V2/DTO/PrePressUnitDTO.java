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
public class PrePressUnitDTO {

    private String materialReceived;

    private String imposition;

    private int flapSize;

    private String paperSize;

    private String gutterSize;

    private String gripperSize;

    private String coverPaperSize;

    private String innerPaperSize;

    private String folderName;

    private String plateProcessBy;

}
