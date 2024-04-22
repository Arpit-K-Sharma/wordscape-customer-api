package com.example.ERP_V2.Services;

import com.example.ERP_V2.Model.PaperSize;

import java.util.List;

public interface PaperSizeService {
    PaperSize createPaperSize(PaperSize paperSize);  // Create a new PaperSize

    List<PaperSize> getAllPaperSizes();  // Get all PaperSizes

    PaperSize getPaperSizeById(int paperSizeId);  // Get PaperSize by ID

    PaperSize updatePaperSize(int paperSizeId, PaperSize updatedPaperSize);  // Update PaperSize

    void deletePaperSize(int paperSizeId);  // Delete PaperSize
}

