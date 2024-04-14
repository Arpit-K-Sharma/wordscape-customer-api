package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.Plate;
import com.example.ERP_V2.Services.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plates")
public class PlateController {

    @Autowired
    private PlateService plateService;

    @GetMapping
    public ResponseEntity<List<Plate>> getAllPlates() {
        List<Plate> plates = plateService.getAllPlates();
        return new ResponseEntity<>(plates, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createPlate(@RequestBody Plate plate) {
        plateService.createPlate(plate);
        return ResponseEntity.ok("New Plate Added !!!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePlate(@PathVariable Long id, @RequestBody Plate updatedPlate) {
        plateService.updatePlate(id, updatedPlate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

