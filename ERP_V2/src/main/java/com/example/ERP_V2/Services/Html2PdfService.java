package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.Model.Customer;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface Html2PdfService {

    public File htmlToPdf(String processedHtml, Customer customer, OrderDTO orderDTO);
}
