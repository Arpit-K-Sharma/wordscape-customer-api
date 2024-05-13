package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.OrderDTO;

import javax.mail.MessagingException;
import java.io.File;
import java.util.List;

public interface OrderService {
    void handleOrder(OrderDTO orderDTO) throws MessagingException;

    List<OrderDTO> getAllOrders();

    byte[] getInvoiceById(int id);



    public OrderDTO getOrderById(int id);
}

