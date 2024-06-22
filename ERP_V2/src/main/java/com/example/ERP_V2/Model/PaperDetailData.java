package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperDetailData {
    private String paperSize;
    private String gutterSize;
    private String gripperSize;
    private String coverPaperSize;
    private String innerPaperSize;
    private String folderName;
    private String plateProcessBy;
}
