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
    private int projectId;

    @Column(name = "order_slip")
    private boolean orderSlip;

    @Column(name = "job_card")
    private boolean jobCard;

    @Column(name = "paper_cutting")
    private boolean paperCutting;

    @Column(name = "plate_preparation")
    private boolean platePreparation;

    private boolean printing;

    @Column(name = "post_press")
    private boolean postPress;

    private boolean delivery;

    @Column(name = "is_end")
    private boolean end;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

}

