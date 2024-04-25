package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.Services.EmailService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendEmail(String to,String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("luciferdynamic598@gmail.com");
        message.setTo(to);
        message.setSubject("Order Invoice");
        message.setText("Your order has been received !!!");
        emailSender.send(message);
    }

    @Override
    public void sendHTMLEmail(String to, OrderDTO orderDTO) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Order Invoice");

        // Load the HTML template
        Context context = new Context();
        context.setVariable("name", orderDTO.getName());
        context.setVariable("paperSize", orderDTO.getPaperSize());
        context.setVariable("pages", orderDTO.getPages());
        context.setVariable("quantity", orderDTO.getQuantity());
        context.setVariable("bindingType", orderDTO.getBindingType());
        context.setVariable("coverTreatment", orderDTO.getCoverTreatmentType());
        context.setVariable("innerPaperType", orderDTO.getInnerPaperType());
        context.setVariable("innerPaperThickness", orderDTO.getInnerPaperThickness());
        context.setVariable("outerPaperType", orderDTO.getOuterPaperType());
        context.setVariable("outerPaperThickness", orderDTO.getOuterPaperThickness());
        context.setVariable("laminationType", orderDTO.getLaminationType());
        context.setVariable("inkType", orderDTO.getInkType());
        context.setVariable("remarks", orderDTO.getRemarks());

        String htmlContent = templateEngine.process("email-template", context);

        File pdfFile = htmlToPdf(htmlContent, orderDTO);


        // Set the HTML content
        helper.setText(htmlContent, true);


        helper.addAttachment(pdfFile.getName(), pdfFile);

        emailSender.send(message);
    }


    public File htmlToPdf(String processedHtml, OrderDTO orderDTO) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            PdfWriter pdfwriter = new PdfWriter(byteArrayOutputStream);

            DefaultFontProvider defaultFont = new DefaultFontProvider(false, true, false);

            ConverterProperties converterProperties = new ConverterProperties();

            converterProperties.setFontProvider(defaultFont);

            HtmlConverter.convertToPdf(processedHtml, pdfwriter, converterProperties);

            String filename = orderDTO.getOrderId()+"_"+orderDTO.getName().replaceAll(" ","_");

            File pdfFile = new File("C:\\Users\\SHADOW\\IdeaProjects\\ERP_V2\\ERP_V2\\src\\main\\resources\\static\\invoice\\" + filename + ".pdf");
            FileOutputStream fout = new FileOutputStream(pdfFile);

//            FileOutputStream fout = new FileOutputStream("C:\\Users\\SHADOW\\IdeaProjects\\ERP_V2\\ERP_V2\\src\\main\\resources\\static\\invoice\\"+filename+".pdf");

            byteArrayOutputStream.writeTo(fout);
            byteArrayOutputStream.close();
            byteArrayOutputStream.flush();
            fout.close();
        return pdfFile;
        } catch (Exception ex) {
//exception
        }
        return null;
    }

    @Override
    public void sendJobCard(Path filepath) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setTo("sweing222@gmail.com");
        helper.setSubject("Job Card Invoice");

        File file = filepath.toFile();
        helper.addAttachment("floppa.jpg",file);

        emailSender.send(message);
    }

//    @Override
//    public void sendJobCard(Path filepath) throws MessagingException, IOException {
//        MimeMessage message = emailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        try {
//            helper.setTo("sweing222@gmail.com");
//            helper.setSubject("Job Card Invoice");
//
//            // Read the image file
//            byte[] imageBytes = Files.readAllBytes(filepath);
//
//            // Attach the image as a multipart
//            ByteArrayDataSource dataSource = new ByteArrayDataSource(imageBytes, "image/jpeg");
//            helper.addAttachment("floppa.jpg", dataSource);
//
//            // Send email
//            emailSender.send(message);
//        } catch (MessagingException | IOException e) {
//            // Handle exceptions
//            e.printStackTrace();
//            throw e;
//        }
//    }

}
