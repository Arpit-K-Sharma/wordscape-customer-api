package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.CoverTreatment;
import com.example.ERP_V2.Repository.CoverTreatmentRepo;
import com.example.ERP_V2.Services.CoverTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoverTreatmentServiceImpl implements CoverTreatmentService {

    @Autowired
    private CoverTreatmentRepo coverTreatmentRepository; // assuming CoverTreatmentRepository is a Spring Data JPA repository

    @Override
    public List<CoverTreatment> getAllCoverTreatments() {
        return coverTreatmentRepository.findAll();
    }

    @Override
    public void createCoverTreatment(CoverTreatment coverTreatment) {
        coverTreatmentRepository.save(coverTreatment);
    }

    @Override
    public void updateCoverTreatment(int id, CoverTreatment updatedCoverTreatment) {
        CoverTreatment existingCoverTreatment = coverTreatmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cover Treatment not found"));

        if (updatedCoverTreatment.getCoverTreatmentType() != null) {
            existingCoverTreatment.setCoverTreatmentType(updatedCoverTreatment.getCoverTreatmentType());
        }
        if (updatedCoverTreatment.getRate() != null) {
            existingCoverTreatment.setRate(updatedCoverTreatment.getRate());
        }

        coverTreatmentRepository.save(existingCoverTreatment);
    }

    @Override
    public void deleteCoverTreatment(int coverTreatmentId) {
        coverTreatmentRepository.deleteById(coverTreatmentId);
    }
}