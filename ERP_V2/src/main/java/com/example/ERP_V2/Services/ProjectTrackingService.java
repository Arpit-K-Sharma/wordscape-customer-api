package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.ProjectTrackingDTO;
import com.example.ERP_V2.Model.ProjectTracking;

public interface ProjectTrackingService {

    void setProjectTracking(int id, ProjectTrackingDTO projectTrackingDTO);

    ProjectTrackingDTO getProjectTracking(int id);
}
