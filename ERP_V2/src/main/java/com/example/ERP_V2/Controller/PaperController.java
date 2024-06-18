package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.Paper;
import com.example.ERP_V2.Services.PaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/papers")
@Slf4j
public class PaperController {

    @Autowired
    private PaperService paperService;

    @GetMapping
    public ResponseEntity<List<Paper>> getAllPapers() {
        log.info("ENDPOINT CALLED: /papers (GET)");
        List<Paper> papers = paperService.getAllPapers();
        log.info("RESPONSE: {} papers returned", papers.size());
        return new ResponseEntity<>(papers, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> createPaper(@RequestBody Paper paper) {
        log.info("ENDPOINT CALLED: /papers (POST)");
        log.info("REQUEST BODY: {}", paper);
        paperService.createPaper(paper);
        log.info("New Paper created successfully");
        return ResponseEntity.ok("New Paper Added !!!");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePaper(@PathVariable int id, @RequestBody Paper updatedPaper) {
        log.info("ENDPOINT CALLED: /papers/{} (PUT)", id);
        log.info("REQUEST BODY: {}", updatedPaper);
        paperService.updatePaper(id, updatedPaper);
        log.info("Paper with ID {} updated successfully", id);
        return ResponseEntity.ok("Paper updated !!!");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{paperId}")
    public ResponseEntity<Void> deletePaper(@PathVariable int paperId) {
        log.info("ENDPOINT CALLED: /papers/{} (DELETE)", paperId);
        paperService.deletePaper(paperId);
        log.info("Paper with ID {} deleted successfully", paperId);
        return ResponseEntity.noContent().build();
    }
}
