package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.PaperThickness;
import com.example.ERP_V2.Services.PaperThicknessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paperThickness")
public class PaperThicknessController {

    @Autowired
    private PaperThicknessService paperThicknessService;

    @PostMapping
    public ResponseEntity<PaperThickness> createPaperThickness(@RequestBody PaperThickness paperThickness) {
        PaperThickness newThickness = paperThicknessService.createPaperThickness(paperThickness);
        return ResponseEntity.status(201).body(newThickness);
    }

    @GetMapping
    public ResponseEntity<List<PaperThickness>> getAllPaperThicknesses() {
        List<PaperThickness> allThicknesses = paperThicknessService.getAllPaperThicknesses();
        return ResponseEntity.ok(allThicknesses);
    }

    @GetMapping("/{thicknessId}")
    public ResponseEntity<PaperThickness> getPaperThicknessById(@PathVariable int thicknessId) {
        PaperThickness thickness = paperThicknessService.getPaperThicknessById(thicknessId);
        return ResponseEntity.ok(thickness);
    }

    @PutMapping("/{thicknessId}")
    public ResponseEntity<PaperThickness> updatePaperThickness(
            @PathVariable int thicknessId,
            @RequestBody PaperThickness updatedThickness
    ) {
        PaperThickness updated = paperThicknessService.updatePaperThickness(thicknessId, updatedThickness);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{thicknessId}")
    public ResponseEntity<Void> deletePaperThickness(@PathVariable int thicknessId) {
        paperThicknessService.deletePaperThickness(thicknessId);
        return ResponseEntity.noContent().build();
    }
}

