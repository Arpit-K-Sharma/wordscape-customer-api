package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.PaperThickness;
import com.example.ERP_V2.Services.PaperThicknessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paperThickness")
@Slf4j
public class PaperThicknessController {

    @Autowired
    private PaperThicknessService paperThicknessService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<PaperThickness> createPaperThickness(@RequestBody PaperThickness paperThickness) {
        log.info("ENDPOINT CALLED: /paperThickness (POST)");
        log.info("REQUEST BODY: {}", paperThickness);
        PaperThickness newThickness = paperThicknessService.createPaperThickness(paperThickness);
        log.info("New Paper Thickness created successfully");
        return ResponseEntity.status(201).body(newThickness);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<PaperThickness>> getAllPaperThicknesses() {
        log.info("ENDPOINT CALLED: /paperThickness (GET)");
        List<PaperThickness> allThicknesses = paperThicknessService.getAllPaperThicknesses();
        log.info("RESPONSE: {} paper thicknesses returned", allThicknesses.size());
        return ResponseEntity.ok(allThicknesses);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{thicknessId}")
    public ResponseEntity<PaperThickness> getPaperThicknessById(@PathVariable int thicknessId) {
        log.info("ENDPOINT CALLED: /paperThickness/{} (GET)", thicknessId);
        PaperThickness thickness = paperThicknessService.getPaperThicknessById(thicknessId);
        if (thickness != null) {
            log.info("RESPONSE: Paper Thickness found with ID: {}", thicknessId);
        } else {
            log.warn("Paper Thickness with ID {} not found", thicknessId);
        }
        return ResponseEntity.ok(thickness);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{thicknessId}")
    public ResponseEntity<PaperThickness> updatePaperThickness(
            @PathVariable int thicknessId,
            @RequestBody PaperThickness updatedThickness
    ) {
        log.info("ENDPOINT CALLED: /paperThickness/{} (PUT)", thicknessId);
        log.info("REQUEST BODY: {}", updatedThickness);
        PaperThickness updated = paperThicknessService.updatePaperThickness(thicknessId, updatedThickness);
        if (updated != null) {
            log.info("Paper Thickness with ID {} updated successfully", thicknessId);
        } else {
            log.warn("Failed to update Paper Thickness with ID {}", thicknessId);
        }
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{thicknessId}")
    public ResponseEntity<Void> deletePaperThickness(@PathVariable int thicknessId) {
        log.info("ENDPOINT CALLED: /paperThickness/{} (DELETE)", thicknessId);
        paperThicknessService.deletePaperThickness(thicknessId);
        log.info("Paper Thickness with ID {} deleted successfully", thicknessId);
        return ResponseEntity.noContent().build();
    }
}
