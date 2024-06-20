package com.example.ERP_V2.Services;
import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.Model.Customer;

import javax.mail.MessagingException;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public interface EmailService {
    public void sendEmail(String to, int otp);

    public CompletableFuture<Void> sendHTMLEmail(Customer customer, OrderDTO orderDTO) throws MessagingException;

}
