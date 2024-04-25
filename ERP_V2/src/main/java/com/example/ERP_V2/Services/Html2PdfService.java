package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.OrderDTO;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface Html2PdfService {

    public File htmlToPdf(String processedHtml, OrderDTO orderDTO);
}
