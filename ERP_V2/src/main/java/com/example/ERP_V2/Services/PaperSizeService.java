package com.example.ERP_V2.Services;

import com.example.ERP_V2.Model.PaperSize;

import java.util.List;

public interface PaperSizeService {
    PaperSize createPaperSize(PaperSize paperSize);  // Create a new PaperSize

    List<PaperSize> getAllPaperSizes();  // Get all PaperSizes

    PaperSize getPaperSizeById(String paperSizeId);  // Get PaperSize by ID

    PaperSize updatePaperSize(String paperSizeId, PaperSize updatedPaperSize);  // Update PaperSize

    void deletePaperSize(String paperSizeId);  // Delete PaperSize
}