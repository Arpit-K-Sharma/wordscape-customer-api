package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.PaperThickness;
import com.example.ERP_V2.Repository.PaperThicknessRepo;
import com.example.ERP_V2.Services.PaperThicknessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperThicknessServiceImpl implements PaperThicknessService {

    @Autowired
    private PaperThicknessRepo paperThicknessRepository;

    @Override
    public PaperThickness createPaperThickness(PaperThickness paperThickness) {
        return paperThicknessRepository.save(paperThickness);
    }

    @Override
    public List<PaperThickness> getAllPaperThicknesses() {
        return paperThicknessRepository.findAll();
    }

    @Override
    public PaperThickness getPaperThicknessById(int thicknessId) {
        return paperThicknessRepository.findById(thicknessId)
                .orElseThrow(() -> new RuntimeException("PaperThickness not found"));
    }

    @Override
    public PaperThickness updatePaperThickness(int thicknessId, PaperThickness updatedThickness) {
        PaperThickness existingThickness = getPaperThicknessById(thicknessId);
        existingThickness.setThickness(updatedThickness.getThickness());
        return paperThicknessRepository.save(existingThickness);
    }

    @Override
    public void deletePaperThickness(int thicknessId) {
        paperThicknessRepository.deleteById(thicknessId);
    }
}