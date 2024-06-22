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
public class BindingData {

    private String binderySelectedOption;

    private String filledInBy;

    private String approvedBy;

    @JsonProperty("binderySelectedOption")
    public void setBindingSelectedOption(List<String> options) {
        this.binderySelectedOption = options.stream()
                .collect(Collectors.joining(","));
    }

    // Helper method to convert comma-separated String to List<String>
    @JsonProperty("binderySelectedOption")
    public List<String> getBindingSelectedOption() {
        return binderySelectedOption != null ?
                Arrays.asList(binderySelectedOption.split(",")) :
                Collections.emptyList();
    }

}
