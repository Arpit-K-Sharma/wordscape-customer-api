package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.JobCardDTO;
import com.example.ERP_V2.Model.Order;

import java.util.Date;

public interface JobCardService {
    void createJobCard(int orderId, JobCardDTO jobCardDTO);

    Order getJobCardById(int id);

    void updateJobCard(int orderId, JobCardDTO jobCardDTO);

    void updateDeadline(int order_id, String deadline);
}
