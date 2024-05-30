package com.example.ERP_V2.Controller;

import com.example.ERP_V2.DTO.CustomerDTO;
import com.example.ERP_V2.Model.Customer;
import com.example.ERP_V2.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("register")
    public ResponseEntity<String> registerAsUser(@RequestBody @Valid CustomerDTO customerDTO) {
        this.customerService.registerAsCustomer(customerDTO);
        return ResponseEntity.ok("Registered Successfully !!!");
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable int id) {
        CustomerDTO customer = customerService.getCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer) {
        customerService.updateCustomer(id, updatedCustomer);
        return ResponseEntity.ok("Customer updated !!!");
    }

    @PutMapping("deactivate/{id}")
    public ResponseEntity<String> deactivateCustomer(@PathVariable int id){
        customerService.deactivateCustomer(id);
        return ResponseEntity.ok("Customer Deactivated !!!");
    }

    @PutMapping("reactivate/{id}")
    public ResponseEntity<String> reactivateCustomer(@PathVariable int id){
        customerService.reactivateCustomer(id);
        return ResponseEntity.ok("Customer Reactivated !!!");
    }
}

