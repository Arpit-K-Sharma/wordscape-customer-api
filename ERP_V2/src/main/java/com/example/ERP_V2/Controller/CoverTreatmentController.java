package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.CoverTreatment;
import com.example.ERP_V2.Services.CoverTreatmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/coverTreatments")
public class CoverTreatmentController {

    @Autowired
    private CoverTreatmentService coverTreatmentService;

    @GetMapping
    public ResponseEntity<List<CoverTreatment>> getAllCoverTreatments() {
        log.info("ENDPOINT CALLED: /coverTreatments (GET)");
        List<CoverTreatment> coverTreatments = coverTreatmentService.getAllCoverTreatments();
        log.info("RESPONSE: {} cover treatments found", coverTreatments.size());
        return new ResponseEntity<>(coverTreatments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCoverTreatment(@RequestBody CoverTreatment coverTreatment) {
        log.info("ENDPOINT CALLED: /coverTreatments (POST)");
        log.info("REQUEST BODY: {}", coverTreatment);
        coverTreatmentService.createCoverTreatment(coverTreatment);
        log.info("Cover Treatment created successfully");
        return ResponseEntity.ok("Cover Treatment Added !!!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCoverTreatment(@PathVariable int id, @RequestBody CoverTreatment updatedCoverTreatment) {
        log.info("ENDPOINT CALLED: /coverTreatments/{} (PUT)", id);
        log.info("REQUEST BODY: {}", updatedCoverTreatment);
        coverTreatmentService.updateCoverTreatment(id, updatedCoverTreatment);
        log.info("Cover Treatment with ID {} updated successfully", id);
        return ResponseEntity.ok("Cover Treatment updated !!!");
    }
}
