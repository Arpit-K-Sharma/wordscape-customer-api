package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.OrderDTO;

import java.util.List;

public interface OrderService {
    void handleOrder(OrderDTO orderDTO);

    List<OrderDTO> getAllOrders();
}
