package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.Model.Order;

import javax.mail.MessagingException;
import java.io.File;
import java.util.List;

public interface OrderService {
    void handleOrder(OrderDTO orderDTO) throws MessagingException;

    List<OrderDTO> getAllOrders();

    byte[] getInvoiceById(int id);



    public OrderDTO getOrderById(int id);

    List<Order> getOrderByCustomerId(int id);

    public void insertDummyData();
}

