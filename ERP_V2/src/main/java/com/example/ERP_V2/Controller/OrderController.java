package com.example.ERP_V2.Controller;

import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.DTO.PdfUploadDTO;
import com.example.ERP_V2.Model.Order;
import com.example.ERP_V2.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("{id}")
    public ResponseEntity<String> addOrder(@PathVariable int id,@RequestBody OrderDTO orderDTO) throws MessagingException {
        this.orderService.handleOrder(id, orderDTO);
        return ResponseEntity.ok("Order Added !!! The order invoice is being sent !!!");
    }

    @PostMapping("/files")
    public ResponseEntity<String> uploadPDF(@ModelAttribute PdfUploadDTO pdfUploadDTO){
        String filename = this.orderService.savePdfFile(pdfUploadDTO);
        return ResponseEntity.ok(filename);
    }

    @GetMapping("files/download/{orderId}")
    public ResponseEntity<byte[]> downloadOrderPdf(@PathVariable int orderId) {
        byte[] pdfData = orderService.getOrderPdf(orderId);
        if (pdfData == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=order_" + orderId + ".pdf")
                .body(pdfData);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        List<OrderDTO> orderDTOList = this.orderService.getAllOrders();
        return ResponseEntity.ok(orderDTOList);
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
