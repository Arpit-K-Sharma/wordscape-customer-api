package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.Services.Html2PdfService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Service
public class Html2PdfServiceImpl implements Html2PdfService {

    public File htmlToPdf(String processedHtml, OrderDTO orderDTO) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            PdfWriter pdfwriter = new PdfWriter(byteArrayOutputStream);
            DefaultFontProvider defaultFont = new DefaultFontProvider(false, true, false);
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(defaultFont);
            HtmlConverter.convertToPdf(processedHtml, pdfwriter, converterProperties);

            String filename = orderDTO.getOrderId() + "_" + orderDTO.getName().replaceAll(" ", "_");
            String filePath = "ERP_V2/src/main/resources/static/invoice/" + filename + ".pdf";
            File pdfFile = new File(filePath);
            FileOutputStream fout = new FileOutputStream(pdfFile);

            byteArrayOutputStream.writeTo(fout);
            byteArrayOutputStream.close();
            byteArrayOutputStream.flush();
            fout.close();

            return pdfFile;
        } catch (Exception ex) {
            // Handle exception
        }
        return null;
    }
}
