package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.Plate;
import com.example.ERP_V2.Services.PlateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plates")
@Slf4j
public class PlateController {

    @Autowired
    private PlateService plateService;

    @GetMapping
    public ResponseEntity<List<Plate>> getAllPlates() {
        log.info("ENDPOINT CALLED: /plates (GET)");
        List<Plate> plates = plateService.getAllPlates();
        log.info("RESPONSE: {} plates returned", plates.size());
        return new ResponseEntity<>(plates, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public ResponseEntity<String> createPlate(@RequestBody Plate plate) {
        log.info("ENDPOINT CALLED: /plates (POST)");
        log.info("REQUEST BODY: {}", plate);
        plateService.createPlate(plate);
        log.info("New Plate created successfully");
        return ResponseEntity.ok("New Plate Added !!!");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlate(@PathVariable String id, @RequestBody Plate updatedPlate) {
        log.info("ENDPOINT CALLED: /plates/{} (PUT)", id);
        log.info("REQUEST BODY: {}", updatedPlate);
        plateService.updatePlate(id, updatedPlate);
        log.info("Plate with ID {} updated successfully", id);
        return ResponseEntity.ok("Plate updated !!!");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/{plateId}")
    public ResponseEntity<Void> deletePlate(@PathVariable String plateId) {
        log.info("ENDPOINT CALLED: /plates/{} (DELETE)", plateId);
        plateService.deletePlate(plateId);
        log.info("Plate with ID {} deleted successfully", plateId);
        return ResponseEntity.noContent().build();
    }
}
