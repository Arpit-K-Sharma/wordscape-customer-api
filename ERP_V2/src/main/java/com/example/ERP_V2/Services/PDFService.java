package com.example.ERP_V2.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface PDFService {

    public void savePdfFile(InputStream inputStream, String fileName);

    public String generateFilename(MultipartFile pdfFile, String customer_id);

    public byte[] downloadPdf(String fileName);
}
