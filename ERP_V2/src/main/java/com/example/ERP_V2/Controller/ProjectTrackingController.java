package com.example.ERP_V2.Controller;

import com.example.ERP_V2.DTO.ProjectTrackingDTO;
import com.example.ERP_V2.Services.ProjectTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("projectTracking")
public class ProjectTrackingController {

    @Autowired
    ProjectTrackingService projectTrackingService;

    @PostMapping("/{id}")
    ResponseEntity<String> setProjectTracking(@PathVariable int id, @RequestBody ProjectTrackingDTO projectTrackingDTO){
        projectTrackingService.setProjectTracking(id,projectTrackingDTO);
        return ResponseEntity.ok("Project Tracking Updated !!!");
    }

    @GetMapping("/{id}")
    ProjectTrackingDTO getProjectTracking(@PathVariable int id){

        return projectTrackingService.getProjectTracking(id);
    }
}
