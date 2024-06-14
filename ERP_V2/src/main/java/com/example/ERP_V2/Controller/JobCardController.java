package com.example.ERP_V2.Controller;

import com.example.ERP_V2.DTO.JobCardDTO;
import com.example.ERP_V2.Model.Order;
import com.example.ERP_V2.Services.JobCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("jobCard")
public class JobCardController {

    @Autowired
    private JobCardService jobCardService;

    @PostMapping("/{order_id}")
    public ResponseEntity<String> createJobCard(@PathVariable int order_id, @RequestBody JobCardDTO jobCardDTO){
        jobCardService.createJobCard(order_id, jobCardDTO);
        return ResponseEntity.ok("JobCard Added !!!");
    }

    @PutMapping("/update/{order_id}")
    public ResponseEntity<String> updateJobCard(@PathVariable int order_id, @RequestBody JobCardDTO jobCardDTO){
        jobCardService.updateJobCard(order_id, jobCardDTO);
        return ResponseEntity.ok("JobCard Updated !!!");
    }

    @PutMapping("/updateDeadline/{order_id}")
    public ResponseEntity<String> updateDeadline(@PathVariable int order_id,@RequestBody String deadline){
        jobCardService.updateDeadline(order_id, deadline);
        return ResponseEntity.ok("Deadline Updated");
    }

    @GetMapping("/{id}")
    public Order getJobCard(@PathVariable int id){
        return jobCardService.getJobCardById(id);
    }
}
