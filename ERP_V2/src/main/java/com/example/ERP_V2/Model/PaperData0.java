package com.example.ERP_V2.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperData0 {
    private int paperData0_id;

    private String readyBy;
    private String date;
    private String time;
    private String type;
    private String size;
    private String numberOfPages;
    private String printrun;

    private String side;

    // Helper method to convert List<String> to comma-separated String
    // Custom setter to handle JSON deserialization
    @JsonProperty("side")
    public void setSide(List<String> options) {
        this.side = options.stream()
                .collect(Collectors.joining(","));
    }

    // Helper method to convert comma-separated String to List<String>
    @JsonProperty("side")
    public List<String> getSide() {
        return side != null ?
                Arrays.asList(side.split(",")) :
                Collections.emptyList();
    }

}
