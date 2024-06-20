package com.example.ERP_V2.Controller;

import com.example.ERP_V2.DTO.ProjectTrackingDTO;
import com.example.ERP_V2.Services.ProjectTrackingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("projectTracking")
@Slf4j
public class ProjectTrackingController {

    @Autowired
    private ProjectTrackingService projectTrackingService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/{id}")
    public ResponseEntity<String> setProjectTracking(@PathVariable int id, @RequestBody ProjectTrackingDTO projectTrackingDTO){
        log.info("ENDPOINT CALLED: /projectTracking/{} (POST)", id);
        log.info("REQUEST BODY: {}", projectTrackingDTO);
        projectTrackingService.setProjectTracking(id, projectTrackingDTO);
        log.info("Project Tracking updated successfully for ID {}", id);
        return ResponseEntity.ok("Project Tracking Updated !!!");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CUSTOMER','ROLE_USER')")
    @GetMapping("/{id}")
    public ProjectTrackingDTO getProjectTracking(@PathVariable int id){
        log.info("ENDPOINT CALLED: /projectTracking/{} (GET)", id);
        ProjectTrackingDTO projectTrackingDTO = projectTrackingService.getProjectTracking(id);
        log.info("RESPONSE: {}", projectTrackingDTO);
        return projectTrackingDTO;
    }
}
