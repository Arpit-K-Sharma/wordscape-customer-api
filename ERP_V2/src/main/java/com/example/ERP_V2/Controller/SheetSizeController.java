package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.SheetSize;
import com.example.ERP_V2.Services.SheetSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sheetSizes")
public class SheetSizeController {

    @Autowired
    private SheetSizeService sheetSizeService;

    @GetMapping
    public List<SheetSize> getAllSheetSizes() {
        return sheetSizeService.getAllSheetSizes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SheetSize> getSheetSizeById(@PathVariable String id) {
        Optional<SheetSize> sheetSize = sheetSizeService.getSheetSizeById(id);
        return sheetSize.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SheetSize createSheetSize(@RequestBody SheetSize sheetSize) {
        return sheetSizeService.createSheetSize(sheetSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SheetSize> updateSheetSize(@PathVariable String id, @RequestBody SheetSize sheetSize) {
        Optional<SheetSize> updatedSheetSize = sheetSizeService.updateSheetSize(id, sheetSize);
        return updatedSheetSize.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSheetSize(@PathVariable String id) {
        boolean isDeleted = sheetSizeService.deleteSheetSize(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
