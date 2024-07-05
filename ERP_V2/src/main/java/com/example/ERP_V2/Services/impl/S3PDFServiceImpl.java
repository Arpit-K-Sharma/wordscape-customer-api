package com.example.ERP_V2.Services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.example.ERP_V2.DTO.CustomerDTO;
import com.example.ERP_V2.Services.CustomerService;
import com.example.ERP_V2.Services.PDFService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class S3PDFServiceImpl implements PDFService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Async("taskExecutor")
    @Override
    public void savePdfFile(InputStream inputStream, String fileName) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, inputStream, metadata));
            log.info("File {} uploaded successfully to S3", fileName);
        } catch (Exception e) {
            log.error("Failed to upload file to S3", e);
            throw new RuntimeException("Failed to upload file to S3: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] downloadPdf(String filename) {
        log.info("downlaod PDF for : " + filename);
        try (S3Object s3Object = amazonS3.getObject(bucketName, filename);
             S3ObjectInputStream s3Input = s3Object.getObjectContent();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int readLength;
            while ((readLength = s3Input.read(buffer)) > 0) {
                outputStream.write(buffer, 0, readLength);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            log.error("Failed to download file from S3", e);
            throw new RuntimeException("Failed to download file from S3: " + e.getMessage(), e);
        }
    }
}
