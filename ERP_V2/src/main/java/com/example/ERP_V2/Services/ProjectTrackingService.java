package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.ProjectTrackingDTO;

public interface ProjectTrackingService {

    void setProjectTracking(String id, ProjectTrackingDTO projectTrackingDTO);

    ProjectTrackingDTO getProjectTracking(String id);
}
