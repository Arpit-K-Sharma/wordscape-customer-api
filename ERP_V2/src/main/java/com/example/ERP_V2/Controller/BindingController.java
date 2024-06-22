package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.Binding;
import com.example.ERP_V2.Services.BindingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/bindings")
public class BindingController {

    @Autowired
    private BindingService bindingService;

    @GetMapping
    public ResponseEntity<List<Binding>> getAllBindings() {
        log.info("ENDPOINT CALLED: /bindings (GET)");
        List<Binding> bindings = bindingService.getAllBindings();
        log.info("RESPONSE: {} bindings found", bindings.size());
        return new ResponseEntity<>(bindings, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public ResponseEntity<String> createBinding(@RequestBody Binding binding) {
        log.info("ENDPOINT CALLED: /bindings (POST)");
        log.info("REQUEST BODY: {}", binding);
        bindingService.createBinding(binding);
        log.info("Binding created successfully");
        return ResponseEntity.ok("Binding Added!!");
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBinding(@PathVariable String id, @RequestBody Binding updatedBinding) {
        log.info("ENDPOINT CALLED: /bindings/{} (PUT)", id);
        log.info("REQUEST BODY: {}", updatedBinding);
        bindingService.updateBinding(id, updatedBinding);
        log.info("Binding with ID {} updated successfully", id);
        return ResponseEntity.ok("Binding Updated !!!");
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/{bindingId}")
    public ResponseEntity<Void> deletePaper(@PathVariable String bindingId) {
        log.info("ENDPOINT CALLED: /papers/{} (DELETE)", bindingId);
        bindingService.deleteBinding(bindingId);
        log.info("Paper with ID {} deleted successfully", bindingId);
        return ResponseEntity.noContent().build();
    }
}
