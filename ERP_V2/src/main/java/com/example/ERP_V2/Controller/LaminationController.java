package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.Lamination;
import com.example.ERP_V2.Services.LaminationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/laminations")
public class LaminationController {

    @Autowired
    private LaminationService laminationService;

    @GetMapping
    public ResponseEntity<List<Lamination>> getAllLaminations() {
        log.info("ENDPOINT CALLED: /laminations (GET)");
        List<Lamination> laminations = laminationService.getAllLaminations();
        log.info("RESPONSE: {} laminations found", laminations.size());
        return new ResponseEntity<>(laminations, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public ResponseEntity<String> createLamination(@RequestBody Lamination lamination) {
        log.info("ENDPOINT CALLED: /laminations (POST)");
        log.info("REQUEST BODY: {}", lamination);
        laminationService.createLamination(lamination);
        log.info("New Lamination created successfully");
        return ResponseEntity.ok("New Lamination Added !!!");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateLamination(@PathVariable String id, @RequestBody Lamination updatedLamination) {
        log.info("ENDPOINT CALLED: /laminations/{} (PUT)", id);
        log.info("REQUEST BODY: {}", updatedLamination);
        laminationService.updateLamination(id, updatedLamination);
        log.info("Lamination with ID {} updated successfully", id);
        return ResponseEntity.ok("Lamination updated !!!");
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/{laminationId}")
    public ResponseEntity<Void> deletePaper(@PathVariable String laminationId) {
        log.info("ENDPOINT CALLED: /papers/{} (DELETE)", laminationId);
        laminationService.deleteLamination(laminationId);
        log.info("Paper with ID {} deleted successfully", laminationId);
        return ResponseEntity.noContent().build();
    }
}
