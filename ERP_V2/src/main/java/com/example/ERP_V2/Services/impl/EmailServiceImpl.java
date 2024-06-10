package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.Model.Customer;
import com.example.ERP_V2.Services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;


@Service
@EnableAsync
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private Html2PdfServiceImpl html2PdfServiceImpl;

    @Override
    public void sendEmail(String to,String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("luciferdynamic598@gmail.com");
        message.setTo(to);
        message.setSubject("Order Invoice");
        message.setText("Your order has been received !!!");
        emailSender.send(message);
    }

    @Async
    @Override
    public CompletableFuture<Void> sendHTMLEmail(Customer customer, OrderDTO orderDTO) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(customer.getEmail());
            helper.setSubject("Order Invoice");

            // Load the HTML template
            Context context = new Context();
            context.setVariable("name", customer.getFullName());
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

            File pdfFile = html2PdfServiceImpl.htmlToPdf(htmlContent, customer, orderDTO);

            // Set the HTML content
            helper.setText(htmlContent, true);

            helper.addAttachment(pdfFile.getName(), pdfFile);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Log or handle the exception as needed
        }

        return CompletableFuture.completedFuture(null);
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
