package com.example.ERP_V2.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperData {
    private PaperData0 paperData0;

    private List<PaperData1> paperData1 = new ArrayList<>();

    private List<PaperData2> paperData2 = new ArrayList<>();

    private List<PaperData3> paperData3 = new ArrayList<>();

}
