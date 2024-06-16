package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.DTO.PaginatedResponse;
import com.example.ERP_V2.DTO.PdfUploadDTO;
import com.example.ERP_V2.Model.Order;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.text.ParseException;
import java.util.List;

public interface OrderService {
    void handleOrder(int customer_id, OrderDTO orderDTO) throws MessagingException;

    String savePdfFile(PdfUploadDTO pdfUploadDTO);

    byte[] getOrderPdf(int orderId);

    PaginatedResponse<OrderDTO> getAllOrders(Integer pageNumber, Integer pageSize, String sortField, String sortDirection);

    byte[] getInvoiceById(int id);



    public OrderDTO getOrderById(int id);

    List<Order> getOrderByCustomerId(int id);

    public void insertDummyData() throws ParseException;

    void cancelOrder(int id);
}

