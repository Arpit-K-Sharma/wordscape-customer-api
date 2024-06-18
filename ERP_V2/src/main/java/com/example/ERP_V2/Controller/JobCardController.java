package com.example.ERP_V2.Controller;

import com.example.ERP_V2.DTO.JobCardDTO;
import com.example.ERP_V2.Model.Order;
import com.example.ERP_V2.Services.JobCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("jobCard")
@Slf4j
public class JobCardController {

    @Autowired
    private JobCardService jobCardService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{order_id}")
    public ResponseEntity<String> createJobCard(@PathVariable int order_id, @RequestBody JobCardDTO jobCardDTO) {
        log.info("ENDPOINT CALLED: /jobCard/{} (POST)", order_id);
        log.info("REQUEST BODY: {}", jobCardDTO);
        jobCardService.createJobCard(order_id, jobCardDTO);
        log.info("JobCard created successfully for order ID: {}", order_id);
        return ResponseEntity.ok("JobCard Added !!!");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update/{order_id}")
    public ResponseEntity<String> updateJobCard(@PathVariable int order_id, @RequestBody JobCardDTO jobCardDTO) {
        log.info("ENDPOINT CALLED: /jobCard/update/{} (PUT)", order_id);
        log.info("REQUEST BODY: {}", jobCardDTO);
        jobCardService.updateJobCard(order_id, jobCardDTO);
        log.info("JobCard updated successfully for order ID: {}", order_id);
        return ResponseEntity.ok("JobCard Updated !!!");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/updateDeadline/{order_id}")
    public ResponseEntity<String> updateDeadline(@PathVariable int order_id, @RequestBody String deadline) {
        log.info("ENDPOINT CALLED: /jobCard/updateDeadline/{} (PUT)", order_id);
        log.info("REQUEST BODY: {}", deadline);
        jobCardService.updateDeadline(order_id, deadline);
        log.info("Deadline updated successfully for order ID: {}", order_id);
        return ResponseEntity.ok("Deadline Updated");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{order_id}")
    public Order getJobCard(@PathVariable int order_id) {
        log.info("ENDPOINT CALLED: /jobCard/{} (GET)", order_id);
        Order order = jobCardService.getJobCardById(order_id);
        log.info("RESPONSE: JobCard found for order ID: {}", order_id);
        return order;
    }
}
