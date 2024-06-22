package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.JobCardDTO;
import com.example.ERP_V2.Model.Order;

import java.util.Date;

public interface JobCardService {
    void createJobCard(String orderId, JobCardDTO jobCardDTO);

    Order getJobCardById(String id);

    void updateJobCard(String orderId, JobCardDTO jobCardDTO);

    void updateDeadline(String order_id, String deadline);
}
