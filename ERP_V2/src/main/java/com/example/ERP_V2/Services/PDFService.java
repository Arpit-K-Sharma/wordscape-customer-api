package com.example.ERP_V2.Services;

import com.example.ERP_V2.DTO.PdfUploadDTO;

public interface PDFService {

    public String savePdfFile(PdfUploadDTO pdfUploadDTO);

    public byte[] getOrderPdf(int orderId);
}
