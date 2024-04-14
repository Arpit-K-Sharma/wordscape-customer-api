package com.example.ERP_V2.Services.impl;

import com.example.ERP_V2.Model.Lamination;
import com.example.ERP_V2.Repository.LaminationRepo;
import com.example.ERP_V2.Services.LaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void updateLamination(Long id, Lamination updatedLamination) {
    }
}
