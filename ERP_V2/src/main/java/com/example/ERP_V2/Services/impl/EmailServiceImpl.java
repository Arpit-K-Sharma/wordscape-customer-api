package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.DTO.OrderDTO;
import com.example.ERP_V2.Model.User;
import com.example.ERP_V2.Services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.util.concurrent.CompletableFuture;


@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private Html2PdfServiceImpl html2PdfServiceImpl;

    @Async("taskExecutor")
    @Override
    public CompletableFuture<Void> sendEmail(String to, int otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("luciferdynamic598@gmail.com");
        message.setTo(to);
        message.setSubject("OTP");
        message.setText("Your OTP is: " + otp);
        emailSender.send(message);
        return CompletableFuture.completedFuture(null);
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<Void> sendHTMLEmail(User customer, OrderDTO orderDTO) {
        log.info("Starting to send HTML email to {}", customer.getEmail());
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
            context.setVariable("innerLaminationType", orderDTO.getInnerLamination());
            context.setVariable("outerLaminationType", orderDTO.getOuterLamination());
            context.setVariable("inkType", orderDTO.getInkType());
            context.setVariable("remarks", orderDTO.getRemarks());

            String htmlContent = templateEngine.process("email-template", context);

            byte[] pdfBytes = html2PdfServiceImpl.htmlToPdf(htmlContent, customer, orderDTO);

            if (pdfBytes != null) {
                ByteArrayDataSource dataSource = new ByteArrayDataSource(pdfBytes, "application/pdf");
                // Set the HTML content
                helper.setText(htmlContent, true);
                helper.addAttachment("Invoice.pdf", dataSource);
            }

            emailSender.send(message);
            log.info("Email successfully sent to {}", customer.getEmail());
        } catch (MessagingException e) {
            log.error("Error while sending email to {}: {}", customer.getEmail(), e.getMessage(), e);
        }

        return CompletableFuture.completedFuture(null);
    }


}
