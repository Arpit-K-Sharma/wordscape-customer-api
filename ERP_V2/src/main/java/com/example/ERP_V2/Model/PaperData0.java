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
public class PaperData0 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int paperData0_id;

    private String readyBy;
    private String date;
    private String time;
    private String type;
    private String size;
    private String numberOfPages;
    private String printrun;

    @Column(length = 1000)
    private String side;

    @OneToOne
    @JoinColumn(name = "paperData_id")
    @JsonBackReference
    private PaperData paperData;

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
