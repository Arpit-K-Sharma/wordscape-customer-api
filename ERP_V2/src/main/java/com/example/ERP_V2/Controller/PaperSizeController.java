package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.PaperSize;
import com.example.ERP_V2.Services.PaperSizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paperSizes")
@Slf4j
public class PaperSizeController {

    @Autowired
    private PaperSizeService paperSizeService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public ResponseEntity<PaperSize> createPaperSize(@RequestBody PaperSize paperSize) {
        log.info("ENDPOINT CALLED: /paperSizes (POST)");
        log.info("REQUEST BODY: {}", paperSize);
        PaperSize newPaperSize = paperSizeService.createPaperSize(paperSize);
        log.info("New Paper Size created successfully");
        return ResponseEntity.status(201).body(newPaperSize);
    }

    @GetMapping
    public ResponseEntity<List<PaperSize>> getAllPaperSizes() {
        log.info("ENDPOINT CALLED: /paperSizes (GET)");
        List<PaperSize> paperSizes = paperSizeService.getAllPaperSizes();
        log.info("RESPONSE: {} paper sizes returned", paperSizes.size());
        return ResponseEntity.ok(paperSizes);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{paperSizeId}")
    public ResponseEntity<PaperSize> getPaperSizeById(@PathVariable int paperSizeId) {
        log.info("ENDPOINT CALLED: /paperSizes/{} (GET)", paperSizeId);
        PaperSize paperSize = paperSizeService.getPaperSizeById(paperSizeId);
        log.info("RESPONSE: Paper Size found with ID: {}", paperSizeId);
        return ResponseEntity.ok(paperSize);
    }

    @PutMapping("/{paperSizeId}")
    public ResponseEntity<PaperSize> updatePaperSize(
            @PathVariable int paperSizeId,
            @RequestBody PaperSize updatedPaperSize) {
        log.info("ENDPOINT CALLED: /paperSizes/{} (PUT)", paperSizeId);
        log.info("REQUEST BODY: {}", updatedPaperSize);
        PaperSize updated = paperSizeService.updatePaperSize(paperSizeId, updatedPaperSize);
        log.info("Paper Size with ID {} updated successfully", paperSizeId);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{paperSizeId}")
    public ResponseEntity<Void> deletePaperSize(@PathVariable int paperSizeId) {
        log.info("ENDPOINT CALLED: /paperSizes/{} (DELETE)", paperSizeId);
        paperSizeService.deletePaperSize(paperSizeId);
        log.info("Paper Size with ID {} deleted successfully", paperSizeId);
        return ResponseEntity.noContent().build();
    }
}
