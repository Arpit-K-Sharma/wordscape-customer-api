package com.example.ERP_V2.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrePressUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int prePressUnitId;

    private String paymentMethod;

    // Store multiple options as a comma-separated string
    @Column(length = 1000)
    private String serviceRequired;

    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

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
