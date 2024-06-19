package com.example.ERP_V2.Services.impl;
import com.example.ERP_V2.DTO.PdfUploadDTO;
import com.example.ERP_V2.Services.PDFService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class PDFServiceImpl implements PDFService {

    @Async("taskExecutor")
    @Override
    public void savePdfFile(PdfUploadDTO pdfUploadDTO, Path path) {
        MultipartFile pdfFile = pdfUploadDTO.getPdfFile();
        if (pdfFile.isEmpty()) throw new RuntimeException("File not found");

        try {
            Files.createDirectories(path.getParent());
            Files.copy(pdfFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            log.info("File with name {} uploaded successfully", pdfFile.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while uploading the file. Error message = "+e);
            // Handle the exception appropriately
        }
    }

    @Override
    public String generateFilename(MultipartFile pdfFile, String customerId) {
        String originalFilename = StringUtils.cleanPath(pdfFile.getOriginalFilename());
        String extension = StringUtils.getFilenameExtension(originalFilename);

        // Ensure extension is not null or empty
        if (extension == null || extension.isEmpty()) {
            extension = "pdf"; // Default to "pdf" if no extension is found
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormat.format(new Date());

        // Concatenate customerId, currentDateTime and extension to form the unique filename
        String filename = customerId + "_" + currentDateTime + "." + extension;

        return filename;
    }


    @Override
    public byte[] downloadPdf(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
