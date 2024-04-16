package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.CoverTreatment;
import com.example.ERP_V2.Services.CoverTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coverTreatments")
public class CoverTreatmentController {

    @Autowired
    private CoverTreatmentService coverTreatmentService;

    @GetMapping
    public ResponseEntity<List<CoverTreatment>> getAllCoverTreatments() {
        List<CoverTreatment> coverTreatments = coverTreatmentService.getAllCoverTreatments();
        return new ResponseEntity<>(coverTreatments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCoverTreatment(@RequestBody CoverTreatment coverTreatment) {
        coverTreatmentService.createCoverTreatment(coverTreatment);
        return ResponseEntity.ok("Cover Treatment Added !!!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCoverTreatment(@PathVariable int id, @RequestBody CoverTreatment updatedCoverTreatment) {
        coverTreatmentService.updateCoverTreatment(id, updatedCoverTreatment);
        return ResponseEntity.ok("Cover Treatment updated !!!");
    }
}

