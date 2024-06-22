package com.example.ERP_V2.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperData3 {
    private int paperData3_id;

    private String type;
    @JsonProperty("ptype")
    private String paperType;
    private String gsm;
    private String printColor;
    private String lamination;
}
