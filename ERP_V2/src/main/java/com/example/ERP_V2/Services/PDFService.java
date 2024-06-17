package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.PdfUploadDTO;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface PDFService {

    public void savePdfFile(PdfUploadDTO pdfUploadDTO, Path path);

    public String generateFilename(MultipartFile pdfFile);

    public byte[] downloadPdf(Path path);
}
