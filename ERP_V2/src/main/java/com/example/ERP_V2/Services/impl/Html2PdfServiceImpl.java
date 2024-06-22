package com.example.ERP_V2.Services.impl;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.Model.Customer;
import com.example.ERP_V2.Services.Html2PdfService;
import com.example.ERP_V2.Services.PDFService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class Html2PdfServiceImpl implements Html2PdfService {

    @Value("${aws.s3.invoice.path}")
    private String invoiceUploadDirectory;

    @Autowired
    private PDFService pdfService;

    public byte[] htmlToPdf(String processedHtml, Customer customer, OrderDTO orderDTO) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PdfWriter pdfwriter = new PdfWriter(byteArrayOutputStream);
            DefaultFontProvider defaultFont = new DefaultFontProvider(false, true, false);
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(defaultFont);
            HtmlConverter.convertToPdf(processedHtml, pdfwriter, converterProperties);

            byte[] pdfBytes = byteArrayOutputStream.toByteArray();

            String filename = orderDTO.getOrderId() + "_" + customer.getFullName().replaceAll(" ", "_") + ".pdf";
            String fullFilePath = invoiceUploadDirectory  + filename;

            // Upload to S3
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("application/pdf");
            metadata.setContentLength(pdfBytes.length);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfBytes);
            pdfService.savePdfFile(inputStream, fullFilePath);

            return pdfBytes;
        } catch (Exception ex) {
            // Handle exception
            ex.printStackTrace();
            return null;
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
