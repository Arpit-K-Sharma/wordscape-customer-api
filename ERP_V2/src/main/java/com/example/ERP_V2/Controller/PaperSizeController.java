package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.PaperSize;
import com.example.ERP_V2.Services.PaperSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paperSizes")
public class PaperSizeController {

    @Autowired
    private PaperSizeService paperSizeService;

    @PostMapping
    public ResponseEntity<PaperSize> createPaperSize(@RequestBody PaperSize paperSize) {
        PaperSize newPaperSize = paperSizeService.createPaperSize(paperSize);
        return ResponseEntity.status(201).body(newPaperSize);
    }

    @GetMapping
    public ResponseEntity<List<PaperSize>> getAllPaperSizes() {
        List<PaperSize> paperSizes = paperSizeService.getAllPaperSizes();
        return ResponseEntity.ok(paperSizes);
    }

    @GetMapping("/{paperSizeId}")
    public ResponseEntity<PaperSize> getPaperSizeById(@PathVariable int paperSizeId) {
        PaperSize paperSize = paperSizeService.getPaperSizeById(paperSizeId);
        return ResponseEntity.ok(paperSize);
    }

    @PutMapping("/{paperSizeId}")
    public ResponseEntity<PaperSize> updatePaperSize(
            @PathVariable int paperSizeId,
            @RequestBody PaperSize updatedPaperSize) {
        PaperSize updated = paperSizeService.updatePaperSize(paperSizeId, updatedPaperSize);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{paperSizeId}")
    public ResponseEntity<Void> deletePaperSize(@PathVariable int paperSizeId) {
        paperSizeService.deletePaperSize(paperSizeId);
        return ResponseEntity.noContent().build();
    }
}
