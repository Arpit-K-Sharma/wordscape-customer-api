package com.example.ERP_V2.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface PDFService {

    public void savePdfFile(InputStream inputStream, String fileName);

    public byte[] downloadPdf(String fileName);
}
