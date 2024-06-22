package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperData2 {
    private String type;
    private String cutSheetSize;
    private String wastage;
    private String totalCutSheet;
}
