package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.Ink;
import com.example.ERP_V2.Services.InkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inks")
public class InkController {

    @Autowired
    private InkService inkService;

    @PostMapping
    public ResponseEntity<Ink> createInk(@RequestBody Ink ink) {
        Ink newInk = inkService.createInk(ink);
        return ResponseEntity.status(201).body(newInk);
    }

    @GetMapping
    public ResponseEntity<List<Ink>> getAllInks() {
        List<Ink> inks = inkService.getAllInks();
        return ResponseEntity.ok(inks);
    }

    @GetMapping("/{inkId}")
    public ResponseEntity<Ink> getInkById(@PathVariable int inkId) {
        Ink ink = inkService.getInkById(inkId);
        return ResponseEntity.ok(ink);
    }

    @PutMapping("/{inkId}")
    public ResponseEntity<Ink> updateInk(@PathVariable int inkId, @RequestBody Ink updatedInk) {
        Ink updated = inkService.updateInk(inkId, updatedInk);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{inkId}")
    public ResponseEntity<Void> deleteInk(@PathVariable int inkId) {
        inkService.deleteInk(inkId);
        return ResponseEntity.noContent().build();
    }
}

