package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.Lamination;
import com.example.ERP_V2.Repository.LaminationRepo;
import com.example.ERP_V2.Services.LaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaminationServiceImpl implements LaminationService {

    @Autowired
    private LaminationRepo laminationRepository; // assuming LaminationRepository is a Spring Data JPA repository

    @Override
    public List<Lamination> getAllLaminations() {
        return laminationRepository.findAll();
    }

    @Override
    public void createLamination(Lamination lamination) {
        laminationRepository.save(lamination);
    }

    @Override
    public void updateLamination(int id, Lamination updatedLamination) {
        Lamination existingLamination = laminationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lamination not found"));

        // Update fields if they are not null in the updatedLamination object
        if (updatedLamination.getLaminationType() != null) {
            existingLamination.setLaminationType(updatedLamination.getLaminationType());
        }
        if (updatedLamination.getRate() != null) {
            existingLamination.setRate(updatedLamination.getRate());
        }

        // Save the updated lamination
        laminationRepository.save(existingLamination);
    }

    @Override
    public void deleteLamination(int laminationId) {
        laminationRepository.deleteById(laminationId);
    }
}