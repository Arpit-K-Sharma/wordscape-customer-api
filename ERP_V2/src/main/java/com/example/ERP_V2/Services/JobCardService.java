package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.JobCardDTO;
import com.example.ERP_V2.Model.Order;

public interface JobCardService {
    void createJobCard(int orderId, JobCardDTO jobCardDTO);

    Order getJobCardById(int id);
}
