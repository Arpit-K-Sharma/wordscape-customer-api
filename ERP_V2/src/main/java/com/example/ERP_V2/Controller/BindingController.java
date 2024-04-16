package com.example.ERP_V2.Controller;

import com.example.ERP_V2.Model.Binding;
import com.example.ERP_V2.Services.BindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bindings")
public class BindingController {

    @Autowired
    private BindingService bindingService;

    @GetMapping
    public ResponseEntity<List<Binding>> getAllBindings() {
        List<Binding> bindings = bindingService.getAllBindings();
        return new ResponseEntity<>(bindings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createBinding(@RequestBody Binding binding) {
        bindingService.createBinding(binding);
        return ResponseEntity.ok("Binding Added!!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBinding(@PathVariable int id, @RequestBody Binding updatedBinding) {
        bindingService.updateBinding(id, updatedBinding);
        return ResponseEntity.ok("Binding Updated !!!");
    }
}

