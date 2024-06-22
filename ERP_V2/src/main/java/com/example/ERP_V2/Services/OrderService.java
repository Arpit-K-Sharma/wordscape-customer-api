package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.DTO.PaginatedResponse;
import com.example.ERP_V2.DTO.PdfUploadDTO;
import com.example.ERP_V2.Model.Order;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface OrderService {
    void handleOrder(String id, OrderDTO orderDTO) throws MessagingException;


    PaginatedResponse<OrderDTO> getAllOrders(Integer pageNumber, Integer pageSize, String sortField, String sortDirection);

    byte[] getInvoiceById(String id);



    public OrderDTO getOrderById(String id);

    List<Order> getOrderByCustomerId(String id);

    public void insertDummyData() throws ParseException;

    void cancelOrder(String id);

    byte[] getOrderPdf(String orderId);

    String saveOrderPdfFile(PdfUploadDTO pdfUploadDTO,String customer_id) throws IOException;
}

