package com.example.ERP_V2.Services;
import com.example.ERP_V2.DTO.OrderDTO;
import javax.mail.MessagingException;
import java.nio.file.Path;

public interface EmailService {
    public void sendEmail(String to,String name);

    public void sendHTMLEmail(String to, OrderDTO orderDTO) throws MessagingException;

    public void sendJobCard(Path filepath) throws MessagingException;
}
