package com.example.ERP_V2.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int paperDataId;

    @OneToOne(mappedBy = "paperData", cascade = CascadeType.ALL)
    private PaperData0 paperData0;

    @OneToMany(mappedBy = "paperData", cascade = CascadeType.ALL)
    private List<PaperData1> paperData1 = new ArrayList<>();

    @OneToMany(mappedBy = "paperData", cascade = CascadeType.ALL)
    private List<PaperData2> paperData2 = new ArrayList<>();

    @OneToMany(mappedBy = "paperData", cascade = CascadeType.ALL)
    private List<PaperData3> paperData3 = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
