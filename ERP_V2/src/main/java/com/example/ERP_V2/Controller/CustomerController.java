package com.example.ERP_V2.Controller;

import com.example.ERP_V2.DTO.CustomerDTO;
import com.example.ERP_V2.DTO.PaginatedResponse;
import com.example.ERP_V2.Model.Customer;
import com.example.ERP_V2.Services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("register")
    public ResponseEntity<String> registerAsUser(@RequestBody @Valid CustomerDTO customerDTO) {
        log.info("\nENDPOINT CALLED: /customers/register\nREQUEST DTO: {}", customerDTO);
        this.customerService.registerAsCustomer(customerDTO);
        return ResponseEntity.ok("Registered Successfully !!!");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<PaginatedResponse<CustomerDTO>> getAllCustomers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortField", defaultValue = "customerId", required = false) String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "desc", required = false) String sortDirection
    ) {
        log.info("\nENDPOINT CALLED: /customers\nPAGE NUMBER: {}\nPAGE SIZE: {}\nSORT FIELD: {}\nSORT DIRECTION: {}",
                pageNumber, pageSize, sortField, sortDirection);
        PaginatedResponse<CustomerDTO> customers = customerService.getAllCustomers(pageNumber, pageSize, sortField, sortDirection);
        log.info("RESPONSE: {}", customers);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CUSTOMER')")
    @GetMapping("/self")
    public ResponseEntity<CustomerDTO> getCustomer(Authentication authentication) {
        log.info("\nENDPOINT CALLED: /customers/self\nAUTHENTICATION: {}", authentication.getName());
        CustomerDTO customer = customerService.getCustomer(Integer.parseInt(authentication.getName()));
        log.info("RESPONSE: {}", customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CUSTOMER')")
    @PutMapping
    public ResponseEntity<String> updateCustomer(Authentication authentication, @RequestBody Customer updatedCustomer) {
        log.info("\nENDPOINT CALLED: /customers\nAUTHENTICATION: {}\nUPDATED CUSTOMER: {}",
                authentication.getName(), updatedCustomer);
        customerService.updateCustomer(Integer.parseInt(authentication.getName()), updatedCustomer);
        return ResponseEntity.ok("Customer updated !!!");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("deactivate/{id}")
    public ResponseEntity<String> deactivateCustomer(@PathVariable int id) {
        log.info("\nENDPOINT CALLED: /customers/deactivate/{}\nID: {}", id);
        customerService.deactivateCustomer(id);
        return ResponseEntity.ok("Customer Deactivated !!!");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("reactivate/{id}")
    public ResponseEntity<String> reactivateCustomer(@PathVariable int id) {
        log.info("\nENDPOINT CALLED: /customers/reactivate/{}\nID: {}", id);
        customerService.reactivateCustomer(id);
        return ResponseEntity.ok("Customer Reactivated !!!");
    }
}
