package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.Paper;
import com.example.ERP_V2.Services.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/papers")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @GetMapping
    public ResponseEntity<List<Paper>> getAllPapers() {
        List<Paper> papers = paperService.getAllPapers();
        return new ResponseEntity<>(papers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createPaper(@RequestBody Paper paper) {
        paperService.createPaper(paper);
        return ResponseEntity.ok("New Paper Added !!!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePaper(@PathVariable Long id, @RequestBody Paper updatedPaper) {
        paperService.updatePaper(id, updatedPaper);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
