package com.example.ERP_V2.Controller;

import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.DTO.PaginatedResponse;
import com.example.ERP_V2.DTO.PdfUploadDTO;
import com.example.ERP_V2.Model.Order;
import com.example.ERP_V2.Repository.OrderRepo;
import com.example.ERP_V2.Services.OrderService;
import com.example.ERP_V2.Services.PDFService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    @PostMapping
    public ResponseEntity<String> addOrder(Authentication authentication, @RequestBody OrderDTO orderDTO) throws MessagingException {
        log.info("ENDPOINT CALLED: /orders (POST)");
        log.info("REQUEST BODY: {}", orderDTO);
        this.orderService.handleOrder(Integer.parseInt(authentication.getName()), orderDTO);
        log.info("Order added successfully");
        return ResponseEntity.ok("Order Added !!! The order invoice is being sent !!!");
    }

    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    @PostMapping("/files")
    public ResponseEntity<Map<String, String>> uploadPDF(Authentication authentication,@ModelAttribute PdfUploadDTO pdfUploadDTO) {
        log.info("ENDPOINT CALLED: /orders/files (POST)");
        log.info("PDF FILENAME: {}", pdfUploadDTO.getPdfFile().getOriginalFilename());
        String customer_id = authentication.getName();
        String filename = this.orderService.saveOrderPdfFile(pdfUploadDTO,customer_id);
        log.info("PDF uploaded and saved successfully with filename: {}", filename);

        // Prepare JSON response
        Map<String, String> response = new HashMap<>();
        response.put("filename", filename);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CUSTOMER','ROLE_USER')")
    @GetMapping("files/download/{orderId}")
    public ResponseEntity<byte[]> downloadOrderPdf(@PathVariable int orderId) {
        log.info("ENDPOINT CALLED: /orders/files/download/{} (GET)", orderId);
        byte[] pdfData = orderService.getOrderPdf(orderId);
        if (pdfData == null) {
            log.warn("PDF not found for order ID: {}", orderId);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=order_" + orderId + ".pdf")
                .body(pdfData);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping
    public ResponseEntity<PaginatedResponse<OrderDTO>> getAllOrders(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortField", defaultValue = "orderId", required = false) String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "desc", required = false) String sortDirection
    ){
        log.info("ENDPOINT CALLED: /orders (GET)");
        log.info("PAGE NUMBER: {}, PAGE SIZE: {}, SORT FIELD: {}, SORT DIRECTION: {}", pageNumber, pageSize, sortField, sortDirection);
        PaginatedResponse<OrderDTO> orders = orderService.getAllOrders(pageNumber, pageSize, sortField, sortDirection);
        log.info("RESPONSE: {} orders returned", orders.getResponse().size());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CUSTOMER','ROLE_USER')")
    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int id){
        log.info("ENDPOINT CALLED: /orders/{} (GET)", id);
        OrderDTO orderDTO = this.orderService.getOrderById(id);
        log.info("RESPONSE: Order found with ID: {}", id);
        return ResponseEntity.ok(orderDTO);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CUSTOMER','ROLE_USER')")
    @GetMapping(value = "invoice/{id}")
    public byte[] getInvoiceById(@PathVariable int id){
        log.info("ENDPOINT CALLED: /orders/invoice/{} (GET)", id);
        return this.orderService.getInvoiceById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    @GetMapping("customer")
    public List<Order> getOrdersByCustomerId(Authentication authentication){
        log.info("ENDPOINT CALLED: /orders/customer (GET)");
        int customerId = Integer.parseInt(authentication.getName());
        List<Order> orders = this.orderService.getOrderByCustomerId(customerId);
        log.info("RESPONSE: {} orders found for customer ID: {}", orders.size(), customerId);
        return orders;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("cancel/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable int id){
        log.info("ENDPOINT CALLED: /orders/cancel/{} (PUT)", id);
        this.orderService.cancelOrder(id);
        log.info("Order with ID {} cancelled successfully", id);
        return ResponseEntity.ok("Order Cancelled");
    }
}
