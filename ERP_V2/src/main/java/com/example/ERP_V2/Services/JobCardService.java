package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.JobCardDTO;

public interface JobCardService {
    void createJobCard(int orderId, JobCardDTO jobCardDTO);
}
