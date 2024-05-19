package com.example.ERP_V2.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperData3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int paperData3_id;

    private String type;
    @JsonProperty("ptype")
    private String paperType;
    private String gsm;
    private String printColor;
    private String lamination;

    @ManyToOne
    @JoinColumn(name = "paperData_id")
    @JsonBackReference

    private PaperData paperData;
}
