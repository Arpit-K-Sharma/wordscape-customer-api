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
public class PrePressUnit {
    private String paymentMethod;

    // Store multiple options as a comma-separated string
    private String serviceRequired;

    @JsonProperty("serviceRequired")
    public void setServiceRequired(List<String> serviceList){
        this.serviceRequired = serviceList.stream().collect(Collectors.joining(","));
    }

    @JsonProperty("serviceRequired")
    public List<String> getBindingSelectedOption() {
        return serviceRequired != null ?
                Arrays.asList(serviceRequired.split(",")) :
                Collections.emptyList();
    }
}
