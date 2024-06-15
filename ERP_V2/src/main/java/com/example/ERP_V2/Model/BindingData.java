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
public class BindingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int bindingUnitId;

    // Store multiple options as a comma-separated string
    @Column(length = 1000)
    private String binderySelectedOption;

    private String filledInBy;

    private String approvedBy;

    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    // Helper method to convert List<String> to comma-separated String
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

    // Custom setter to handle JSON deserialization
    @JsonProperty("binderySelectedOption")
    public void setBindingSelectedOptionList(List<String> options) {
        setBindingSelectedOption(options);
    }
}
