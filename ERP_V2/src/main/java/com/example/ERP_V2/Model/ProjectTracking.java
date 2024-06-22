package com.example.ERP_V2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTracking {
    @Field(name = "order_slip")
    private boolean orderSlip = false;

    @Field(name = "job_card")
    private boolean jobCard = false;

    @Field(name = "paper_cutting")
    private boolean paperCutting = false;

    @Field(name = "plate_preparation")
    private boolean platePreparation = false;

    private boolean printing = false;

    @Field(name = "post_press")
    private boolean postPress = false;

    private boolean delivery = false;

    private boolean invoice = false;

    @Field(name = "is_end")
    private boolean end = false;

}

