package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.Lamination;
import com.example.ERP_V2.Services.LaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laminations")
public class LaminationController {

    @Autowired
    private LaminationService laminationService;

    @GetMapping
    public ResponseEntity<List<Lamination>> getAllLaminations() {
        List<Lamination> laminations = laminationService.getAllLaminations();
        return new ResponseEntity<>(laminations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createLamination(@RequestBody Lamination lamination) {
        laminationService.createLamination(lamination);
        return ResponseEntity.ok("New Lamination Added !!!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateLamination(@PathVariable int id, @RequestBody Lamination updatedLamination) {
        laminationService.updateLamination(id, updatedLamination);
        return ResponseEntity.ok("Lamination updated !!!");

    }
}

