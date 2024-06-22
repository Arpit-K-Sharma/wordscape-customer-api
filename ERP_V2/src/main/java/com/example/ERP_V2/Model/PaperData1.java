package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperData1 {
    private String type;
    private String fullSheetSize;
    private String weight;
    private String paperType;
    private String totalSheets;
}
