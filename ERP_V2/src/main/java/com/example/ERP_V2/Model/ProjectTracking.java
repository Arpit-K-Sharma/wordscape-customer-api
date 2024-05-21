package com.example.ERP_V2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private int projectTrackingId;

    @Column(name = "order_slip")
    private boolean orderSlip = false;

    @Column(name = "job_card")
    private boolean jobCard = false;

    @Column(name = "paper_cutting")
    private boolean paperCutting = false;

    @Column(name = "plate_preparation")
    private boolean platePreparation = false;

    private boolean printing = false;

    @Column(name = "post_press")
    private boolean postPress = false;

    private boolean delivery = false;

    @Column(name = "is_end")
    private boolean end = false;

}

