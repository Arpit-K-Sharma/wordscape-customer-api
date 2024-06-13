package com.example.ERP_V2.Controller;

import com.example.ERP_V2.DTO.CustomerDTO;
import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.Model.Order;
import com.example.ERP_V2.Repository.OrderRepo;
import com.example.ERP_V2.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.File;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderService orderService;

    @PostMapping("{id}")
    public ResponseEntity<String> addOrder(@PathVariable int id,@RequestBody OrderDTO orderDTO) throws MessagingException {
        this.orderService.handleOrder(id, orderDTO);
        return ResponseEntity.ok("Order Added !!! The order invoice is being sent !!!");
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ){
        List<OrderDTO> orders = orderService.getAllOrders(pageNumber, pageSize);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int id){
        OrderDTO orderDTO = this.orderService.getOrderById(id);
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping(value = "invoice/{id}")
    public byte[] getInvoiceById(@PathVariable int id){
        return this.orderService.getInvoiceById(id);
    }

    @GetMapping("customer/{id}")
    public List<Order> getOrdersByCustomerId(@PathVariable int id){
        return this.orderService.getOrderByCustomerId(id);
    }

    @PutMapping("cancel/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable int id){
        this.orderService.cancelOrder(id);
        return ResponseEntity.ok("Order Cancelled");
    }



}
