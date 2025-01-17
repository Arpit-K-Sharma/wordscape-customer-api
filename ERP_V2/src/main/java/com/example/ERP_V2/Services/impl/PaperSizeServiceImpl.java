package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.PaperSize;
import com.example.ERP_V2.Repository.PaperSizeRepo;
import com.example.ERP_V2.Services.PaperSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperSizeServiceImpl implements PaperSizeService {

    @Autowired
    private PaperSizeRepo paperSizeRepository;

    @Override
    public PaperSize createPaperSize(PaperSize paperSize) {
        return paperSizeRepository.save(paperSize);
    }

    @Override
    public List<PaperSize> getAllPaperSizes() {
        return paperSizeRepository.findAll();
    }

    @Override
    public PaperSize getPaperSizeById(String paperSizeId) {
        return paperSizeRepository.findById(paperSizeId)
                .orElseThrow(() -> new RuntimeException("PaperSize not found"));
    }

    @Override
    public PaperSize updatePaperSize(String paperSizeId, PaperSize updatedPaperSize) {
        PaperSize existingPaperSize = getPaperSizeById(paperSizeId);

        if (existingPaperSize == null) {
            throw new RuntimeException("PaperSize not found " );
        }

        if (updatedPaperSize.getPaperSize() != null) {
            existingPaperSize.setPaperSize(updatedPaperSize.getPaperSize());
        }

        if (updatedPaperSize.getDimensions() != null) {
            existingPaperSize.setDimensions(updatedPaperSize.getDimensions());
        }

        if (updatedPaperSize.getPaperLength() != 0) {
            existingPaperSize.setPaperLength(updatedPaperSize.getPaperLength());
        }

        if (updatedPaperSize.getPaperBreadth() != 0) {
            existingPaperSize.setPaperBreadth(updatedPaperSize.getPaperBreadth());
        }

        return paperSizeRepository.save(existingPaperSize);
    }

    @Override
    public void deletePaperSize(String paperSizeId) {
        PaperSize existingPaperSize = getPaperSizeById(paperSizeId);
        paperSizeRepository.delete(existingPaperSize);
    }
}