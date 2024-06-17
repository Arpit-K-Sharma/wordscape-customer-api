package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.PdfUploadDTO;
import com.example.ERP_V2.Model.Order;
import com.example.ERP_V2.Repository.OrderRepo;
import com.example.ERP_V2.Services.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class PDFServiceImpl implements PDFService {

    @Autowired
    private OrderRepo orderRepo;

    @Value("${order.pdf.directory}")
    private String pdfDirectory;

    @Override
    public String savePdfFile(PdfUploadDTO pdfUploadDTO) {
        MultipartFile pdfFile = pdfUploadDTO.getPdfFile();
        if (pdfFile.isEmpty()) throw new RuntimeException("File not found");

        String originalFilename = StringUtils.cleanPath(pdfFile.getOriginalFilename());
        String extension = StringUtils.getFilenameExtension(originalFilename);
        String filename = String.valueOf(UUID.randomUUID() + "." + extension);
        Path uploadPath = Paths.get(pdfDirectory + filename);
        try {
            Files.createDirectories(uploadPath.getParent());
            Files.copy(pdfFile.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return filename;
    }

    @Override
    public byte[] getOrderPdf(int orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found "));
        Path pdfPath = Paths.get(pdfDirectory + order.getPdfFilename());
        try {
            return Files.readAllBytes(pdfPath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
