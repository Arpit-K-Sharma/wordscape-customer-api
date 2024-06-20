package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.Ink;
import com.example.ERP_V2.Services.InkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/inks")
public class InkController {

    @Autowired
    private InkService inkService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public ResponseEntity<Ink> createInk(@RequestBody Ink ink) {
        log.info("ENDPOINT CALLED: /inks (POST)");
        log.info("REQUEST BODY: {}", ink);
        Ink newInk = inkService.createInk(ink);
        log.info("Ink created successfully with ID: {}", newInk.getInkId());
        return ResponseEntity.status(201).body(newInk);
    }

    @GetMapping
    public ResponseEntity<List<Ink>> getAllInks() {
        log.info("ENDPOINT CALLED: /inks (GET)");
        List<Ink> inks = inkService.getAllInks();
        log.info("RESPONSE: {} inks found", inks.size());
        return ResponseEntity.ok(inks);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{inkId}")
    public ResponseEntity<Ink> getInkById(@PathVariable int inkId) {
        log.info("ENDPOINT CALLED: /inks/{} (GET)", inkId);
        Ink ink = inkService.getInkById(inkId);
        log.info("RESPONSE: Ink found with ID: {}", inkId);
        return ResponseEntity.ok(ink);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/{inkId}")
    public ResponseEntity<Ink> updateInk(@PathVariable int inkId, @RequestBody Ink updatedInk) {
        log.info("ENDPOINT CALLED: /inks/{} (PUT)", inkId);
        log.info("REQUEST BODY: {}", updatedInk);
        Ink updated = inkService.updateInk(inkId, updatedInk);
        log.info("Ink updated successfully with ID: {}", inkId);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/{inkId}")
    public ResponseEntity<Void> deleteInk(@PathVariable int inkId) {
        log.info("ENDPOINT CALLED: /inks/{} (DELETE)", inkId);
        inkService.deleteInk(inkId);
        log.info("Ink deleted successfully with ID: {}", inkId);
        return ResponseEntity.noContent().build();
    }
}
