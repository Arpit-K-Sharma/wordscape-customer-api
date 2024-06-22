package com.example.ERP_V2.Services;

import com.example.ERP_V2.Model.PaperThickness;

import java.util.List;

public interface PaperThicknessService {
    PaperThickness createPaperThickness(PaperThickness paperThickness);  // Create new PaperThickness

    List<PaperThickness> getAllPaperThicknesses();  // Get all PaperThicknesses

    PaperThickness getPaperThicknessById(String thicknessId);  // Get by ID

    PaperThickness updatePaperThickness(String thicknessId, PaperThickness updatedThickness);  // Update PaperThickness

    void deletePaperThickness(String thicknessId);  // Delete PaperThickness
}